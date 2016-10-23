package com.github.lazydeer.formlayout.fields;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import com.github.lazydeer.formlayout.FormLayout;
import com.github.lazydeer.formlayout.IsEmpty;
import com.github.lazydeer.formlayout.R;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dzq on 2016/10/20.
 */

public class InputField extends EditText implements View.OnFocusChangeListener {

    private String titleText;
    private AttributeParser parser;
    private DefaultValue defaultValue;
    private int inputHeight;

    private boolean haveFocus;

    private boolean validateFailed;
    private String validateFailedMessage;

    public InputField(Context context) {
        this(context, null);
    }

    public InputField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        registerListener();
    }

    public InputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        registerListener();
    }

    private void init(AttributeSet attrs) {
        defaultValue = new DefaultValue(getContext());
        parser = new AttributeParser(getContext());

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.InputField);
        titleText = array.getString(R.styleable.InputField_titleText);
        int inputFieldBackgroundResId = array.getResourceId(
                R.styleable.InputField_android_background, -1);
        if (inputFieldBackgroundResId != -1) {
            Drawable inputFieldBackground = ContextCompat.getDrawable(
                    getContext(), inputFieldBackgroundResId);
            parser.setInputFieldBackground(inputFieldBackground);
        }
        int padding = (int) array.getDimension(
                R.styleable.InputField_android_padding, -1);
        parser.setPadding(padding);
        parser.parseDrawable(array);
        parser.parseAttribute(array);
        array.recycle();

    }

    private void registerListener() {
        setOnFocusChangeListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ViewParent parent = getParent();
        if (parent instanceof FormLayout) {
            AttributeParser parentParser = ((FormLayout) parent).getParser();
            getConfig(parentParser);
        } else {
            getConfig(null);
        }
        initView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        this.inputHeight = height;
        if (parser.getTitleType() == TitleType.OUTSIDE_TOP
                || parser.getTitleType() == TitleType.INNER_TOP) {
            height += parser.getPadding() + parser.getTitleSize()
                    + parser.getTitleToInputSpace();
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackground(canvas);
        drawLeftDrawable(canvas);
        drawRightDrawable(canvas);
        drawTitle(canvas);
        super.onDraw(canvas);
    }

    private void initView() {
        int textStart = parser.getPadding();
        int textEnd = parser.getPadding();

        if (parser.getEndDrawable() != null) {
            int endDrawableWidth = getBitmapDrawableWidth(parser.getEndDrawable());
            textEnd += endDrawableWidth;
        }
        if (parser.getStartDrawable() != null) {
            if (parser.getStartDrawable() instanceof BitmapDrawable) {
                textStart += getBitmapDrawableWidth(
                        parser.getStartDrawable()) + parser.getPadding();
            } else {
                textStart += getTextSize() + parser.getPadding();
            }
        }
        if (parser.getTitleType() == TitleType.OUTSIDE_TOP
                || parser.getTitleType() == TitleType.INNER_TOP) {
            this.setGravity(Gravity.BOTTOM);
        } else {
            textStart += parser.getTitleWidth();
        }
        this.setPadding(textStart, parser.getPadding(), textEnd, parser.getPadding());
        this.setBackgroundColor(Color.TRANSPARENT);
    }


    //设置输入框的背景
    private void setBackground(Canvas canvas) {
        if (parser.getInputFieldBackground() == null) {
            parser.setInputFieldBackground(this.getBackground());
        }
        if (parser.getInputFieldBackground() == null) {
            return;
        }
        int start = 0;
        int top = 0;
        switch (parser.getTitleType()) {
            case TitleType.INNER_LEFT:
                break;
            case TitleType.OUTSIDE_LEFT:
                start = parser.getTitleWidth();
                break;
            case TitleType.INNER_TOP:
                break;
            case TitleType.OUTSIDE_TOP:
                top = getHeight() - inputHeight;
                break;
        }
        parser.getInputFieldBackground().setBounds(
                new Rect(start, top, getWidth(), getHeight()));
        parser.getInputFieldBackground().draw(canvas);

    }

    //画左边的title
    private void drawTitle(Canvas canvas) {
        if (IsEmpty.string(titleText)) {
            return;
        }
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(this.parser.getTitleColor());
        paint.setStrokeWidth(3);
        paint.setTextSize(this.parser.getTitleSize());
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        Rect targetRect = new Rect(0, 0, this.parser.getTitleWidth(), getHeight());


        int textWidth = (int) paint.measureText(titleText);
        int x = parser.getPadding();
        int y = parser.getPadding() + (parser.getTitleSize());
        if (parser.getTitleType() == TitleType.INNER_TOP
                || parser.getTitleType() == TitleType.OUTSIDE_TOP) {
            if (parser.getTitlePosition() == TitlePosition.TOP_RIGHT) {
                x = getWidth() - textWidth - parser.getPadding();
            }
        } else {
            switch (parser.getTitlePosition()) {
                case TitlePosition.TOP_LEFT:
                    break;
                case TitlePosition.TOP_CENTER:
                    x = (parser.getTitleWidth() - textWidth) / 2;
                    break;
                case TitlePosition.TOP_RIGHT:
                    x = parser.getTitleWidth() - textWidth - parser.getPadding();
                    break;
                case TitlePosition.CENTER_LEFT:
                    y = (targetRect.bottom - fontMetrics.bottom) / 2;
                    break;
                case TitlePosition.CENTER:
                    x = (parser.getTitleWidth() - textWidth) / 2;
                    y = (targetRect.bottom - fontMetrics.bottom) / 2;
                    break;
                case TitlePosition.CENTER_RIGHT:
                    x = parser.getTitleWidth() - textWidth - parser.getPadding();
                    y = (targetRect.bottom - fontMetrics.bottom) / 2;
                    break;
                case TitlePosition.BOTTOM_LEFT:
                    y = getHeight() - parser.getPadding() - parser.getTitleSize();
                    break;
                case TitlePosition.BOTTOM_CENTER:
                    x = (parser.getTitleWidth() - textWidth) / 2;
                    y = getHeight() - parser.getPadding() - parser.getTitleSize();
                    break;
                case TitlePosition.BOTTOM__RIGHT:
                    x = parser.getTitleWidth() - textWidth - parser.getPadding();
                    y = getHeight() - parser.getPadding() - parser.getTitleSize();
                    break;

            }
        }
        canvas.drawText(titleText, x, y, paint);

    }

    private void drawLeftDrawable(Canvas canvas) {
        if (parser.getStartDrawable() != null) {
            int width = getBitmapDrawableWidth(parser.getStartDrawable());
            int height = getBitmapDrawableHeight(parser.getStartDrawable());
            int start = parser.getPadding();
            int top = (getHeight() - height) / 2;
            if (parser.getTitleType() != TitleType.INNER_TOP
                    && parser.getTitleType() != TitleType.OUTSIDE_TOP) {
                start += parser.getTitleWidth();
            } else {
                top = (getHeight() - inputHeight) + ((inputHeight - height)) / 2;
            }
            parser.getStartDrawable().setBounds(start, top, start + width,
                    top + height);
            parser.getStartDrawable().draw(canvas);

        }
    }

    private void drawRightDrawable(Canvas canvas) {

        if (parser.getEndDrawable() != null) {
            int width = getBitmapDrawableWidth(parser.getEndDrawable());
            int height = getBitmapDrawableHeight(parser.getEndDrawable());
            int start = getWidth() - width - parser.getPadding();
            int top = (getHeight() - height) / 2;
            if (parser.getTitleType() == TitleType.INNER_TOP
                    || parser.getTitleType() == TitleType.OUTSIDE_TOP) {
                top = (getHeight() - inputHeight) + ((inputHeight - height)) / 2;
            }
            parser.getStartDrawable().setBounds(start, top, start + width,
                    top + height);
            parser.getStartDrawable().draw(canvas);
        }

        switch (parser.getRightDrawableType()) {
            case 0:
            case RightDrawable.CLEAR_AND_ERROR:
                if ((isFocused() && getText().toString().length() > 0)
                        || (!isFocused() && validateFailed)) {

                    Drawable drawable;
                    if (validateFailed) {
                        drawable = parser.getWrongDrawable();
                    } else {
                        drawable = parser.getClearDrawable();
                    }
                    int width = getBitmapDrawableWidth(drawable);
                    int height = getBitmapDrawableHeight(drawable);
                    int start;

                    if (parser.getEndDrawable() == null) {
                        start = getWidth() - width - parser.getPadding();
                    } else {
                        start = getWidth() - parser.getPadding() -
                                parser.getRightDrawableToDrawableSpace() - width
                                - getBitmapDrawableWidth(parser.getEndDrawable());
                    }
                    int top = (getHeight() - height) / 2;
                    if (parser.getTitleType() == TitleType.INNER_TOP
                            || parser.getTitleType() == TitleType.OUTSIDE_TOP) {
                        top = (getHeight() - inputHeight) + ((inputHeight - height) / 2);
                    }
                    drawable.setBounds(start, top, start + width,
                            top + height);
                    drawable.draw(canvas);
                }
                break;
            case RightDrawable.CLEAR_ONLY:
                break;
            case RightDrawable.ERROR_ONLY:
                break;
            case RightDrawable.NONE:
                break;

        }
    }

    private int getBitmapDrawableWidth(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable d = (BitmapDrawable) drawable;
            return d.getBitmap().getWidth();
        } else {
            return (int) getTextSize();
        }
    }

    private int getBitmapDrawableHeight(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable d = (BitmapDrawable) drawable;
            int maxHeight = getHeight() - 2 * parser.getPadding();
            if (d.getBitmap().getHeight() > maxHeight) {
                return maxHeight;
            }
            return d.getBitmap().getHeight();
        } else {
            return (int) getTextSize();
        }
    }

    /**
     * 获取属性配置，先判断自己的属性是否为空，如果为空则查询父控件中属性是否为空，如果父控件也为空，则用默认配置
     * 否则用父控件配置
     */
    private void getConfig(AttributeParser parentParser) {
        Field[] fields = parser.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().isAssignableFrom(int.class)) {
                try {
                    int value = (int) f.get(parser);
                    if (value != -1) {
                        continue;
                    }
                    if (value == -1 && parentParser != null) {
                        int parentValue = (int) f.get(parentParser);
                        if (parentValue == -1) {
                            f.set(parser, DefaultValue.class.getField(
                                    f.getName()).getInt(defaultValue));
                        } else {
                            f.setInt(parser, parentValue);
                        }
                    } else {
                        f.set(parser, DefaultValue.class.getField(
                                f.getName()).getInt(defaultValue));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (f.getType().isAssignableFrom(Drawable.class) ||
                    f.getType().isAssignableFrom(String.class)) {
                try {
                    Object value = f.get(parser);
                    if (value != null) {
                        continue;
                    }
                    if (value == null && parentParser != null) {
                        Object parentValue = f.get(parentParser);
                        if (parentValue == null) {
                            f.set(parser, DefaultValue.class.getField(
                                    f.getName()).get(defaultValue));
                        } else {
                            f.set(parser, parentValue);
                        }
                    } else {
                        f.set(parser, DefaultValue.class.getField(
                                f.getName()).get(defaultValue));
                    }
                } catch (Exception e) {
                }
            }
            if (f.getType().isAssignableFrom(boolean.class)) {
                try {
                    boolean value = f.getBoolean(parser);
                    if (value) {
                        continue;
                    }
                    if (parentParser != null) {
                        boolean parentValue = f.getBoolean(parentParser);
                        if (parentValue) {
                            f.setBoolean(parser, parentValue);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (this.haveFocus && !hasFocus) {
            validateEditText();
        } else {
            validateFailed = false;
        }
        this.haveFocus = hasFocus;
        Log.d("focus change:", this.haveFocus + "");
    }

    public void validateEditText() {
        //验证是否唯恐
        if (parser.isNotNull()) {
            if (IsEmpty.string(getText().toString())) {
                validateFailed = true;
                validateFailedMessage = parser.getNotNullErrorMessage();
            }
        }
        if (parser.getValidateRegexString() != null && !validateFailed) {
            Pattern pattern = Pattern.compile(parser.getValidateRegexString());
            Matcher isNum = pattern.matcher(getText().toString());
            if (isNum.matches()) {
                validateFailed = false;
            } else {
                validateFailed = true;
                validateFailedMessage = parser.getValidateErrorMessage();
            }
        }
        invalidate();
    }

    /**
     * 点击事件处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (parser.getRightDrawableType() == RightDrawable.CLEAR_AND_ERROR
                || parser.getRightDrawableType() == RightDrawable.ERROR_ONLY) {
            if (getText().toString().length() > 0 && event.getAction() == MotionEvent.ACTION_UP) {
                if (parser.getClearDrawable().getBounds().contains((int) event.getX(), (int) event.getY()) && isFocused()) {
                    this.setText("");
                }
            }
            if (validateFailed && event.getAction() == MotionEvent.ACTION_UP) {
                if (parser.getWrongDrawable().getBounds().contains((int) event.getX(), (int) event.getY())) {
                    Toast.makeText(getContext(), validateFailedMessage, Toast.LENGTH_SHORT).show();
                }
                super.onTouchEvent(event);
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


    public interface TitleType {
        int INNER_LEFT = 1;
        int OUTSIDE_LEFT = 2;
        int OUTSIDE_TOP = 3;
        int INNER_TOP = 4;

    }

    public interface TitlePosition {
        int TOP_LEFT = 1;
        int TOP_CENTER = 2;
        int TOP_RIGHT = 3;
        int CENTER_LEFT = 4;
        int CENTER = 5;
        int CENTER_RIGHT = 6;
        int BOTTOM_LEFT = 7;
        int BOTTOM_CENTER = 8;
        int BOTTOM__RIGHT = 9;
    }

    public interface RightDrawable {
        int CLEAR_AND_ERROR = 1;
        int ERROR_ONLY = 2;
        int CLEAR_ONLY = 3;
        int NONE = 4;
    }

    public interface TitleDrawablePosition {
        int LEFT = 1;
        int RIGHT = 2;
    }

    public interface TitleTagDrawablePosition {
        int LEFT_TOP = 1;
        int RIGHT_TOP = 2;
    }
}


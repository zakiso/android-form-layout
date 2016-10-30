package com.github.lazydeer.formlayout.fields;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.lazydeer.formlayout.R;


/**
 * Created by dzq on 2016/10/21.
 */

public class AttributeParser {

    private Context context;

    //默认title位置和类型
    private int titleType;
    private int titlePosition;
    //默认右边显示的图标
    private int rightDrawableType;
    //title的宽度
    private int titleWidth;
    private int titleColor;
    private int titleSize;

    private int padding;
    private int titleToInputSpace;
    private int rightDrawableToDrawableSpace;

    private Drawable endDrawable;
    private Drawable startDrawable;
    private Drawable clearDrawable;
    private Drawable wrongDrawable;

    private Drawable inputFieldBackground;

    //校验相关字段
    private boolean notNull;
    private String notNullErrorMessage;
    private String validateRegexString;
    private String validateErrorMessage;


    public Drawable titleDrawable;
    public Drawable titleTagDrawable;
    public int titleDrawablePosition;
    public int titleTagDrawablePosition;

    public int titleToTitleDrawableSpace;
    public int titleToTitleTagDrawableSpace;

    public int inputFieldMaxLines;

    public AttributeParser(Context context) {
        this.context = context;
    }


    public void parseAttribute(TypedArray array) {
        this.titleColor = array.getColor(R.styleable.InputField_titleTextColor, -1);
        this.titleWidth = (int) array.getDimension(R.styleable.InputField_titleAreaWidth, -1);
        this.titleSize = (int) array.getDimension(R.styleable.InputField_titleTextSize, -1);
        this.titleType = array.getInt(R.styleable.InputField_titleType, -1);
        this.titlePosition = array.getInt(R.styleable.InputField_titlePosition, -1);
        this.rightDrawableType = array.getInt(R.styleable.InputField_rightDrawableType, -1);
        this.titleToInputSpace = (int) array.getDimension(
                R.styleable.InputField_titleToInputSpace, -1);
        this.rightDrawableToDrawableSpace = (int) array.getDimension(
                R.styleable.InputField_rightDrawableToDrawableSpace, -1);

        this.titleDrawablePosition = array.getInt(R.styleable.InputField_titleDrawablePosition, -1);
        this.titleTagDrawablePosition = array.getInt(
                R.styleable.InputField_titleTagDrawablePosition, -1);

        this.titleToTitleDrawableSpace = (int) array.getDimension(
                R.styleable.InputField_titleToTitleDrawableSpace, -1);

        this.titleToTitleTagDrawableSpace = (int) array.getDimension(
                R.styleable.InputField_titleToTitleTagDrawableSpace, -1);


        this.notNull = array.getBoolean(R.styleable.InputField_validateNotNull, false);
        this.notNullErrorMessage = array.getString(R.styleable.InputField_nullErrorMessage);
        this.validateRegexString = array.getString(R.styleable.InputField_validateRegexString);
        this.validateErrorMessage = array.getString(R.styleable.InputField_validateErrorMessage);


    }

    //初始化输入框中需要用到的小图标
    public void parseDrawable(TypedArray array) {
        int endDrawableId = array.getResourceId(R.styleable.InputField_endDrawable, -1);
        int startDrawableId = array.getResourceId(R.styleable.InputField_startDrawable, -1);
        int clearDrawableId = array.getResourceId(R.styleable.InputField_clearDrawable, -1);
        int wrongDrawableId = array.getResourceId(R.styleable.InputField_wrongDrawable, -1);
        int titleDrawableId = array.getResourceId(R.styleable.InputField_titleDrawable, -1);
        int titleTagDrawableId = array.getResourceId(R.styleable.InputField_titleTagDrawable, -1);
        if (endDrawableId != -1) {
            this.endDrawable = ContextCompat.getDrawable(context, endDrawableId);
        }
        if (startDrawableId != -1) {
            this.startDrawable = ContextCompat.getDrawable(context, startDrawableId);
        }
        if (clearDrawableId != -1) {
            this.clearDrawable = ContextCompat.getDrawable(context, clearDrawableId);
        }
        if (wrongDrawableId != -1) {
            this.wrongDrawable = ContextCompat.getDrawable(context, wrongDrawableId);
        }
        if (titleDrawableId != -1) {
            this.titleDrawable = ContextCompat.getDrawable(context, titleDrawableId);
        }
        if (titleTagDrawableId != -1) {
            this.titleTagDrawable = ContextCompat.getDrawable(context, titleTagDrawableId);
        }
    }

    public Drawable getInputFieldBackground() {
        return inputFieldBackground;
    }

    public void setInputFieldBackground(Drawable inputFieldBackground) {
        this.inputFieldBackground = inputFieldBackground;
    }

    public int getTitleType() {
        return titleType;
    }

    public void setTitleType(int titleType) {
        this.titleType = titleType;
    }

    public int getTitlePosition() {
        return titlePosition;
    }

    public void setTitlePosition(int titlePosition) {
        this.titlePosition = titlePosition;
    }

    public int getRightDrawableType() {
        return rightDrawableType;
    }

    public void setRightDrawableType(int rightDrawableType) {
        this.rightDrawableType = rightDrawableType;
    }

    public int getTitleWidth() {
        return titleWidth;
    }

    public void setTitleWidth(int titleWidth) {
        this.titleWidth = titleWidth;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public Drawable getEndDrawable() {
        return endDrawable;
    }

    public void setEndDrawable(Drawable endDrawable) {
        this.endDrawable = endDrawable;
    }

    public Drawable getStartDrawable() {
        return startDrawable;
    }

    public void setStartDrawable(Drawable startDrawable) {
        this.startDrawable = startDrawable;
    }

    public Drawable getClearDrawable() {
        return clearDrawable;
    }

    public void setClearDrawable(Drawable clearDrawable) {
        this.clearDrawable = clearDrawable;
    }

    public Drawable getWrongDrawable() {
        return wrongDrawable;
    }

    public void setWrongDrawable(Drawable wrongDrawable) {
        this.wrongDrawable = wrongDrawable;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getTitleToInputSpace() {
        return titleToInputSpace;
    }

    public void setTitleToInputSpace(int titleToInputSpace) {
        this.titleToInputSpace = titleToInputSpace;
    }

    public int getRightDrawableToDrawableSpace() {
        return rightDrawableToDrawableSpace;
    }

    public void setRightDrawableToDrawableSpace(int rightDrawableToDrawableSpace) {
        this.rightDrawableToDrawableSpace = rightDrawableToDrawableSpace;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getNotNullErrorMessage() {
        return notNullErrorMessage;
    }

    public void setNotNullErrorMessage(String notNullErrorMessage) {
        this.notNullErrorMessage = notNullErrorMessage;
    }

    public String getValidateRegexString() {
        return validateRegexString;
    }

    public void setValidateRegexString(String validateRegexString) {
        this.validateRegexString = validateRegexString;
    }

    public String getValidateErrorMessage() {
        return validateErrorMessage;
    }

    public void setValidateErrorMessage(String validateErrorMessage) {
        this.validateErrorMessage = validateErrorMessage;
    }

    public Drawable getTitleDrawable() {
        return titleDrawable;
    }

    public void setTitleDrawable(Drawable titleDrawable) {
        this.titleDrawable = titleDrawable;
    }

    public Drawable getTitleTagDrawable() {
        return titleTagDrawable;
    }

    public void setTitleTagDrawable(Drawable titleTagDrawable) {
        this.titleTagDrawable = titleTagDrawable;
    }

    public int getTitleDrawablePosition() {
        return titleDrawablePosition;
    }

    public void setTitleDrawablePosition(int titleDrawablePosition) {
        this.titleDrawablePosition = titleDrawablePosition;
    }

    public int getTitleTagDrawablePosition() {
        return titleTagDrawablePosition;
    }

    public void setTitleTagDrawablePosition(int titleTagDrawablePosition) {
        this.titleTagDrawablePosition = titleTagDrawablePosition;
    }

    public int getTitleToTitleDrawableSpace() {
        return titleToTitleDrawableSpace;
    }

    public int getTitleToTitleTagDrawableSpace() {
        return titleToTitleTagDrawableSpace;
    }

    public int getInputFieldMaxLines() {
        return inputFieldMaxLines;
    }

    public void setInputFieldMaxLines(int inputFieldMaxLines) {
        this.inputFieldMaxLines = inputFieldMaxLines;
    }
}

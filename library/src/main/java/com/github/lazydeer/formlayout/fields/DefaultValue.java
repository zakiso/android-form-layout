package com.github.lazydeer.formlayout.fields;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.lazydeer.formlayout.FormUtils;
import com.github.lazydeer.formlayout.R;


/**
 * Created by dzq on 2016/10/21.
 */

public class DefaultValue {

    private Context context;
    //默认title位置和类型
    public int titleType = InputField.TitleType.OUTSIDE_LEFT;
    public int titlePosition = InputField.TitlePosition.TOP_LEFT;
    //默认右边显示的图标
    public int rightDrawableType = InputField.RightDrawable.CLEAR_AND_ERROR;
    //title的宽度
    public int titleWidth = 100;
    public int titleColor = Color.BLACK;
    public int titleSize = 20;

    public int padding = 10;
    public int titleToInputSpace = 5;
    public int rightDrawableToDrawableSpace = 5;

    //校验相关字段
    public boolean notNull = false;
    public String notNullErrorMessage;

    public String validateRegexString;
    public String validateErrorMessage;


    //默认drawable的id
    public Drawable endDrawable;
    public Drawable startDrawable;
    public Drawable clearDrawable;
    public Drawable wrongDrawable;
    public Drawable inputFieldBackground;

    public Drawable titleDrawable;
    public Drawable titleTagDrawable;
    public int titleDrawablePosition = InputField.TitleDrawablePosition.LEFT;
    public int titleTagDrawablePosition = InputField.TitleTagDrawablePosition.LEFT_TOP;

    public DefaultValue(Context context) {
        this.context = context;
        clearDrawable = ContextCompat.getDrawable(context, R.drawable.delete_tag);
        wrongDrawable = ContextCompat.getDrawable(context, R.drawable.wrong_tag);
        titleWidth = FormUtils.dip2px(context, titleWidth);
        titleSize = FormUtils.dip2px(context, titleSize);
        padding = FormUtils.dip2px(context, padding);
        titleToInputSpace = FormUtils.dip2px(context, titleToInputSpace);
        rightDrawableToDrawableSpace = FormUtils.dip2px(context, rightDrawableToDrawableSpace);
        notNullErrorMessage = context.getString(R.string.null_error_string);
        validateErrorMessage = context.getString(R.string.fill_error_string);
    }


}

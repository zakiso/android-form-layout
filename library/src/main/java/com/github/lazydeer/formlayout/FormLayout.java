package com.github.lazydeer.formlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.github.lazydeer.formlayout.fields.AttributeParser;


/**
 * Created by dzq on 2016/10/19.
 */

public class FormLayout extends LinearLayout {

    private Drawable inputFieldBackground;

    private AttributeParser parser;

    public FormLayout(Context context) {
        super(context);
    }

    public FormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        parser = new AttributeParser(getContext());
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.InputField);
        parser.parseDrawable(array);
        parser.parseAttribute(array);
        array.recycle();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FormLayout);
        int fieldResourceId = a.getResourceId(R.styleable.FormLayout_inputFieldBackground, -1);
        if (fieldResourceId != -1) {
            inputFieldBackground = ContextCompat.getDrawable(getContext(), fieldResourceId);
            parser.setInputFieldBackground(inputFieldBackground);
        }
        int inputFieldPadding = (int) a.getDimension(R.styleable.FormLayout_inputFieldPadding, -1);
        parser.setPadding(inputFieldPadding);
        a.recycle();
    }

    public AttributeParser getParser() {
        return parser;
    }
}

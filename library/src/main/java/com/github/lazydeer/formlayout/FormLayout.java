package com.github.lazydeer.formlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.lazydeer.formlayout.fields.AttributeParser;
import com.github.lazydeer.formlayout.fields.FieldValidateError;
import com.github.lazydeer.formlayout.fields.InputField;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dzq on 2016/10/19.
 */

public class FormLayout extends LinearLayout {

    private Drawable inputFieldBackground;

    private AttributeParser parser;

    private ValidateListener validateListener;

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
        setOrientation(VERTICAL);
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
        int inputMaxLines = (int) a.getInt(R.styleable.FormLayout_inputFieldMaxLines, -1);
        if (inputMaxLines != -1) {
            parser.setInputFieldMaxLines(inputMaxLines);
        }
        a.recycle();
    }

    public AttributeParser getParser() {
        return parser;
    }

    public List<FieldValidateError> validateInputFields() {
        List<FieldValidateError> list = new ArrayList();
        list = validateViewGroupSubView(this);
        if (validateListener != null) {
            if (list.size() == 0) {
                validateListener.onSuccess(this);
            } else {
                validateListener.onFailure(this, list);
            }
        }
        return list;
    }

    private List<FieldValidateError> validateViewGroupSubView(ViewGroup viewGroup) {
        List<FieldValidateError> list = new ArrayList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View subView = getChildAt(i);
            if (subView instanceof InputField) {
                FieldValidateError e = ((InputField) subView).validateEditText();
                if (!FormUtils.emptyObject(e)) {
                    list.add(e);
                }
            }
            if (subView instanceof ViewGroup) {
                List subErrors = validateViewGroupSubView((ViewGroup) subView);
                list.addAll(subErrors);
            }
        }
        return list;
    }

    public ValidateListener getValidateListener() {
        return validateListener;
    }

    public void setValidateListener(ValidateListener validateListener) {
        this.validateListener = validateListener;
    }

    public interface ValidateListener {
        void onSuccess(FormLayout formLayout);

        void onFailure(FormLayout formLayout, List<FieldValidateError> list);
    }
}

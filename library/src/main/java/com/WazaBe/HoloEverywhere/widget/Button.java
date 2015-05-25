package com.WazaBe.HoloEverywhere.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.WazaBe.HoloEverywhere.FontLoader;
import com.WazaBe.HoloEverywhere.R;
import com.WazaBe.HoloEverywhere.util.FontFamilyUtils;
import com.WazaBe.HoloEverywhere.util.FontFamilyUtils.FontFamilyExtension;

public class Button extends android.widget.Button implements FontFamilyExtension {
    private boolean allCaps = false;
    private CharSequence originalText;
    private BufferType originalType;

    private String fontFamily;

    public Button(Context context) {
        this(context, null);
    }

    public Button(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.TextView, defStyle, 0);
        if (a.hasValue(R.styleable.TextView_android_textAllCaps)) {
            allCaps = a.getBoolean(R.styleable.TextView_android_textAllCaps,
                    false);
        } else {
            allCaps = a.getBoolean(R.styleable.TextView_textAllCaps, false);
        }
        CharSequence text = null;
        if (a.hasValue(R.styleable.TextView_android_text)) {
            text = a.getText(R.styleable.TextView_android_text);
        }
        a.recycle();
        if (text != null) {
            setText(text);
        }
        fontFamily = FontFamilyUtils.getFontFamily(context, attrs,
                android.R.attr.buttonStyle, defStyle);
        if (!TextUtils.isEmpty(fontFamily)) {
            FontLoader.apply(this);
        }
    }

    @Override
    public void setTextAppearance(Context context, int resid) {
        super.setTextAppearance(context, resid);
        String newFontFamily = FontFamilyUtils.getFontFamily(context, resid);
        if (!TextUtils.equals(newFontFamily, fontFamily)) {
            this.fontFamily = newFontFamily;
            FontLoader.apply(this);
        }
    }

    @Override
    public void setCustomTypeface(Typeface customTypeface) {
        super.setTypeface(customTypeface);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
        FontFamilyUtils.onSetTypeface(this, tf);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
        FontFamilyUtils.onSetTypeface(this, tf, style);
    }

    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    @Override
    @SuppressLint("NewApi")
    public void dispatchDisplayHint(int hint) {
        onDisplayHint(hint);
    }

    public boolean isAllCaps() {
        return allCaps;
    }

    @Override
    @SuppressLint("NewApi")
    protected void onDisplayHint(int hint) {
        if (VERSION.SDK_INT >= 8) {
            super.onDisplayHint(hint);
        }
    }

    @Override
    public void setAllCaps(boolean allCaps) {
        this.allCaps = allCaps;
        updateTextState();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        originalType = type;
        updateTextState();
    }

    private void updateTextState() {
        super.setText(allCaps ? originalText.toString().toUpperCase()
                : originalText, originalType);
    }
}

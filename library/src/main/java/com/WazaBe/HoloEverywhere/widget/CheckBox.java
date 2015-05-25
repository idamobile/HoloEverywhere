package com.WazaBe.HoloEverywhere.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.WazaBe.HoloEverywhere.FontLoader;
import com.WazaBe.HoloEverywhere.util.FontFamilyUtils;
import com.WazaBe.HoloEverywhere.util.FontFamilyUtils.FontFamilyExtension;

public class CheckBox extends android.widget.CheckBox implements FontFamilyExtension {

    private String fontFamily;

    public CheckBox(Context context) {
        this(context, null);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.checkboxStyle);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        fontFamily = FontFamilyUtils.getFontFamily(context, attrs,
                android.R.attr.checkboxStyle, defStyle);
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

}

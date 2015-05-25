package com.WazaBe.HoloEverywhere.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.WazaBe.HoloEverywhere.FontLoader;
import com.WazaBe.HoloEverywhere.R;

public class FontFamilyUtils {

    public interface FontFamilyExtension {

        void setCustomTypeface(Typeface customTypeface);

        String getFontFamily();

    }

    public static String getFontFamily(Context context, AttributeSet attrs,
            int defAttr, int defStyle) {
        String fontFamily = null;
        int[] attrsArray = new int[] {
                android.R.attr.textAppearance, // 0
        };
        TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray, defAttr, defStyle);
        int ap = ta.getResourceId(0, -1);
        TypedArray appearance = null;
        if (ap != -1) {
            appearance = context.obtainStyledAttributes(ap, R.styleable.TextAppearance);
        }
        if (appearance != null) {
            int n = appearance.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = appearance.getIndex(i);
                if (attr == R.styleable.TextAppearance_android_fontFamily) {
                    fontFamily = appearance.getString(attr);
                }
            }
            appearance.recycle();
        }
        ta.recycle();

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextView, defStyle, 0);
        if (a.hasValue(R.styleable.TextView_android_fontFamily)) {
            fontFamily = a.getString(R.styleable.TextView_android_fontFamily);
        }
        a.recycle();
        return fontFamily;
    }

    public static String getFontFamily(Context context, int textAppearanceResid) {
        String fontFamily = null;
        TypedArray appearance =
                context.obtainStyledAttributes(textAppearanceResid, R.styleable.TextAppearance);
        if (appearance != null) {
            int n = appearance.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = appearance.getIndex(i);
                if (attr == R.styleable.TextAppearance_android_fontFamily) {
                    fontFamily = appearance.getString(attr);
                }
            }
            appearance.recycle();
        }
        return fontFamily;
    }

    public static void onSetTypeface(TextView view, Typeface typeface) {
        FontLoader.apply(view);
    }

    public static void onSetTypeface(TextView view, Typeface typeface, int style) {
        FontLoader.apply(view);
    }
}

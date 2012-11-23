package com.WazaBe.HoloEverywhere.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;

import com.WazaBe.HoloEverywhere.R;

public class TextView extends android.widget.TextView {
    private boolean allCaps = false;
    private CharSequence originalText;
    private BufferType originalType;
    private String fontFamily;

    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        int[] attrsArray = new int[] {
                android.R.attr.textAppearance, // 0
        };
        TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
        int ap = ta.getResourceId(0, -1);
        TypedArray appearance = null;
        if (ap != -1) {
            appearance = context.obtainStyledAttributes(ap, R.styleable.TextAppearance);
        }
        if (appearance != null) {
            int n = appearance.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = appearance.getIndex(i);
                switch (attr) {
                case R.styleable.TextAppearance_android_fontFamily:
                    fontFamily = appearance.getString(attr);
                    break;
                }
            }
            appearance.recycle();
        }
        ta.recycle();

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

        if (a.hasValue(R.styleable.TextView_android_fontFamily)) {
            fontFamily = a.getString(R.styleable.TextView_android_fontFamily);
        }
        a.recycle();
        if (text != null) {
            setText(text);
        }
    }

    @Override
    @SuppressLint("NewApi")
    public void dispatchDisplayHint(int hint) {
        onDisplayHint(hint);
    }

    public boolean isAllCaps() {
        return allCaps;
    }

    public String getFontFamily() {
        return fontFamily;
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

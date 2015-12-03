package com.algorepublic.matzomatch.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ahmad on 6/26/15.
 */
public class CusFntTextView extends TextView {

    public CusFntTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CusFntTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusFntTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "BenchNine-Bold.ttf");
            setTypeface(tf);
        }
    }
}
package com.example.liujunyang.view_pratice.course10;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.liujunyang.view_pratice.R;
import com.example.liujunyang.view_pratice.Utils;

/**
 * Created by @author liujunyang
 * on 2018/12/10
 */
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float TEXT_SIZE = Utils.dpToPixel(12);
    private static final float TEXT_MARGIN = Utils.dpToPixel(8);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dpToPixel(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dpToPixel(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dpToPixel(16);
    Rect backgroundPadding = new Rect();
    boolean floatingLabelShown;
    float floatingLabelFraction;
    boolean useFloatingLabel;
    ObjectAnimator animator;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel,false);

    }
    void init(Context context, AttributeSet attrs) {
        paint.setTextSize(TEXT_SIZE);
        getBackground().getPadding(backgroundPadding);
        setPadding(getPaddingLeft(),  (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (floatingLabelShown && TextUtils.isEmpty(s)) {
                    floatingLabelShown = false;
                    getAnimator().reverse();
                } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                    floatingLabelShown = true;
                    getAnimator().start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (0xff*floatingLabelFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET+ extraOffset , paint);
    }

    private ObjectAnimator getAnimator(){


        if( animator == null){
            animator = ObjectAnimator.ofFloat(this,"floatingLabelFraction",0,1);
        }
        return animator;
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }
}

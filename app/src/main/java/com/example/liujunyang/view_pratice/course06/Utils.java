package com.example.liujunyang.view_pratice.course06;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by @author liujunyang
 * on 2018/11/8
 */
public class Utils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}

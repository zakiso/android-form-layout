package com.github.lazydeer.formlayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.List;
import java.util.Set;

/**
 * Created by dzq on 2016/10/20.
 */

public class FormUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 功能描述：获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidthPixel(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 功能描述：获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeightPixel(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static boolean emptyList(List object) {
        if (null == object || object.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断所有的list是否都为空或者null
    public static boolean emptyList(List... objects) {
        boolean allNull = true;
        for (List obj : objects) {
            if (null != obj && obj.size() > 0) {
                allNull = false;
                break;
            }
        }
        return allNull;
    }

    public static boolean emptyList(Set object) {
        if (null == object || object.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean emptyString(String object) {
        if (null == object || "".equals(object)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean emptyString(String... objects) {
        boolean allEmpty = true;
        for (String s : objects) {
            if (null == s || "".equals(s)) {
                allEmpty = false;
                break;
            }
        }
        return allEmpty;
    }

    public static boolean stringWithSomeEmpty(String... objects) {
        boolean someEmpty = false;
        for (String s : objects) {
            if (null == s || "".equals(s)) {
                someEmpty = true;
                break;
            }
        }
        return someEmpty;
    }

    public static int whereIsEmptyStr(String... objects) {
        int emptyIndex = -1;
        for (int i = 0; i < objects.length; i++) {
            if (null == objects[i] || "".equals(objects[i])) {
                emptyIndex = i;
                break;
            }
        }
        return emptyIndex;
    }

    public static boolean emptyObject(Object object) {
        if (null == object) {
            return true;
        } else {
            return false;
        }
    }

}

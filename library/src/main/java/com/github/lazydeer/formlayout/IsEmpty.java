package com.github.lazydeer.formlayout;

import java.util.List;
import java.util.Set;

/**
 * Created by dzq on 16/7/21.
 */
public class IsEmpty {
    public static boolean list(List object) {
        if (null == object || object.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断所有的list是否都为空或者null
    public static boolean list(List... objects) {
        boolean allNull = true;
        for (List obj : objects) {
            if (null != obj && obj.size() > 0) {
                allNull = false;
                break;
            }
        }
        return allNull;
    }

    public static boolean list(Set object) {
        if (null == object || object.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean string(String object) {
        if (null == object || "".equals(object)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean string(String... objects) {
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

    public static boolean object(Object object) {
        if (null == object) {
            return true;
        } else {
            return false;
        }
    }
}

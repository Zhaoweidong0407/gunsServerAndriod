package cn.stylefeng.guns.core.util;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Iterator;
import java.util.Map;

public class ObjUtil {

    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof CharSequence) {
            return StrUtil.isEmpty((CharSequence)obj);
        } else if (obj instanceof Map) {
            return MapUtil.isEmpty((Map)obj);
        } else if (obj instanceof Iterable) {
            return IterUtil.isEmpty((Iterable)obj);
        } else if (obj instanceof Iterator) {
            return IterUtil.isEmpty((Iterator)obj);
        } else {
            return ArrayUtil.isArray(obj) ? ArrayUtil.isEmpty(obj) : false;
        }
    }

    public static String extName(String fileName) {
        if (fileName == null) {
            return null;
        } else {
            int index = fileName.lastIndexOf(".");
            if (index == -1) {
                return "";
            } else {
                String ext = fileName.substring(index + 1);
                return StrUtil.containsAny(ext, new char[]{'/', '\\'}) ? "" : ext;
            }
        }
    }
}

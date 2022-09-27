package cn.stylefeng.guns.core.util;

public class FIleNameUtil {
    public static String getFileSuffix(String fileWholeName) {
        if ("".equals(fileWholeName) || null == fileWholeName) {
            return "none";
        } else {
            int lastIndexOf = fileWholeName.lastIndexOf(".");
            return fileWholeName.substring(0,lastIndexOf);
        }
    }
}

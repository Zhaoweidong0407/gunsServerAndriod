package cn.stylefeng.guns.core.util;

import java.util.UUID;

public class PkeyGenerator {
    private PkeyGenerator()
    {
    }
    /**
     * 生成时间戳主键
     */
    private static int generateCount = 0;
    public static synchronized String getUniqueString(String key)
    {
        if(generateCount > 999)
            generateCount = 0;
        String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);
        generateCount++;
        return key+uniqueNumber;
    }

}

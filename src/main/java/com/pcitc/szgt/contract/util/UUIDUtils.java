package com.pcitc.szgt.contract.util;

import java.util.StringTokenizer;
import java.util.UUID;

public class UUIDUtils {

    public static String getUUID()
    {
        String result = "";
        UUID uuid = UUID.randomUUID();
        String temp = uuid.toString();
        StringTokenizer token = new StringTokenizer(temp, "-");
        while (token.hasMoreTokens()) {
            result = result + token.nextToken();
        }
        return result;
    }
}

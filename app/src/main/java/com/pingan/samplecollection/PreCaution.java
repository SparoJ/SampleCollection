package com.pingan.samplecollection;

/**
 * @author apple
 * @Description :
 * @date 17/2/24  下午4:38
 */

public class PreCaution {

    public static <T>T checkNull(T obj, String msg) {
        if(null != obj) {
            return obj;
        }
        if(null == msg) {
            throw new NullPointerException("obj is null");
        } else {
            throw new NullPointerException(msg);
        }

    }
}

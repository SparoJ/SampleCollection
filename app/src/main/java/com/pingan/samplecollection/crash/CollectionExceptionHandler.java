package com.pingan.samplecollection.crash;

import android.util.Log;

import com.pingan.samplecollection.BuildConfig;

import static android.content.ContentValues.TAG;


/**
 * @author apple
 * @Description :
 * @date 17/2/16  上午10:06
 */

public class CollectionExceptionHandler implements Thread.UncaughtExceptionHandler {

    public static class InnerHandler {
        public static final CollectionExceptionHandler INSTANCE = new CollectionExceptionHandler();
    }
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private CollectionExceptionHandler() {}

    public static CollectionExceptionHandler getInstance() {
        return InnerHandler.INSTANCE;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        StackTraceElement[] errorTrace = e.getStackTrace();
        String report = e.toString();
        StringBuilder builder = new StringBuilder(report);
        builder.append("\n\n");
        builder.append("------------ stack trace ------------- \n\n");
        for (int i = 0; i < errorTrace.length; i++) {
            builder.append(errorTrace[i].toString()+"\n");
        }
        builder.append("-----------------------------------\n\n");
        if(BuildConfig.DEBUG) {
            //输出到 console
            Log.e("uncaughtException ", builder.toString());
        }
        //输出到其他平台 TODO
        Log.e("uncaughtException", "@@@uncaughtException:@@ " );
        e.printStackTrace();
    }
}

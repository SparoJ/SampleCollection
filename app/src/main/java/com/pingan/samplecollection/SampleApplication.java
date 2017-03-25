package com.pingan.samplecollection;

import android.app.Application;

import com.pingan.samplecollection.crash.CollectionExceptionHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author apple
 * @Description :
 * @date 17/2/13  上午9:11
 */

public class SampleApplication extends Application {


    private static SampleApplication mApplication;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
        //初始化LeakCanary工具
        mRefWatcher = LeakCanary.install(this);
        // init crash handler
//       Thread.setDefaultUncaughtExceptionHandler(new CollectionExceptionHandler());
        CollectionExceptionHandler.getInstance().init();
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static SampleApplication getApplication() {
        return mApplication;
    }

}

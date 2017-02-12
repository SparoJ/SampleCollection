package com.pingan.samplecollection.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.trello.rxlifecycle.components.RxActivity;


/**
 * @author apple
 * @Description :
 * @date 17/2/12  下午7:54
 */

public abstract class BaseActivity extends RxActivity { //继承RxActivity 完成生命周期的绑定


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(initPagedLayout() > 0) {
            setContentView(initPagedLayout());
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initViewListener();
        process();
    }

    protected abstract void process();

    protected abstract void initViewListener();

    protected abstract void initView();

    protected abstract int initPagedLayout();

    protected <T extends View> T $(int resId) {
        if(resId <= 0) {
            throw new IllegalArgumentException("passed an illegal or inappropriate argument");
        }
        return (T)super.findViewById(resId);
    }
}


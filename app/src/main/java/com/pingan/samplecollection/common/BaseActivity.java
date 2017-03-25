package com.pingan.samplecollection.common;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.pingan.samplecollection.ActivityStackManager;
import com.pingan.samplecollection.SampleApplication;
import com.trello.rxlifecycle.components.RxActivity;


/**
 * @author apple
 * @Description :
 * @date 17/2/12  下午7:54
 */

public abstract class BaseActivity extends RxActivity { //继承RxActivity 完成生命周期的绑定

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(initPagedLayout() > 0) {
            setContentView(initPagedLayout());
        }
        initWindows();
        ActivityStackManager.getInstance().addActivity(this);
    }

    protected void initWindows() {
        Window window = getWindow();
        int color = getResources().getColor(android.R.color.holo_blue_bright);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            int barColor = getSelfDefineStatusBarColor();
            if( barColor > 0) {
                color = barColor;
            }
            window.setStatusBarColor(color);
            //设置导航栏颜色
            window.setNavigationBarColor(color);
            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            //给statusbar着色
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
            int barColor = getSelfDefineStatusBarColor();
            if( barColor > 0) {
                color = barColor;
            }
            view.setBackgroundColor(color);
            contentView.addView(view);  //是addView 而不是 addView(,0)  因为 contentView 是FrameLayout
        }
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected int getSelfDefineStatusBarColor() {
        return 0;
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

    protected void startActivityWithoutExtras(Class<?> clazz) {
        startActivityWithExtras(clazz, null);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle bundle) {
        if(null == clazz) {
            return;
        }
        Intent intent = new Intent(this, clazz);
        if(null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStackManager.getInstance().setTopActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().finishActivity(this);
        // 初始化 RefWatcher
        SampleApplication.getApplication().getRefWatcher().watch(this);
    }
}


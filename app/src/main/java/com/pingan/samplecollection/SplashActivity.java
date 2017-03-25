package com.pingan.samplecollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.pingan.samplecollection.common.BaseActivity;
import com.pingan.samplecollection.main.MainActivity;
import com.pingan.samplecollection.ui.widget.CountTimeProgressView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * @author apple
 * @Description :
 * @date 17/2/12  下午7:50
 */

public class SplashActivity extends BaseActivity {

    private CountTimeProgressView mProgressView;
    private String mAClass;
    private String mPayload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PushManager.getInstance().initialize(this.getApplicationContext(), SamplePushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), SampleIntentService.class);
        String clientid = PushManager.getInstance().getClientid(this.getApplicationContext());
        Log.e("shandahua", "onCreate: push -->clientid-->>"+clientid);
        Toast.makeText(this,clientid,Toast.LENGTH_LONG).show();

//        Intent intent = getIntent();
//        if ((intent.getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
//                != Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) {
//
//            intent.setClass(this, MainActivity.class);
//            startActivity(intent);
//        }
//        Intent intent = getIntent();
//        boolean isNotification = intent.getBooleanExtra("isNotification", false);
//        if(isNotification) {
//            mAClass = intent.getStringExtra("class");
//            mPayload = intent.getStringExtra("payload");
//        }
//        PushState.getObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<PushBean>() {
//            @Override
//            public void call(PushBean pushBean) {
//                if (pushBean.isNotification) {
////                    Intent intent = new Intent();
////
////                    intent.putExtra("push", pushBean);
////                                intent.setClass(SplashActivity.this, MainActivity.class);
////            startActivity(intent);
//                    SplashActivity.this.finish();
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Log.e(TAG, "onResume: "+intent);
    }

    @Override
    protected void process() {
        mProgressView.addOnEndListener(new CountTimeProgressView.OnEndListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("class",mAClass);
                bundle.putString("payload",mPayload);
                intent.putExtras(bundle);

                startActivityWithExtras(MainActivity.class, bundle);
                finish();
            }
//            gradlew bintrayUpload

            @Override
            public void onClick(long overageTime) {

                startActivityWithoutExtras(MainActivity.class);
                finish();
            }

        });
        mProgressView.startCountTimeAnimation();
    }

    @Override
    protected void initViewListener() {

    }

    @Override
    protected void initView() {
        mProgressView = $(R.id.countTimeProgressView);
    }

    @Override
    protected int initPagedLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initWindows() {
//        super.initWindows();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: "+SplashActivity.this );
        if (mProgressView != null && mProgressView.isRunning()) {
            mProgressView.cancelCountTimeAnimation();
        }
    }
}

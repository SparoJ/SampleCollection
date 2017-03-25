package com.pingan.samplecollection.main;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pingan.samplecollection.PushBean;
import com.pingan.samplecollection.PushMsgActivity;
import com.pingan.samplecollection.PushState;
import com.pingan.samplecollection.R;
import com.pingan.samplecollection.SplashActivity;
import com.pingan.samplecollection.common.BaseActivity;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.igexin.sdk.GTServiceManager.context;


public class MainActivity extends BaseActivity {

    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        Log.e(TAG, "onCreate: MainActivity--->>>"+intent );
//        String aClass = intent.getStringExtra("class");
//        String payload = intent.getStringExtra("payload");
//        if(null != aClass && !TextUtils.isEmpty(payload)) {
//            Intent newIntent = new Intent();
//            newIntent.putExtra("payload", payload);
//            try {
//                newIntent.setClass(this,Class.forName(aClass));
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            startActivity(intent);
//        }

    }

    @Override
    protected void process() {
        Log.e(TAG, "onPost: "+"---->>>>>MainActivity");
//        Subscription push = PushState.getObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<PushBean>() {
//                    @Override
//                    public void call(PushBean pushBean) {
//                        if (pushBean.isNotification) {
//                            Intent intent = new Intent();
//
//                            intent.putExtra("push", pushBean);
//                            intent.setClass(MainActivity.this, PushMsgActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                });

//        Intent intent;
//        if((intent = getIntent())!=null) {
//            PushBean pushBean = (PushBean) intent.getSerializableExtra("push");
//            if(null != pushBean) {
//                intent.setClass(MainActivity.this, PushMsgActivity.class);
//                intent.putExtra("push",pushBean);
//                startActivity(intent);
//            }
    }



//        PushState.getObservable()
//                .throttleFirst(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<PushBean>() {
//                    @Override
//                    public void call(PushBean pushBean) {
//                        if(pushBean.isNotification) {
//
//                            Intent intent = new Intent(MainActivity.this, PushMsgActivity.class);
//
//                            Log.e(TAG, "call: "+Thread.currentThread().getName() + "===>>>>"+System.currentTimeMillis() );
//                        }
//                    }
//                });
//    }

    @Override
    protected void initViewListener() {

    }

    @Override
    protected void initView() {
        mBtn = (Button) findViewById(R.id.btn_xutil_test);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancelAll();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Test.getText(new TestCallback() {
//            @Override
//            public void setText(String text) {
////                try {
//////                   Thread.sleep(2000); ui线程修改bug
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                mBtn.setText(text);
//                Log.e("shandahua", "Mainactivity --- setText: ====>>>"+ Thread.currentThread().getName() );
//            }
//        });
    }

    //  另外当前这种调用方式也会
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Test.getText(new TestCallback() {
//            @Override
//            public void setText(String text) {
////                try {
////                    Thread.sleep(2000); ui线程修改bug  在onResume中会 这里肯定会
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                mBtn.setText(text);
//                Log.e("shandahua", "Mainactivity --- setText: ====>>>"+ Thread.currentThread().getName() );
//            }
//        });
//    }

    @Override
    protected int initPagedLayout() {
        return R.layout.activity_main;
    }

    public interface TestCallback {
        void setText(String text);
    }
}

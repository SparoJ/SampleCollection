package com.pingan.samplecollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pingan.samplecollection.common.BaseActivity;


/**
 * @author apple
 * @Description :
 * @date 17/2/24  下午4:36
 */

public class PushMsgActivity extends BaseActivity {

    private Button mBtn;
    private String payload;
    private boolean isNotification;
    private TextView mText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: "+PushMsgActivity.this );
    }

    @Override
    protected void process() {
        Intent intent = getIntent();
        if(null != intent) {
            PushBean push = (PushBean) intent.getSerializableExtra("push");
            payload = push.payload;
            isNotification = push.isNotification;
        }
        Log.e(TAG, "process: ==payload==>>>>"+payload );
        mBtn.setText(String.format("payload+%s+isNotification+%s",payload,isNotification));
        mText.setVisibility(isNotification? View.VISIBLE:View.GONE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent: " );
        if(null != intent) {
            PushBean push = (PushBean) intent.getSerializableExtra("push");
            payload = push.payload;
            isNotification = push.isNotification;
        }
        Log.e(TAG, "onNewIntent: －－payload－》"+payload);
        mBtn.setText(String.format("payload+%s+isNotification+%s",payload,isNotification));
        mText.setVisibility(isNotification? View.VISIBLE:View.GONE);
    }

    @Override
    protected void initViewListener() {

    }

    @Override
    protected void initView() {
        mBtn = $(R.id.btn_xutil_test);
        mText = $(R.id.msg_text);
    }

    @Override
    protected int initPagedLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ---->>>PushMsgActivity--->>>"+System.currentTimeMillis() );
    }
}

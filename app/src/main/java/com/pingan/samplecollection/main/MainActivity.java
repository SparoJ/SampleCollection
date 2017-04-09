package com.pingan.samplecollection.main;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.pingan.samplecollection.PushBean;
import com.pingan.samplecollection.PushMsgActivity;
import com.pingan.samplecollection.PushState;
import com.pingan.samplecollection.R;
import com.pingan.samplecollection.SoActivity;
import com.pingan.samplecollection.SplashActivity;
import com.pingan.samplecollection.common.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.igexin.sdk.GTServiceManager.context;


public class MainActivity extends BaseActivity {

    private Button mBtn;
    private Button mBtnMsg;
    private TextView mTvText;

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

        String string = "{\n" +
                "  \"work_duration\": \"3个工作日出货，7天无理由退换\",\n" +
                "  \"pictures\": [\n" +
                "    \"http://img.yumaozhubao.com/images/00087892.jpg\",\n" +
                "    \"http://img.yumaozhubao.com/images/00087893.jpg\",\n" +
                "    \"http://img.yumaozhubao.com/images/00087894.jpg\",\n" +
                "    \"http://img.yumaozhubao.com/images/00087895.jpg\"\n" +
                "  ],\n" +
                "  \"thumb\": \"http://img.yumaozhubao.com/images/00087892_thumb.jpg\",\n" +
                "  \"id\": 1125,\n" +
                "  \"categories\": [\n" +
                "    {\n" +
                "      \"id\": 693,\n" +
                "      \"name\": \"吊坠\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 528,\n" +
                "      \"name\": \"银饰\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"vendor\": {\n" +
                "    \"logo\": \"http://img.yumaozhubao.com/images/00090911.png\",\n" +
                "    \"product_amount\": 3479,\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"玉猫平台\",\n" +
                "    \"address\": \"深圳市罗湖区田贝四路水贝万山A座4006\",\n" +
                "    \"vendor_type\": 2\n" +
                "  },\n" +
                "  \"manufacturers\": [],\n" +
                "  \"name\": \"S925蛋形五角星石榴吊坠\",\n" +
                "  \"short_description\": \"(73970)S925蛋形五角星石榴吊坠，材质：S925，总重约2.9g，主石：石榴石，尺寸约4*6mm\",\n" +
                "  \"sku\": \"CB1011135\",\n" +
                "  \"stock_quantity\": 1,\n" +
                "  \"price\": 559,\n" +
                "  \"product_cost\": 208,\n" +
                "  \"actual_cost_price\": 208,\n" +
                "  \"created_on_utc\": \"2017-03-02T07:12:22+0000\"\n" +
                "}";

//        formatJson(string);
    }

    private void formatJson(String string) {
        Gson gson = new Gson();
        final TestResponse response = gson.fromJson(string, TestResponse.class);
        Log.e(TAG, "formatJson: "+response.getName()+"--->>>"+response.getActual_cost_price()+"---->>>>"+response.getCategories());
String url = "https://test-dist.yumao168.com/api/products";
        OkHttpClient client = new OkHttpClient();
//        OkHttpClient.Builder timeout = client.newBuilder().connectTimeout(200000, TimeUnit.SECONDS);
        Request.Builder builder = new Request.Builder();
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
//        urlBuilder.addQueryParameter("pid","1124");
//        Request request = builder.url(urlBuilder.build()).build();
        Request request = new Request.Builder()
                .url("https://test-dist.yumao168.com/api/categories?cid=21")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.toString() );


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: --->>response.body.string>>>\n\n"+response.body().string());
                formatArrayResponse(response.body().string());
            }
        });

    }

    private void formatArrayResponse(String string) {
// array json 解析
        List<ArrayBean> list = new ArrayList<>();
        Gson gson = new Gson();
        list = gson.fromJson(string, new TypeToken<List<ArrayBean>>() {
        }.getType());

        Log.e(TAG, "formatArrayResponse: "+ list.get(0).getName());

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

        mBtnMsg = (Button) findViewById(R.id.msg_text);
        mBtnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SoActivity.class));
            }
        });

        mTvText = (TextView) findViewById(R.id.tv_text);
        String content = "您的客户在想啥申请的贷款（申请编号O10000000002466）已通过终审，审批金额为1000000.00元，期限为5个月，贷款将于近日发放";
        mTvText.setText(content);
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

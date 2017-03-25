package com.pingan.samplecollection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.pingan.samplecollection.main.MainActivity;
import com.squareup.haha.perflib.Main;

import org.json.JSONException;

/**
 * @author apple
 * @Description :
 * @date 17/2/23  下午10:51
 */

public class SampleIntentService extends GTIntentService {

    private PushBean mBean;
    private SparseArray<PushBean> mArray = new SparseArray<>();

    public SampleIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.e(TAG, "onReceiveServicePid: pid===>"+pid );  // service pid
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.e(TAG, "onReceiveMessageData: msg-->>>"+new String(msg.getPayload()+"===THread==="+ Thread.currentThread().getId()));  // 消息接收
        Log.e(TAG, "onReceiveMessageData: isRunningBackground--->>>");
        String payload  = new String(msg.getPayload());
        Log.e(TAG, "onReceiveMessageData:==payload=>>> "+payload+"threadId-->"+Thread.currentThread().getId());
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.pingan.samplecollection");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//        |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.putExtra("isNotification",true);
//        intent.putExtra("payload",payload);
        mBean = new PushBean();
        mBean.isNotification = true;
        mBean.payload = payload;
//        PushState.publish(bean);
//        intent.putExtra("class","com.pingan.samplecollection.PushMsgActivity");
//        startActivity(intent);

        int id = msg.hashCode(); // id 必须不同 否则推送通知会被覆盖
        mArray.put(id, mBean); //存储推送信息 后续展示对应条目推送的信息
        showNotification(context,id,"push test", payload);
    }
//
//    @Override
//    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
//        Log.e(TAG, "onReceiveMessageData: msg-->>>"+new String(msg.getPayload()+"===THread==="+ Thread.currentThread().getId()));  // 消息接收
//        Log.e(TAG, "onReceiveMessageData: isRunningBackground--->>>");
//        String payload  = new String(msg.getPayload());
//        Log.e(TAG, "onReceiveMessageData:==payload=>>> "+payload+"threadId-->"+Thread.currentThread().getId());
////        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.pingan.samplecollection");
//        Intent pushIntent = new Intent(context, PushMsgActivity.class);
//        pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//        |Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
////        intent.putExtra("isNotification",true);
////        intent.putExtra("payload",payload);
//        mBean = new PushBean();
//        mBean.isNotification = true;
//        mBean.payload = payload;
//        pushIntent.putExtra("push", mBean);
//
//        Intent mainIntent = new Intent(context, MainActivity.class);
//        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        PushState.publish(bean);
////        intent.putExtra("class","com.pingan.samplecollection.PushMsgActivity");
////        startActivity(intent);
//
//    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.e(TAG, "onReceiveOnlineState: online-->>bo>>"+online );
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG, "onReceiveCommandResult: "+ cmdMessage );

    }

    private void showNotification(Context context, int id, String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.push);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(context, PushMsgActivity.class);
        resultIntent.putExtra("push", mArray.get(id));
//        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent[] intents = new Intent[]{intent, resultIntent};
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent contentIntent = getDefalutIntent(resultIntent, id);
        PendingIntent contentIntent = PendingIntent.getActivities(context, id, intents, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }


}

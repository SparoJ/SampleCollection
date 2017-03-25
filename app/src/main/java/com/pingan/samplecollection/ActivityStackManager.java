package com.pingan.samplecollection;

/**
 * @author apple
 * @Description :
 * @date 17/2/24  下午4:43
 */

import android.app.Activity;
import android.util.Log;

import java.util.Iterator;
import java.util.Stack;

/**
 * manage the activity
 */
public class ActivityStackManager {

    private ActivityStackManager(){}
    private Stack<Activity> activities;
    private String TAG = this.getClass().getSimpleName();


    public static class InnerStackInstance {
        private static final ActivityStackManager INSTANCE = new ActivityStackManager();
    }

    public static synchronized ActivityStackManager getInstance() {
        return InnerStackInstance.INSTANCE;
    }

    public void  addActivity(Activity activity) {
        if(activities == null) {
            activities = new Stack<>();
        }
        if(activities.contains(activity)) {
            activities.remove(activity);
        }
        activities.push(activity); //add activity to stack
        printActivityStack();
    }

    public void finishActivity(Activity activity) {
        if(null != activities && activities.size()>0) {
            activities.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> clzz) {
        if(null != activities && activities.size()>0) {
          Activity activity;
            //遍历stack 集合
            Iterator<Activity> iterator = activities.iterator();
            while(iterator.hasNext()) {
                activity = iterator.next();
                if(activity.getClass().equals(clzz)) {
                    iterator.remove();
                    activity.finish();
                }
            }
        }
    }

    public boolean isContainActivity(Class<?> clzz) {
        if(null != activities && activities.size()>0) {
            Activity activity;
            Iterator<Activity> iterator = activities.iterator();
            while(iterator.hasNext()) {
                activity = iterator.next();
                if(activity.getClass().equals(clzz)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Activity getTopActivity() {
        if(null != activities && activities.size()>0) {
            return activities.peek();  // peek not pop(get top element and remove it)
        }
        return null;
    }

    public void finishAllActivity() {
        if(null != activities && activities.size()>0) {
            while(!activities.isEmpty()) {
                activities.pop().finish();
            }
            activities.clear();
            activities = null;
        }
    }

    public void printActivityStack() {
        if(null != activities && activities.size()>0) {
            Iterator<Activity> iterator = activities.iterator();
            StringBuilder builder = new StringBuilder();
            builder.append("ActivityStack:\n");
            while(iterator.hasNext()) {
                builder.append(iterator.next().getClass().getName()+"\n");
            }
            Log.i(TAG, builder.toString());
        }
    }

    public int getActivityPosition(Activity activity) {
        if(null != activities && activities.size()>0) {
            return activities.search(activity);
        }
            return -1;
    }

    public int getActivityPosition(Class<?> clzz) {
        if(null != activities && activities.size()>0) {
            Activity activity;
            Iterator<Activity> iterator = activities.iterator();
            while(iterator.hasNext()) {
                activity = iterator.next();
                if(activity.getClass().equals(clzz)) {
                    return activities.search(activity);
                }
            }
        }
        return -1;
    }

    public void exitApp() {
        finishAllActivity();
    }

    public void setTopActivity(Activity activity) {

        if(null != activities && activities.size()>0) {
            if(activities.search(activity) == -1) {
                activities.push(activity);
                return;
            }

            int location = activities.search(activity) -1;
            if(location != 0) {
                activities.push(activities.remove(location));
            }
        } else {
            addActivity(activity);
        }
    }
}

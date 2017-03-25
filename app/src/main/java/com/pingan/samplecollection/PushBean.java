package com.pingan.samplecollection;

import java.io.Serializable;

/**
 * @author apple
 * @Description :
 * @date 17/3/9  下午5:34
 */
public class PushBean implements Serializable{

    public boolean isNotification;
    public String payload;

    public boolean isNotification() {
        return isNotification;
    }

    public String getPayload() {
        return payload;
    }

    public void setNotification(boolean notification) {
        isNotification = notification;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}

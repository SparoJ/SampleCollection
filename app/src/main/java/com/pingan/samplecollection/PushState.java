package com.pingan.samplecollection;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * @author apple
 * @Description :
 * @date 17/3/9  下午5:33
 */

/**
 * 缺少反订阅的问题 可能导致内存泄漏
 */
public class PushState {

    private PushState() {}

    private static PublishSubject<PushBean> pushObservable = PublishSubject.create();

    public static Observable<PushBean> getObservable() {
        return pushObservable;
    }

    public static void publish(PushBean pushBean) {
        try {
            pushObservable.onNext(pushBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

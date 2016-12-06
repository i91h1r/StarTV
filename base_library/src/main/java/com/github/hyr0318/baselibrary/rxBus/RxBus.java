package com.github.hyr0318.baselibrary.rxBus;

import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Description:
 * 作者：hyr on 2016/11/18 09:25
 * 邮箱：2045446584@qq.com
 */
public class RxBus {

    private static volatile RxBus rxBus;

    private final Subject<Object, Object> bus;

    private final Map<Class<?>, Object> mStickyEventMap;


    public RxBus() {

        bus = new SerializedSubject<>(PublishSubject.create());

        mStickyEventMap = new ConcurrentHashMap<>();
    }


    public static RxBus getDefault() {

        if (rxBus == null) {

            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }

        return rxBus;
    }


    public void post(EventCenter eventCenter) {

        bus.onNext(eventCenter);
    }


    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }


    public void postSticky(EventCenter eventCenter) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(eventCenter.getClass(), eventCenter);
        }

        post(eventCenter);
    }


    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> tObservable = bus.ofType(eventType);

            final Object event = mStickyEventMap.get(eventType);

            if (null != event) {
                return tObservable.mergeWith(Observable.create(new Observable.OnSubscribe<T>() {
                    @Override public void call(Subscriber<? super T> subscriber) {
                        subscriber.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return tObservable;
            }
        }
    }


    public <T> T getStickyEvent(Class<T> eventType) {

        synchronized (mStickyEventMap) {

            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }


    public <T> T removeStickyEvent(Class<T> eventType) {

        synchronized (mStickyEventMap) {

            return eventType.cast(mStickyEventMap.remove(eventType));
        }

    }


    public void removeAllStickyEvents() {

        synchronized (mStickyEventMap) {

            mStickyEventMap.clear();
        }
    }

}

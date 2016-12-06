package com.github.hyr0318.baselibrary.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.github.hyr0318.baselibrary.base.BaseAppManager;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.loading.LoadingViewController;
import com.github.hyr0318.baselibrary.net.NetChangeObserver;
import com.github.hyr0318.baselibrary.net.NetStateReceiver;
import com.github.hyr0318.baselibrary.net.NetUtils;
import com.github.hyr0318.baselibrary.rxBus.RxBus;
import com.github.hyr0318.baselibrary.rxBus.RxBusSubscriber;
import com.github.hyr0318.baselibrary.rxBus.RxSubscriptions;
import com.github.hyr0318.baselibrary.utils.CommonUtils;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Description:
 * 作者：hyr on 2016/8/31 09:14
 * 邮箱：2045446584@qq.com
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected Context mContext = null;

    protected static String TAG_LOG = null;
    private NetChangeObserver netChangeObserver;
    private LoadingViewController loadingViewController;
    public Subscription rxSubscribe;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取传递参数
        Bundle extras = getIntent().getExtras();

        if (null != extras) {
            getIntentExtras(extras);
        }

        //绑定Eventbus
        if (isBindRxBusHere()) {

            rxSubscribe = RxBus.getDefault().toObservableSticky(EventCenter.class).map(
                new Func1<EventCenter, EventCenter>() {
                    @Override public EventCenter call(EventCenter eventCenter) {
                        try {
                            // 变换操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return eventCenter;
                    }
                }).subscribe(new RxBusSubscriber<EventCenter>() {
                @Override protected void onEvent(EventCenter eventCenter) {
                    if (null != eventCenter) {
                        onEventComming(eventCenter);
                    }
                }


                @Override public void onError(Throwable e) {
                    super.onError(e);
                }
            });

        }

        RxSubscriptions.add(rxSubscribe);
        mContext = this;

        TAG_LOG = this.getClass().getSimpleName();

        BaseAppManager.getInstance().addActivity(this);

        if (getContentViewLayoutID() != 0) {

            setContentView(getContentViewLayoutID());
        } else {
            throw new IllegalArgumentException("返回一个正确的ContentView");
        }

        netChangeObserver = new NetChangeObserver() {
            @Override public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }


            @Override public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };

        NetStateReceiver.registerObserver(netChangeObserver);

        initViewsAndEvents();
    }


    protected abstract void getViewById();


    @Override public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getViewById();

        if (null != getLoadingView()) {

            loadingViewController = new LoadingViewController(
                getLoadingView());

        }

    }


    @Override protected void onDestroy() {
        super.onDestroy();

        NetStateReceiver.removeRegisterObserver(netChangeObserver);

        if (isBindRxBusHere()) {
            RxSubscriptions.remove(rxSubscribe);
        }
    }


    protected abstract View getLoadingView();

    //初始化view和绑定eventbus
    protected abstract void initViewsAndEvents();

    //获取传递参数
    protected abstract void getIntentExtras(Bundle extras);

    //是否绑定EventBus
    protected abstract boolean isBindRxBusHere();

    //获取布局view id
    protected abstract int getContentViewLayoutID();

    //网络连接
    protected abstract void onNetworkConnected(NetUtils.NetType type);

    //网络没有连接
    protected abstract void onNetworkDisConnected();

    protected abstract void onEventComming(EventCenter eventCenter);


    /**
     * 启动新的Activity
     */

    protected void startNewActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    /**
     * 启动新的Activity,带参数
     */

    protected void startNewActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 启动新的Activity，并关闭Activity
     */
    protected void startNewActivityAndFinished(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }


    /**
     * 启动带参数的新Activity,并关闭Activity
     */
    protected void startNewActivityAndFinished(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }


    /**
     * 启动一个有返回结果的activity
     */
    protected void startNewActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }


    /**
     * 启动一个带参数的有返回结果的activity
     */
    protected void startNewActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * toast
     */
    protected void showToast(String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !CommonUtils.isEmpty(msg)) {
            Snackbar.make(getLoadingView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }


    /**
     * 触发显示加载进度视图
     */
    protected void triggerShowLoading(boolean toggle) {
        if (null == loadingViewController) {
            throw new IllegalArgumentException("请返回一个正确的加载进度的view");
        }

        if (toggle) {
            loadingViewController.showLoading();
        } else {
            loadingViewController.restore();
        }
    }


    /**
     * 触发显示一个空的视图
     */

    protected void triggerShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == loadingViewController) {
            throw new IllegalArgumentException("请返回一个正确的加载进度的view");
        }

        if (toggle) {
            loadingViewController.showEmpty(msg, onClickListener);
        } else {
            loadingViewController.restore();
        }
    }


    /**
     * 触发显示一个错误的视图
     */
    protected void triggerShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == loadingViewController) {
            throw new IllegalArgumentException("请返回一个正确的加载进度的view");
        }

        if (toggle) {
            loadingViewController.showError(msg, onClickListener);
        } else {
            loadingViewController.restore();
        }
    }


    /**
     * 触发显示一个网络错误的视图
     */
    protected void triggerNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == loadingViewController) {
            throw new IllegalArgumentException("请返回一个正确的加载进度的view");
        }

        if (toggle) {
            loadingViewController.showNetworkError(onClickListener);
        } else {
            loadingViewController.restore();
        }
    }

}

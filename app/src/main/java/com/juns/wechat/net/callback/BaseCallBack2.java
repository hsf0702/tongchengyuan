package com.juns.wechat.net.callback;

import android.widget.Toast;

import com.juns.wechat.R;
import com.juns.wechat.manager.AccountManager;
import com.juns.wechat.net.response.BaseResponse;
import com.juns.wechat.net.response.BaseResponse2;
import com.juns.wechat.util.ToastUtil;

import org.xutils.common.Callback;

/**
 */
public abstract class BaseCallBack2<T> implements Callback.CommonCallback<T>{

    @Override
    public final void onSuccess(T result) {
        BaseResponse2 response = (BaseResponse2) result;
        if(response.code == BaseResponse.SUCCESS){
            handleResponse(result);
        }else if(response.code == BaseResponse.SERVER_ERROR){
            ToastUtil.showToast("服务器出错了", Toast.LENGTH_SHORT);
            handleResponse(result);
        }else if(response.code == BaseResponse.TOKEN_EXPIRED || response.code == BaseResponse.TOKEN_INVALID){
            handleTokenError();
        }else {
            onFailure(response.code, response.msg);
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ToastUtil.showToast(R.string.toast_network_error, Toast.LENGTH_SHORT);
    }

    protected void handleResponse(T result){

    }

    protected void onFailure(int code, String msg) {

    }

    private void handleTokenError(){
        AccountManager.getInstance().logOut();
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}

package org.maktab.musucplayer.view_model.orderingFragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;

public class Handeler<T> extends HandlerThread {

    public static final String TAG = "ThumbnailDownloader";
    private static final int MESSAGE_DOWNLOAD = 0;

    private Handler mRequestHandler;
    private Handler mResponseHandler;
    private HandlerAns mListener;

    public void setListener(HandlerAns listener) {
        mListener = listener;
    }

    public Handeler(Handler responseHandler) {
        super(TAG);
        mRequestHandler = new Handler();
        mResponseHandler = responseHandler;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                try {
                    ((ViewModelOrdering) msg.obj).initFragments();
                    mResponseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mListener.onCopleate(true);
                        }
                    });
                } catch (Exception e) {
                    mResponseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mListener.onCopleate(false);
                        }
                    });
                }

            }
        };
    }

    public void queueThumbnailMessage(T target) {
        Message message = mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target);
        message.sendToTarget();

    }

    public interface HandlerAns<T> {
        void onCopleate(boolean ans);
    }
}

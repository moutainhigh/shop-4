package com.qutu.talk.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public abstract class CountTimeUtils{

    /**
     * Millis since epoch when alarm should stop.
     */
    private final long mMillisInFuture;

    private long mStopTimeInFuture;

    /**
     * boolean representing if the timer was cancelled
     */
    private boolean mCancelled = false;

    /**
     * @param secondInFuture The number of millis in the future from the call
     *   to {@link #start()} until the countdown is done and {@link #onFinish()}
     *   is called.
     */
    public CountTimeUtils(long secondInFuture) {
        mMillisInFuture = secondInFuture;
    }

    /**
     * Cancel the countdown.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /**
     * Start the countdown.
     */
    public synchronized final CountTimeUtils start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = (SystemClock.elapsedRealtime()/1000) + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }


    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();

    private static final int MSG = 1;

    // handles counting down
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (CountTimeUtils.this) {
                if (mCancelled) {
                    return;
                }
                final long millisLeft = mStopTimeInFuture - (SystemClock.elapsedRealtime()/1000);
                if (millisLeft < 0) {
                    onFinish();
                } else {
                    onTick(millisLeft);
                    sendMessageDelayed(obtainMessage(MSG), 1000);
                }
            }
        }
    };
}

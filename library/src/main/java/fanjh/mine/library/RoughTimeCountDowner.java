package fanjh.mine.library;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
* @author fanjh
* @date 2017/9/1 17:24
* @description TimeCountDowner
 * rough mean when the system busy will cause time was unCorrect
**/
public class RoughTimeCountDowner {
    public static final int INVALID_TIME = -1;
    private long mInitialTime;
    private long mIntervalTime;
    private long mRestTime;
    private OnTickListener mOnTickListener;
    private boolean isRunning;

    public interface OnTickListener{
        void onTick(long restTime,long initTime,long intervalTime);
        void onDone();
    }

    public RoughTimeCountDowner(long mInitialTime, long mIntervalTime, OnTickListener mOnTickListener) {
        this.mInitialTime = mInitialTime;
        this.mIntervalTime = mIntervalTime;
        mRestTime = mInitialTime;
        this.mOnTickListener = mOnTickListener;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start(){
        if(isRunning){
            return;
        }
        if(mInitialTime > 0 && mIntervalTime > 0) {
            mRestTime = mInitialTime;
            isRunning = true;
            if(null != mOnTickListener){
                mOnTickListener.onTick(mRestTime,mInitialTime,mIntervalTime);
            }
            mMainHandler.sendEmptyMessageDelayed(0, mIntervalTime);
        }
    }

    public void reset(){
        if(!isRunning){
            return;
        }
        mInitialTime = INVALID_TIME;
        mIntervalTime = INVALID_TIME;
        mRestTime = INVALID_TIME;
        pause();
    }

    public void pause(){
        if(isRunning){
            isRunning = false;
            mMainHandler.removeCallbacksAndMessages(null);
        }
    }

    private Handler mMainHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(null != mOnTickListener){
                mRestTime -= mIntervalTime;
                if(mRestTime <= 0){
                    isRunning = false;
                    mOnTickListener.onDone();
                }else if(mRestTime > 0){
                    mOnTickListener.onTick(mRestTime,mInitialTime,mIntervalTime);
                    sendEmptyMessageDelayed(0,mIntervalTime);
                }
            }
        }
    };

}

package com.example.splash;

import android.os.Handler;

public class ITimeCounter implements Runnable {
    int time;
    GetBack getBack;
    Handler handler;
    boolean isRun=false;
    ITimeCounter(int time,GetBack getBack)
    {
        handler= new Handler();
        this.time=time;
        this.getBack=getBack;
    }

    @Override
    public void run() {
        if(isRun)
        {
            if(time>0)
            {
                time--;
                getBack.getTime(time);
                handler.postDelayed(this,1000);
            }
            if(time<=0)
            {
                cancel();
                this.getBack.onFinish();
            }
        }
    }

    void start()
    {
        isRun=true;
        handler.post(this);
    }

    public void cancel() {
        handler.removeCallbacks(this);
    }

    public interface GetBack{
        void getTime(int time);
        void onFinish();
    }


}

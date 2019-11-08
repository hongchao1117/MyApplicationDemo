package com.hongchao.keepservicealive;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessConnection.Stub(){};

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1,new Notification());
        //绑定建立链接
        bindService(new Intent(this,GuardService.class),connection,Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("tag", "onServiceConnected: 建立连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开连接
            startService(new Intent(MyService.this,GuardService.class));
            //重新绑定
            bindService(new Intent(MyService.this,GuardService.class),
                    connection,Context.BIND_IMPORTANT);
        }
    };
}

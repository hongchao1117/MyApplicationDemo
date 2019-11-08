package com.hongchao.mybroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 实现一个强制下线的功能
 */
public class MainActivity extends BaseActivity {
    private Button send_broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_broadcast = findViewById(R.id.send_broadcast);
        send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.hongchao.mybroadcastreceiver.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}

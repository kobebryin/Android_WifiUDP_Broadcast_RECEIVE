package com.example.develop.receive_broadcast_wifiudp;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static Handler exHandler;
    TextView tv;
    private ChatServer chatserver;
    String msgString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tv = (TextView)findViewById(R.id.tv_res);
        try {
            chatserver= new ChatServer();
            chatserver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        exHandler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                msgString += msg.obj + "\n";
                Log.d("Handler","Now in Handler");
                tv.setText(msgString);
            }
        };
    }


}

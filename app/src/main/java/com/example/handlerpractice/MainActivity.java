package com.example.handlerpractice;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        final TextView textView = (TextView)findViewById(R.id.text);

        final Handler handler = new Handler(){
            public void handleMessage(Message message){
                if(message.arg1 != -1){
                    textView.setText(message.arg1+"");
                }
                else textView.setText("结束");

            }
        };

        final Runnable myWork = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress<=10){
                    Message message = new Message();
                    message.arg1 = progress;
                    handler.sendMessage(message);
                    progress +=1;

                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                Message message = handler.obtainMessage();
                message.arg1 = -1;
                handler.sendMessage(message);
            }
        };

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Thread thread = new Thread(null,myWork,"workThread");
                        thread.start();
                    }
                }
        );
    }
}

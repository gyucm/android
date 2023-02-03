
package com.kcci.socketclient;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static Handler mainHandler; //어찌보면 쓰레드
    ClientThread clientThread;
    TextView textView;
    ScrollView scrollViewRecv;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd.HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextIp = findViewById(R.id.editTextIP);
        EditText editTextId = findViewById(R.id.editTextID);
        ToggleButton toggleButtonStart = findViewById(R.id.toggleButtonStart);
        Button buttonSend = findViewById(R.id.buttonSend);
        EditText editTextSend = findViewById(R.id.editTextSend);
        buttonSend.setEnabled(false);
        textView = findViewById(R.id.textViewRecv);
        scrollViewRecv = findViewById(R.id.scrollViewRecv);

        toggleButtonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(toggleButtonStart.isChecked()){
                    String strIp = editTextIp.getText().toString();
                    String strId = editTextId.getText().toString();
                    clientThread = new ClientThread(strIp,strId);//객체생성
                    clientThread.start();
                    buttonSend.setEnabled(true);
                } else {
                    clientThread.stopClient();
                    buttonSend.setEnabled(false);
                }
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//이벤트 처리 클릭 시 동작
                String strSend = editTextSend.getText().toString();
                clientThread.sendData(strSend);
                editTextSend.setText("");
            }
        });
        mainHandler = new MainHandler();
    }
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Date date = new Date();
            String strDate = dateFormat.format(date);
            Bundle bundle = msg.getData();
            String data = bundle.getString("msg");
            data = data + '\n'; //문자 행 변경
            strDate = strDate + " " + data;
            textView.append(strDate);
            scrollViewRecv.fullScroll(View.FOCUS_DOWN); //자동 스크롤
        }
    }
}

package org.techtown.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment3 extends Fragment {
    ClientThread clientThread;
    TextView textView;
    ScrollView scrollViewRecv;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd.HH:mm:ss");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        EditText editTextIp = view.findViewById(R.id.editTextIP);
        EditText editTextPort = view.findViewById(R.id.editTextPort);
        ToggleButton toggleButtonStart = view.findViewById(R.id.toggleButtonStart);
        Button buttonSend = view.findViewById(R.id.buttonSend);
        EditText editTextSend = view.findViewById(R.id.editTextSend);
        buttonSend.setEnabled(false);
        textView = view.findViewById(R.id.textViewRecv);
        scrollViewRecv = view.findViewById(R.id.scrollViewRecv);

        toggleButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButtonStart.isChecked()) {
                    String strIp = editTextIp.getText().toString();
                    int intPort = Integer.parseInt(editTextPort.getText().toString()); //포장객체 형 변환
                    clientThread = new ClientThread(strIp, intPort);//객체생성
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
        return view;
    }

    void recvDataProcess(String data) {
        Date date = new Date();
        String strDate = dateFormat.format(date);
        data = data + '\n'; //문자 행 변경
        strDate = strDate + " " + data;
        textView.append(strDate);
        scrollViewRecv.fullScroll(View.FOCUS_DOWN); //자동 스크롤
    }
}

package com.kcci.sampleinternet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.annotation.Native;

public class MainActivity extends AppCompatActivity {
    //리턴할때 어떤 액티비티로 부터 받은건지 체크할려면 코드가 하나 필요해서 상수 정의
    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                intent.putExtra("arg","hello"); //인텐트에 담아서 보냄
                startActivityForResult(intent,REQUEST_CODE_MENU);//디플케이드 앞으로 사라질 함수
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(),"onActivityResult 메서드 호출됨. 요청코드 : " + requestCode + ", 결과코드 : " + resultCode, Toast.LENGTH_LONG).show();

            if (resultCode==RESULT_OK){
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(),"응답으로 전달된 name : " + name,Toast.LENGTH_LONG).show();
        }
        }
    }

}

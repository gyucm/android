package com.kcci.buttontest; // 버튼 사용법 배우기

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintest1);
    }
    public void onButton1Clicked(View v){
        Log.d("onButton1Clicked()","Test1"); //디버깅시 사용 Log.d (string,string)
        Toast.makeText(this,"확인 1 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();//long, short로 길이 조절
        Log.d("onButton1Clicked()","Test2");
    }
    public void onButton2Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
        startActivity(myIntent);
    }
    public void onButton3Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0000-1000"));
        startActivity(myIntent);
    }
}

//alt enter or 마우스 접근 후 import 누르면 함수 적용
// 아니면 FIle -> setting -> auto import 두개체크 (자동임폴트)
package com.kcci.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strId=null;
    String strPw=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout);

        Button login = findViewById(R.id.login);
        EditText editTextID = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);
        login.setOnClickListener(new View.OnClickListener() {//이벤트처리
            @Override
            public void onClick(View view) { //추상메소드 onClick 필수
                strId = editTextID.getText().toString(); //get text로 얻어와서 string 변환 후 strID에 저장
                strPw = editTextPw.getText().toString();
                if(strId.length() != 0 && strPw.length() !=0) {
                    Log.d("onClick()", "id : " + strId + ", pw : " + strPw); // log.d로 메세지 출력
                    Toast.makeText(view.getContext(), "id : " + strId + ", pw : " + strPw, Toast.LENGTH_LONG).show();
                    //디버깅시 logd 혹은 Toast 사용
                    editTextID.setText("");
                    editTextPw.setText("");
                }else {
                    Toast.makeText(view.getContext(), "id와 pw가 입력 되지 않았습니다", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
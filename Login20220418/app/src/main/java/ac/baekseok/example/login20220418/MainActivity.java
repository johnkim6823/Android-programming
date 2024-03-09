package ac.baekseok.example.login20220418;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Join {

    EditText edtID, edtPW;
    Button btnMainLogin, btnMainJoin;

    int IdFlag=0; //아이디 일치 1 아이디 일치 X 0
    int PwFlag=0; //PW 일치 1    PW 일치 X 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = (EditText) findViewById(R.id.loginID);
        edtPW = (EditText) findViewById(R.id.loginPW);

        btnMainLogin = (Button)findViewById(R.id.mainLogin);
        btnMainJoin = (Button) findViewById(R.id.mainJoin);

        btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM joinInfo;" ,null);

                //DB
                String strName = null;                          //임시변수
                String strPW = null;                            //임시변수

                //EditText
                String InputID = null;                          //임시변수
                String InputPW = null;                          //임시변수

                //LoginDB의 joinInfo테이블에서 2차원 배열구조의 회원정보 가져옴 여러건
                //1건 1건씩 로그인화면에 내가 입력한 ID와 PW가 맞는지 비교
                IdFlag = 0;                                     //전역변수 초기화 필요
                PwFlag =0;                                      //전역변수 초기화 필요

                while(cursor.moveToNext()) {                     //cursor2차원의 모든 회원정보가 있음. moveToNext() 다음 회원이 있으면 비교
                    //로그인 화면에서 입력한 ID,PW이 cursor이번 행(이번 건)과 비교
                    strName = cursor.getString(0);
                    strPW = cursor.getString(1);
                    InputID = edtID.getText().toString();
                    InputPW = edtPW.getText().toString();

                    //DB에서 가져온 ID/PW와 editText에 적힌 부분이 맞는지 비교
                    if(InputID.equals(strName)) {               //일치하면 True, 다르면 False
                        IdFlag = 1;
                        if(InputPW.equals(strPW)){              // ID 일치 PW 일치
                            PwFlag = 1;
                            Toast.makeText(getApplicationContext(), "정상회원입니다.\n" + InputID+"님 안녕하세요!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), secondActivity.class);    //변경 필요
                            startActivity(intent);
                        }//if->if()
                        else{                                   // ID 일치 PW 불일치
                            PwFlag =0;
                            Toast.makeText(getApplicationContext(), "회원님, 비밀번호 오류입니다.\n다시 입력해주세요.", Toast.LENGTH_LONG).show();
                        }//if->else()
                    }//if()
                    else{                                       //ID 불일치
                    }//else()
                }//while()

                cursor.close();
                sqlDB.close();
                //ID가 없다면
                if(IdFlag == 0 && PwFlag == 0) {
                    Toast.makeText(getApplicationContext(), "등록된 ID가 없습니다.\n 회원가입을 진행해 주세요.", Toast.LENGTH_LONG).show();
                }
            }//Onclick()
        });

        btnMainJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });




    }//onCreate()

}//MainActivity
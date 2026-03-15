package ac.baekseok.example.login20220418;

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
                String InputID = edtID.getText().toString().trim();
                String InputPW = edtPW.getText().toString().trim();

                if (InputID.isEmpty() || InputPW.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT * FROM joinInfo WHERE uID = ?", new String[]{InputID});

                IdFlag = 0;
                PwFlag = 0;

                if (cursor.moveToFirst()) {
                    IdFlag = 1;
                    String strPW = cursor.getString(1);
                    if (InputPW.equals(strPW)) {
                        PwFlag = 1;
                        Toast.makeText(getApplicationContext(), "정상회원입니다.\n" + InputID + "님 안녕하세요!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "회원님, 비밀번호 오류입니다.\n다시 입력해주세요.", Toast.LENGTH_LONG).show();
                    }
                }

                cursor.close();
                sqlDB.close();

                if (IdFlag == 0) {
                    Toast.makeText(getApplicationContext(), "등록된 ID가 없습니다.\n회원가입을 진행해 주세요.", Toast.LENGTH_LONG).show();
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

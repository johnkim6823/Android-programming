package ac.baekseok.example.login20220418;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Join extends Activity {

    EditText joinID, joinPW;
    Button btnJoinRegistration, btnJoingoMain;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    int IdFlag=0; //아이디 일치 1 아이디 일치 X 0
    int PwFlag=0; //PW 일치 1    PW 일치 X

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        joinID = (EditText) findViewById(R.id.joinID);
        joinPW = (EditText) findViewById(R.id.joinPW);

        btnJoinRegistration = (Button)findViewById(R.id.registration);
        btnJoingoMain = (Button) findViewById(R.id.goMain);

        myHelper = new myDBHelper(this);

        btnJoinRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 등록
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO joinInfo VALUES('"+
                        joinID.getText().toString()+"','"+
                        joinPW.getText().toString()+"');");

                sqlDB.close();
                Toast.makeText(getApplicationContext(), joinID.getText().toString() + "님이 가입되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        btnJoingoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }//onCreate()

    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context, "LoginDB", null, 1);
        }//생성자로 DB생성, LoginDB생성

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE joinInfo(uID Text PRIMARY KEY, uPassword Text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS joinInfo");
            onCreate(sqLiteDatabase);
        }
    }

}//Join

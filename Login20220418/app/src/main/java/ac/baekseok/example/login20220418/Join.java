package ac.baekseok.example.login20220418;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Join extends AppCompatActivity {

    EditText joinID, joinPW;
    Button btnJoinRegistration, btnJoingoMain;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                String id = joinID.getText().toString().trim();
                String pw = joinPW.getText().toString().trim();

                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = myHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("uID", id);
                values.put("uPassword", pw);
                sqlDB.insert("joinInfo", null, values);

                sqlDB.close();
                Toast.makeText(getApplicationContext(), id + "님이 가입되었습니다.", Toast.LENGTH_LONG).show();
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
        }

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

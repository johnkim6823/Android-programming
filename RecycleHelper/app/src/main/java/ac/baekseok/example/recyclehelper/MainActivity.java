package ac.baekseok.example.recyclehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtLoginID, edtLoginPW;
    Button btnLogin, btnJoin;

    UserDBHelper dbHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("RecycleHelper - 분리수거 도우미");

        edtLoginID = (EditText) findViewById(R.id.loginID);
        edtLoginPW = (EditText) findViewById(R.id.loginPW);
        btnLogin = (Button) findViewById(R.id.mainLogin);
        btnJoin = (Button) findViewById(R.id.mainJoin);

        dbHelper = new UserDBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtLoginID.getText().toString().trim();
                String pw = edtLoginPW.getText().toString().trim();

                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = dbHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT * FROM userTBL WHERE uID = ?", new String[]{id});

                if (cursor.moveToFirst()) {
                    String dbPW = cursor.getString(1);
                    if (pw.equals(dbPW)) {
                        Toast.makeText(getApplicationContext(), id + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), RecycleGuideActivity.class);
                        intent.putExtra("userName", id);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "등록되지 않은 ID입니다.\n회원가입을 진행해 주세요.", Toast.LENGTH_LONG).show();
                }

                cursor.close();
                sqlDB.close();
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class UserDBHelper extends SQLiteOpenHelper {

        public UserDBHelper(Context context) {
            super(context, "RecycleHelperDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE userTBL(uID TEXT PRIMARY KEY, uPassword TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS userTBL");
            onCreate(db);
        }
    }
}

package ac.baekseok.example.recyclehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText edtJoinID, edtJoinPW;
    Button btnRegistration, btnGoMain;

    MainActivity.UserDBHelper dbHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setTitle("RecycleHelper - 회원가입");

        edtJoinID = (EditText) findViewById(R.id.joinID);
        edtJoinPW = (EditText) findViewById(R.id.joinPW);
        btnRegistration = (Button) findViewById(R.id.registration);
        btnGoMain = (Button) findViewById(R.id.goMain);

        dbHelper = new MainActivity.UserDBHelper(this);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtJoinID.getText().toString().trim();
                String pw = edtJoinPW.getText().toString().trim();

                if (id.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ID와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pw.length() < 4) {
                    Toast.makeText(getApplicationContext(), "비밀번호는 4자리 이상 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = dbHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT * FROM userTBL WHERE uID = ?", new String[]{id});
                if (cursor.moveToFirst()) {
                    Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    sqlDB.close();
                    return;
                }
                cursor.close();
                sqlDB.close();

                sqlDB = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("uID", id);
                values.put("uPassword", pw);
                sqlDB.insert("userTBL", null, values);
                sqlDB.close();

                Toast.makeText(getApplicationContext(), id + "님 가입이 완료되었습니다!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

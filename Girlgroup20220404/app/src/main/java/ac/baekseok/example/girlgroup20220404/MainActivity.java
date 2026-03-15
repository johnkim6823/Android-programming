package ac.baekseok.example.girlgroup20220404;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText editName, editCount, editNameResult, editCountResult;
    Button btnInsert, btnSelect, btnDelete, btnUpdate, btnInit;
    SQLiteDatabase sqlDB;                                           //write용 read용 다 필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.EditName);
        editCount = (EditText) findViewById(R.id.EditCount);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnQuery);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnInit = (Button) findViewById(R.id.btnInit);

        editNameResult = (EditText) findViewById(R.id.edtNameResult);
        editCountResult = (EditText) findViewById(R.id.edtCountResult);

        myHelper = new myDBHelper(this);      //본 화면에 생성(MainActivity)

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String count = editCount.getText().toString().trim();
                if (name.isEmpty() || count.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "그룹 이름과 인원수를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = myHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("gName", name);
                values.put("gNumber", Integer.parseInt(count));
                sqlDB.insert("groupTBL", null, values);

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "1건의 입력이 실행되었습니다.", Toast.LENGTH_LONG).show();
            }
        });//btnInsert

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor= sqlDB.rawQuery("SELECT * FROM groupTBL", null);

                String strNames = "그룹 이름\r\n--------------\r\n";
                String strCounts = "그룹 인원\r\n--------------\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strCounts += cursor.getString(1) + "\r\n";
                }

                editNameResult.setText(strNames);
                editCountResult.setText(strCounts);
                cursor.close();
                sqlDB.close();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String count = editCount.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "수정할 그룹 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = myHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("gNumber", count);
                sqlDB.update("groupTBL", values, "gName = ?", new String[]{name});

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_LONG).show();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "삭제할 그룹 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                sqlDB = myHelper.getWritableDatabase();
                sqlDB.delete("groupTBL", "gName = ?", new String[]{name});

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_LONG).show();
                btnSelect.callOnClick();
            }
        });

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1,2);
                sqlDB.close();
                btnSelect.callOnClick();
                Toast.makeText(getApplicationContext(), "DB가 초기화 되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

    }//onCreate

    public class myDBHelper extends SQLiteOpenHelper{
        //DB 생성
        public myDBHelper(Context context) {    //데이터베이스를 생성한다.
            super(context, "groupDB", null, 1);         //groupDB라는 DB 생성
        }//myDBHelper()

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {               //테이블 생성
            sqLiteDatabase.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {     //테이블의 내용을 삭제 후 생성을 한다.
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(sqLiteDatabase);
        }

    }//myDBHelper
}//MainActivity

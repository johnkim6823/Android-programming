package ac.baekseok.example.girlgroup20220404;

import androidx.appcompat.app.AppCompatActivity;

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
        //클래스명으로 인스턴스 생성 = 생성자.

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('" +
                        editName.getText().toString()+"', "+
                        editCount.getText().toString()+");");

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
                String strCounts = "그릅 인원\r\n--------------\r\n";

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
                sqlDB =myHelper.getReadableDatabase();
                if(editName.getText().toString()!=""){
                    sqlDB.execSQL("UPDATE groupTBL SET gNumber="
                            + editCount.getText()+" WHERE gName='"
                            + editName.getText().toString()+"';");
                }

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_LONG).show();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                if(editName.getText().toString()!=""){
                    sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '"
                            +editName.getText().toString()+"';");
                }
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
        //myDBHelper가 SQLiteOpenHelper를 상속 받음. 2개의 필수 Method -> onCreate(), onUpgrade()
        //onCreate(): 테이블 생성, onUpgrade(): 테이블 삭제 및 다시 생성하는 역활
        //커서를 myDBHelper클래스에 두고 [Code] -> [override]

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {               //테이블 생성
            //Query문도 Language 중 하나.  //DB 처리(query) -> 테이블 생성 | 문자열로 처리받음.
            sqLiteDatabase.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {     //테이블의 내용을 삭제 후 생성을 한다.
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(sqLiteDatabase);
        }


    }//myDBHelper(클래스에 클래스가 있다는 의미로 내부클래스라함.
}//MainActivity
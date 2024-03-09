package ac.baekseok.example.diary20220425;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnWrite;
    EditText editDiary;

    String fileName;                                                //오늘 날짜의 또는 지정된 날짜의 일기는  날짜.txt 휴대폰에 저장됨 -> 20220425.txt -> 확정자는 빼고

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("김명준의 간단 일기장");                                //액티비티 상단(Title)

        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        editDiary = (EditText)findViewById(R.id.edtDiary);

        Calendar cal = Calendar.getInstance() ;
        int CYear = cal.get(Calendar.YEAR);                                       //년도 값 가져오기
        int CMonth = cal.get(Calendar.MONTH);                                   //월 값 가져오기 (초기값: 0~11)
        int CDay = cal.get(Calendar.DATE);                                        //날짜 값 가져오기

        datePicker.init(CYear, CMonth, CDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                fileName = Integer.toString(i) + "_" + Integer.toString(i1+1) + "_" + Integer.toString(i2) + ".txt";
                //readDiary(fileName);
            }
        });//초기샃태 메소드 해당되는 날짜의 일기가 있으면 그 읽기를 날짜.txt에서 읽어와서 영역에 불러와라.


    }//onCreate()

    public void readDiary(){

    }
}
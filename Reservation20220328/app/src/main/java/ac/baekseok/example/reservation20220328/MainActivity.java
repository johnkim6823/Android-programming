package ac.baekseok.example.reservation20220328;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;    //Chrono
    Button btnStart, btnEnd;

    RadioButton rdoCal, rdoTime;

    CalendarView calendarView1;
    TimePicker timePicker1;

    TextView textView1;

    Integer selectYear = 0, selectMonth = 0, selectDay = 0; //전역변수 선언 임시 날짜 피커에서 선택한 년도, 월, 일 저장 변수.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("20171274 김명준 시간 예약하기");

        //버튼 위젯
        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);
        //크로노미터
        chronometer = (Chronometer)findViewById(R.id.chronometer1);
        //라디오 버튼
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        //캘린더뷰
        calendarView1 = (CalendarView) findViewById(R.id.calendarView1);
        //타임픽커
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        //텍스트뷰뷰
       textView1 = (TextView)findViewById(R.id.textView1);
       //앱 실행되면 radioGroup,캘린더뷰와 타임피커는 invisible
        calendarView1.setVisibility(View.INVISIBLE);
        timePicker1.setVisibility(View.INVISIBLE);

        btnStart.setOnClickListener(new View.OnClickListener() {        //예약에 거리는 시간 강조하기 위해 글자색 변경
            @Override
            public void onClick(View view) {
                chronometer.setTextColor(Color.RED);
                chronometer.setBase(SystemClock.elapsedRealtime());     //시간 초기화 되었으니 +1초씩 타이머동작 시작
                chronometer.start();                                    //타이머 스타트
            }
        });//btnStart

        rdoCal.setOnClickListener(new View.OnClickListener() {          //날짜 선택 radioButton을 누르면
            @Override
            public void onClick(View view) {
                calendarView1.setVisibility(View.VISIBLE);
                timePicker1.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {         //시간 선책 radioButton을 누르면
            @Override
            public void onClick(View view) {
                calendarView1.setVisibility(View.INVISIBLE);
                timePicker1.setVisibility(View.VISIBLE);
            }
        });

        calendarView1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //선택된 날짜변경 메소드 안에 날짜 저장 하는 코드
                selectYear = year;
                selectMonth = month+1; //1월부터 12월이기 때문에 +1 해야함
                selectDay = day;
            }
        });

        //캘린더뷰에서 날짜변경, 타임피커에서 시간 병경하고 btnEnd를 누르면 (예약완료버튼)을 누르면 타이머 정지 및 글자 파란색으로 지정
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //타이머 정지
                chronometer.stop();
                //글자 파란색으로
                chronometer.setTextColor(Color.BLUE);
                //글자 넣기
                textView1.setText(Integer.toString(selectYear) + "년 " +
                                    Integer.toString(selectMonth) + "월 " +
                                    Integer.toString(selectDay) + "일 " +
                                    Integer.toString(timePicker1.getCurrentHour()) + "시 " +
                                    Integer.toString(timePicker1.getCurrentMinute()) + "분에 예약됨");

                //날짜 선택을 하지 않아도 오늘을 초기값으로 갖기.   -> selectYear,Month,Day가 하나라도 1보다 작다면.
                if(selectYear < 1 || selectMonth < 1 || selectDay < 1) {
                    java.util.Calendar curDate=java.util.Calendar.getInstance();
                    curDate.setTimeInMillis(calendarView1.getDate());//캘린더뷰에 가져오는 Method

                    textView1.setText(Integer.toString(curDate.get(Calendar.YEAR)) + "년 " +
                            Integer.toString(curDate.get((Calendar.MONTH+1))) + "월 " +
                            Integer.toString(curDate.get(Calendar.DAY_OF_MONTH)) + "일 " +
                            Integer.toString(timePicker1.getCurrentHour()) + "시 " +
                            Integer.toString(timePicker1.getCurrentMinute()) + "분에 예약됨");
                }

            }
        });

    }//onCreate()
}//MainActivity()
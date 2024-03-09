package ac.baekseok.example.activity20220404;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
    Button secondBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondBtn = (Button) findViewById(R.id.secondBtn);

        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼을 눌렀을 때 화면 닫음(종료 Method)
                finish();
                //눌렀을 때 다음 화면으로 전환용
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                //Intent로 넘기면 start를 통해 Activity 시작 가능
                startActivity(intent);  //정보 전달 - SecondActivity.java에 대한 intent를 가지고 두번째 화면을 시작
            }
        });
    }//OnCreate()
}//MainActivity
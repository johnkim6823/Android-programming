package ac.baekseok.example.activity20220404;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mainBtn1, mainBtn2, mainBtn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBtn1 = (Button) findViewById(R.id.mainBtn1);
        mainBtn2 = (Button) findViewById(R.id.mainBtn2);
        mainBtn3 = (Button) findViewById(R.id.mainBtn3);

        mainBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //눌렀을 때 다음 화면으로 전환용
                Intent intent=new Intent(getApplicationContext(), SecondActivity.class);
                //Intent로 넘기면 start를 통해 Activity 시작 가능
                startActivity(intent);  //정보 전달 - SecondActivity.java에 대한 intent를 가지고 두번째 화면을 시작
            }
        });

        mainBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ThirdActivity.class);
                //Intent로 넘기면 start를 통해 Activity 시작 가능
                startActivity(intent);  //정보 전달 - SecondActivity.java에 대한 intent를 가지고 두번째 화면을 시작
            }
        });

        mainBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ForthActivity.class);
                //Intent로 넘기면 start를 통해 Activity 시작 가능
                startActivity(intent);  //정보 전달 - SecondActivity.java에 대한 intent를 가지고 두번째 화면을 시작
            }
        });
    }//OnCreate()
}//MainActivity
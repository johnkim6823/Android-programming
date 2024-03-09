package ac.baekseok.example.calculator20220314;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //인스턴스 선언
    EditText edit1, edit2;          // 숫자 입력
    Button btnAdd, btnSub, btnMul, btnDiv;  //계산기 버튼
    TextView result;                //결과 TextView

    String num1, num2;              //숫자1, 숫자2 저장용
    Integer resultnum;              //결과값 저장용
    Double temp1;                   //소수점을 위한 temp값
    String temp2;                   //최종 소수점 2자리를 위한 String
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //인스턴스 생성
        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button)findViewById(R.id.BtnDiv);
        result = (TextView) findViewById(R.id.Result);

        //버튼 클릭 시
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) + Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());  // +는 문자 결합 연산자
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) - Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());  // +는 문자 결합 연산자
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) * Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());  // +는 문자 결합 연산자
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();
                temp1 = Double.parseDouble(num1)/Double.parseDouble(num2);
                temp2 = String.format("%.2f",temp1); //temp: Double인데 String 타입으로 바꿈 -> "%.2f -> 문자열로 넣음
                //resultnum = Integer.parseInt(num1) / Integer.parseInt(num2);

                result.setText("계산 결과: " + temp2);  // +는 문자 결합 연산자
            }
        });
    }
}
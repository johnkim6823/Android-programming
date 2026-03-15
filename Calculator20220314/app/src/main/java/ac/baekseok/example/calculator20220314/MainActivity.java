package ac.baekseok.example.calculator20220314;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //더하기 버튼 클릭 시
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInput()) return;
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) + Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());
            }
        });

        //빼기 버튼 클릭 시
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInput()) return;
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) - Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());
            }
        });

        //곱하기 버튼 클릭 시
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInput()) return;
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                resultnum = Integer.parseInt(num1) * Integer.parseInt(num2);

                result.setText("계산 결과: " + resultnum.toString());
            }
        });

        //나누기 버튼 클릭 시
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInput()) return;
                num1= edit1.getText().toString();
                num2 = edit2.getText().toString();

                if (Double.parseDouble(num2) == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                temp1 = Double.parseDouble(num1)/Double.parseDouble(num2);
                temp2 = String.format("%.2f",temp1);

                result.setText("계산 결과: " + temp2);
            }
        });
    }

    private boolean validateInput() {
        String s1 = edit1.getText().toString().trim();
        String s2 = edit2.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "숫자를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Double.parseDouble(s1);
            Double.parseDouble(s2);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "올바른 숫자를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

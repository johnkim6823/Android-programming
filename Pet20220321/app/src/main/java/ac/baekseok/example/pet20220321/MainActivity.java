package ac.baekseok.example.pet20220321;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkAgree;                              //시작 버튼

    TextView text1;                                 //좋아하는 동물 TextView
    RadioGroup radioGroup1;                         //전체 라디오그룹 숩김/나타남을 위해
    RadioButton rbtnDog, rbtnCat, rbtnRabbit;       //선택할 버튼

    Button btnOk;                                   //서택할 버튼튼

    ImageView imgPet;                               //보여질 이미지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rbtnDog = (RadioButton) findViewById(R.id.rbtnDog);
        rbtnCat = (RadioButton) findViewById(R.id.rbtnCat);
        rbtnRabbit = (RadioButton) findViewById(R.id.rbtnRabbit);
        btnOk = (Button) findViewById(R.id.btnOk);
        imgPet = (ImageView) findViewById(R.id.imgPet);

        //체크가 변경 될 시 수행
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkAgree.isChecked()==true){ //만약 check가 되어 있을 경우
                    text1.setVisibility(View.VISIBLE);
                    radioGroup1.setVisibility(View.VISIBLE);
                    btnOk.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                }
                else{   //만약 check가 안되어 있을 경우
                    text1.setVisibility(View.INVISIBLE);
                    radioGroup1.setVisibility(View.INVISIBLE);
                    btnOk.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }

            }
        });//chkAgree()
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(radioGroup1.getCheckedRadioButtonId()){  //라디오 그룹의 체크가된 버튼 아이디를 가져옴.
                    case R.id.rbtnDog: imgPet.setImageResource(R.drawable.dog); break; //이미지를 이미지뷰에 셋딩(매개변수는 리소스 위치)
                    case R.id.rbtnCat: imgPet.setImageResource(R.drawable.cat); break;
                    case R.id.rbtnRabbit: imgPet.setImageResource(R.drawable.rabbit);   break;

                    default:
                        Toast.makeText(getApplicationContext(),"동물을 선택해 주세요.", Toast.LENGTH_LONG).show();
                }   //switch
            }
        }); //btnOk()



    }   //onCreate()
}   //MainActivity
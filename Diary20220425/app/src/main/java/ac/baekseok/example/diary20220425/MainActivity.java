package ac.baekseok.example.diary20220425;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnWrite;
    EditText editDiary;

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("김명준의 간단 일기장");

        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        editDiary = (EditText)findViewById(R.id.edtDiary);

        Calendar cal = Calendar.getInstance();
        int CYear = cal.get(Calendar.YEAR);
        int CMonth = cal.get(Calendar.MONTH);
        int CDay = cal.get(Calendar.DATE);

        fileName = CYear + "_" + (CMonth + 1) + "_" + CDay + ".txt";
        readDiary(fileName);

        datePicker.init(CYear, CMonth, CDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                fileName = i + "_" + (i1 + 1) + "_" + i2 + ".txt";
                readDiary(fileName);
            }
        });

        btnWrite.setEnabled(true);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editDiary.getText().toString();
                if (content.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "일기 내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                writeDiary(fileName, content);
                Toast.makeText(getApplicationContext(), "일기가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }//onCreate()

    public void readDiary(String fName) {
        try {
            FileInputStream fis = openFileInput(fName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            editDiary.setText(new String(buffer));
        } catch (IOException e) {
            editDiary.setText("");
        }
    }

    public void writeDiary(String fName, String content) {
        try {
            FileOutputStream fos = openFileOutput(fName, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "저장 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}

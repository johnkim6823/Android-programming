package ac.baekseok.example.recyclehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecycleGuideActivity extends AppCompatActivity {

    EditText edtSearch;
    LinearLayout layoutGuideList;
    TextView tvWelcome;

    // 분리수거 가이드 데이터
    private final LinkedHashMap<String, String[]> recycleData = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        setTitle("분리수거 가이드");

        String userName = getIntent().getStringExtra("userName");

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        layoutGuideList = (LinearLayout) findViewById(R.id.layoutGuideList);

        if (userName != null) {
            tvWelcome.setText(userName + "님, 올바른 분리수거를 실천해 보세요!");
        }

        initRecycleData();
        displayAllGuides();

        // 실시간 검색
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterGuides(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initRecycleData() {
        // 카테고리: {품목들, 분리수거 방법}
        recycleData.put("종이류", new String[]{
                "신문지, 책, 노트, 종이컵, 상자, 포장지",
                "물에 젖지 않게 묶어서 배출\n" +
                "- 비닐 코팅된 종이는 일반쓰레기\n" +
                "- 종이컵은 내용물 비우고 헹궈서 배출\n" +
                "- 영수증(감열지)은 일반쓰레기"
        });

        recycleData.put("플라스틱", new String[]{
                "페트병, 플라스틱 용기, 세제 용기, 샴푸통",
                "내용물 비우고 물로 헹군 후 배출\n" +
                "- 라벨(비닐)은 제거 후 별도 배출\n" +
                "- 페트병은 압착하여 뚜껑 닫고 배출\n" +
                "- 펌프 용기의 스프링은 분리하여 배출"
        });

        recycleData.put("유리병", new String[]{
                "소주병, 맥주병, 음료병, 드링크병, 와인병",
                "내용물 비우고 물로 헹군 후 배출\n" +
                "- 병뚜껑은 분리하여 캔류로 배출\n" +
                "- 깨진 유리는 신문지로 감싸서 일반쓰레기\n" +
                "- 도자기, 내열유리는 일반쓰레기"
        });

        recycleData.put("캔/고철", new String[]{
                "음료캔, 맥주캔, 통조림캔, 부탄가스, 철사",
                "내용물 비우고 물로 헹군 후 배출\n" +
                "- 알루미늄캔은 압착하여 배출\n" +
                "- 부탄가스는 구멍 뚫어 가스 제거 후 배출\n" +
                "- 냄비, 프라이팬 등은 고철로 배출"
        });

        recycleData.put("비닐류", new String[]{
                "과자봉지, 라면봉지, 비닐봉지, 에어캡(뽁뽁이)",
                "이물질 제거 후 투명 비닐봉투에 모아 배출\n" +
                "- 이물질 제거가 어려운 비닐은 일반쓰레기\n" +
                "- 깨끗한 비닐만 재활용 가능\n" +
                "- 식탁보, 고무장갑은 일반쓰레기"
        });

        recycleData.put("스티로폼", new String[]{
                "택배 상자, 과일 포장재, 전자제품 완충재",
                "이물질과 테이프 제거 후 배출\n" +
                "- 색깔이 있는 스티로폼은 일반쓰레기\n" +
                "- 건축용 스티로폼은 일반쓰레기\n" +
                "- 컵라면 용기는 깨끗이 씻어서 배출"
        });

        recycleData.put("의류/원단", new String[]{
                "옷, 가방, 담요, 커튼, 이불, 신발",
                "깨끗이 세탁 후 마른 상태로 배출\n" +
                "- 의류수거함에 넣어서 배출\n" +
                "- 오염되거나 훼손된 의류는 일반쓰레기\n" +
                "- 솜이불은 대형폐기물로 배출"
        });

        recycleData.put("폐건전지/형광등", new String[]{
                "건전지, 충전지, 형광등, LED전구",
                "전용 수거함에 배출\n" +
                "- 아파트/주민센터 전용 수거함 이용\n" +
                "- 깨진 형광등은 종량제 봉투에 배출\n" +
                "- 백열전구는 일반쓰레기"
        });

        recycleData.put("음식물쓰레기", new String[]{
                "채소, 과일, 밥, 반찬, 빵, 국물",
                "물기 제거 후 음식물쓰레기 전용봉투에 배출\n" +
                "- 뼈, 조개껍데기, 갑각류 껍질은 일반쓰레기\n" +
                "- 견과류 껍질, 파인애플 껍질은 일반쓰레기\n" +
                "- 양파/마늘/옥수수 껍질은 일반쓰레기\n" +
                "- 달걀 껍데기는 일반쓰레기\n" +
                "- 티백, 한약재 찌꺼기는 일반쓰레기"
        });

        recycleData.put("전자제품/폐가전", new String[]{
                "TV, 냉장고, 세탁기, 에어컨, 컴퓨터, 휴대폰",
                "대형: 무상방문수거 서비스 (1599-0903)\n" +
                "- 소형: 가까운 우체국 또는 주민센터에 배출\n" +
                "- 휴대폰: 대리점 또는 전용 수거함에 배출\n" +
                "- 배터리는 분리하여 전용 수거함에 배출"
        });
    }

    private void displayAllGuides() {
        layoutGuideList.removeAllViews();
        for (Map.Entry<String, String[]> entry : recycleData.entrySet()) {
            addGuideCard(entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
        }
    }

    private void filterGuides(String query) {
        layoutGuideList.removeAllViews();

        if (query.isEmpty()) {
            displayAllGuides();
            return;
        }

        String lowerQuery = query.toLowerCase();
        boolean found = false;

        for (Map.Entry<String, String[]> entry : recycleData.entrySet()) {
            String category = entry.getKey();
            String items = entry.getValue()[0];
            String method = entry.getValue()[1];

            if (category.toLowerCase().contains(lowerQuery) ||
                items.toLowerCase().contains(lowerQuery) ||
                method.toLowerCase().contains(lowerQuery)) {

                addGuideCard(category, items, method);
                found = true;
            }
        }

        if (!found) {
            TextView tvNoResult = new TextView(this);
            tvNoResult.setText("'" + query + "'에 대한 검색 결과가 없습니다.\n\n일반쓰레기로 배출하거나\n관할 지자체에 문의하세요.");
            tvNoResult.setTextSize(16);
            tvNoResult.setPadding(32, 32, 32, 32);
            layoutGuideList.addView(tvNoResult);
        }
    }

    private void addGuideCard(String category, String items, String method) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 20, 24, 20);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);
        card.setBackgroundColor(0xFFF5F5F5);

        // 카테고리 제목
        TextView tvCategory = new TextView(this);
        tvCategory.setText("[ " + category + " ]");
        tvCategory.setTextSize(20);
        tvCategory.setTextColor(0xFF2E7D32);
        tvCategory.setPadding(0, 0, 0, 8);
        card.addView(tvCategory);

        // 해당 품목들
        TextView tvItems = new TextView(this);
        tvItems.setText("품목: " + items);
        tvItems.setTextSize(14);
        tvItems.setTextColor(0xFF555555);
        tvItems.setPadding(0, 0, 0, 12);
        card.addView(tvItems);

        // 분리수거 방법
        final TextView tvMethod = new TextView(this);
        tvMethod.setText(method);
        tvMethod.setTextSize(14);
        tvMethod.setTextColor(0xFF333333);
        tvMethod.setVisibility(View.GONE);
        tvMethod.setPadding(16, 8, 0, 0);
        card.addView(tvMethod);

        // 카드 클릭으로 펼치기/접기
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvMethod.getVisibility() == View.GONE) {
                    tvMethod.setVisibility(View.VISIBLE);
                } else {
                    tvMethod.setVisibility(View.GONE);
                }
            }
        });

        layoutGuideList.addView(card);
    }
}

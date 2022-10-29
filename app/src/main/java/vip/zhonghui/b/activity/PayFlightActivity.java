package vip.zhonghui.b.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.zhonghui.b.R;
import vip.zhonghui.b.adapter.FlightAdapter;
import vip.zhonghui.b.entity.FlightList;
import vip.zhonghui.b.entity.FlightResult;
import vip.zhonghui.b.utils.DateUtil;

public class PayFlightActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String city;
    private String endCity;
    private String eveyDate;
    private FlightAdapter adapter;
    private List<FlightList> flightLists;
    private String[] times = {"时间排序", "从早到晚", "从晚到早"};
    private String[] prices = {"价格排序", "从低到高", "从高到低"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_flight);
        onResult();
        initView();
    }

    // 初始化视图
    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_date = findViewById(R.id.tv_date);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(v -> {
            finish();
        });
        tv_title.setText(city + " --> " + endCity);
        tv_date.setText(eveyDate);
        initAdapter();
        initSpinner();
    }

    // 初始化适配器
    private void initAdapter() {
        ListView lv_flight = findViewById(R.id.lv_flight);
        adapter = new FlightAdapter(this, flightLists);
        lv_flight.setAdapter(adapter);
    }

    // 初始化 Spinner 控件
    private void initSpinner() {
        Spinner sp_Time = findViewById(R.id.sp_time_sort);
        Spinner sp_Price = findViewById(R.id.sp_price_sort);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_text, times);
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_text, prices);
        sp_Time.setAdapter(timeAdapter);
        sp_Price.setAdapter(priceAdapter);
        sp_Time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (times[position]) {
                    case "从早到晚":
                        flightLists.sort(((o1, o2) -> DateUtil.getTime(o1.getDepartTime()) - DateUtil.getTime(o2.getDepartTime())));
                        sp_Price.setSelection(0);
                        adapter.notifyDataSetChanged();
                        break;
                    case "从晚到早":
                        flightLists.sort(((o1, o2) -> DateUtil.getTime(o2.getDepartTime()) - DateUtil.getTime(o1.getDepartTime())));
                        sp_Price.setSelection(0);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_Price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (prices[position]) {
                    case "从低到高":
                        flightLists.sort(((o1, o2) -> Integer.valueOf(o1.getMinPrice()) - Integer.valueOf(o2.getMinPrice())));
                        sp_Time.setSelection(0);
                        adapter.notifyDataSetChanged();
                        break;
                    case "从高到低":
                        flightLists.sort(((o1, o2) -> Integer.valueOf(o2.getMinPrice()) - Integer.valueOf(o1.getMinPrice())));
                        sp_Time.setSelection(0);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // 接收数据
    private void onResult() {
        preferences = getSharedPreferences("zhashut", MODE_PRIVATE);
        city = preferences.getString("city", "");
        endCity = preferences.getString("endCity", "");
        Intent intent = getIntent();
        eveyDate = intent.getStringExtra("eveyDate");
        FlightResult flightResult = (FlightResult) intent.getSerializableExtra("flightResult");
        flightLists = flightResult.getFlightLists();
    }
}
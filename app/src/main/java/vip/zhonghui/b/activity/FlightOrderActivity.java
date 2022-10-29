package vip.zhonghui.b.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.zhonghui.b.R;
import vip.zhonghui.b.adapter.FlightOrderAdapter;
import vip.zhonghui.b.entity.FlightList;
import vip.zhonghui.b.entity.FlightPrice;

public class FlightOrderActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String city;
    private String endCity;
    private String date;
    private String week;
    private String airline;
    private String flightNo;
    private String departTime;
    private String departPort;
    private String departT;
    private String arrivalTime;
    private String arrivalPort;
    private String arrivalT;
    private List<FlightPrice> flightPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_order);
        onResult();
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        ImageView iv_icon = findViewById(R.id.iv_icon);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_airline = findViewById(R.id.tv_airline);
        TextView tv_flightno = findViewById(R.id.tv_flightno);
        TextView tv_date = findViewById(R.id.tv_date);
        TextView tv_week = findViewById(R.id.tv_week);
        TextView tv_depart_time = findViewById(R.id.tv_depart_time);
        TextView tv_arrival_time = findViewById(R.id.tv_arrival_time);
        TextView tv_depart_port = findViewById(R.id.tv_depart_port);
        TextView tv_arrival_port = findViewById(R.id.tv_arrival_port);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(v -> {
            finish();
        });
        iv_icon.setImageResource(R.drawable.planeicon);
        tv_title.setText(city + " --> " + endCity);
        tv_airline.setText(airline);
        tv_flightno.setText(flightNo);
        tv_date.setText(date);
        tv_week.setText(week);
        tv_depart_time.setText(departTime);
        tv_depart_port.setText(departPort + departT);
        tv_arrival_time.setText(arrivalTime);
        tv_arrival_port.setText(arrivalPort + arrivalT);
        initAdapter();
    }

    private void initAdapter() {
        ListView lv_order = findViewById(R.id.lv_order);
        FlightOrderAdapter adapter = new FlightOrderAdapter(this, flightPrices);
        lv_order.setAdapter(adapter);
    }

    private void onResult() {
        Intent intent = getIntent();
        preferences = getSharedPreferences("zhashut", MODE_PRIVATE);
        city = preferences.getString("city", "");
        endCity = preferences.getString("endCity", "");
        date = preferences.getString("date", "");
        week = preferences.getString("week", "");
        FlightList flightList = (FlightList) intent.getSerializableExtra("info");
        airline = flightList.getAirline();
        flightNo = flightList.getFlightNo();
        departTime = flightList.getDepartTime();
        departPort = flightList.getDepartPort();
        departT = flightList.getDepartT();
        arrivalTime = flightList.getArrivalTime();
        arrivalPort = flightList.getArrivalPort();
        arrivalT = flightList.getArrivalT();
        flightPrices = flightList.getFlightPrices();
    }
}
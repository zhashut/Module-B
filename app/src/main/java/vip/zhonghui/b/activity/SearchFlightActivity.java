package vip.zhonghui.b.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import vip.zhonghui.b.R;
import vip.zhonghui.b.entity.FlightInfo;
import vip.zhonghui.b.entity.FlightResult;
import vip.zhonghui.b.utils.DateUtil;
import vip.zhonghui.b.utils.HttpUtil;

public class SearchFlightActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private SharedPreferences preferences;
    private EditText et_from;
    private EditText et_to;
    private TextView tv_date;
    private TextView tv_week;
    private Calendar calendar;
    private String city;
    private String endCity;
    private ImageView iv_rotate;
    private TextView tv_wait;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                FlightInfo flightInfo = (FlightInfo) msg.obj;
                if (flightInfo == null) {
                    Toast.makeText(SearchFlightActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                searchSuccess(flightInfo);
            }
        }
    };

    /**
     * 查询航班
     *
     * @param flightInfo
     */
    private void searchSuccess(FlightInfo flightInfo) {
        FlightResult flightResult = flightInfo.getFlightResult();
        Intent intent = new Intent(this, PayFlightActivity.class);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("city", city);
        edit.putString("endCity", endCity);
        edit.putString("date", tv_date.getText().toString());
        edit.putString("week", tv_week.getText().toString());
        String eveyDate = DateUtil.getEveyDate(tv_date.getText().toString());
        intent.putExtra("eveyDate", eveyDate);
        intent.putExtra("flightResult", flightResult);
        edit.commit();
        startActivity(intent);
        iv_rotate.clearAnimation();
        iv_rotate.setVisibility(View.GONE);
        tv_wait.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        reload();
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        TextView tv_title = findViewById(R.id.tv_title);
        et_from = findViewById(R.id.et_from);
        et_to = findViewById(R.id.et_to);
        tv_date = findViewById(R.id.tv_date);
        tv_week = findViewById(R.id.tv_week);
        iv_rotate = findViewById(R.id.iv_rotate);
        tv_wait = findViewById(R.id.tv_wait);
        calendar = Calendar.getInstance();
        tv_title.setText("机票查询");
        et_from.setText(city);
        et_to.setText(endCity);
        tv_date.setText(DateUtil.getDate(calendar));
        tv_week.setText(DateUtil.getWeek(calendar));
        tv_date.setOnClickListener(this);
        findViewById(R.id.iv_reversal).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date:
                DatePickerDialog dialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dialog.create();
                dialog.show();
                break;
            case R.id.iv_reversal:
                String from = et_from.getText().toString();
                String to = et_to.getText().toString();
                et_from.setText(to);
                et_to.setText(from);
                break;
            case R.id.btn_search:
                city = et_from.getText().toString().trim();
                endCity = et_to.getText().toString().trim();
                if (TextUtils.isEmpty(city) || TextUtils.isEmpty(endCity)) {
                    Toast.makeText(this, "请补充完整信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                String date = tv_date.getText().toString();

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
                iv_rotate.setVisibility(View.VISIBLE);
                tv_wait.setVisibility(View.VISIBLE);
                iv_rotate.startAnimation(animation);

                HttpUtil.doGetFlight(city, endCity, date, new Callback() {
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String flightJson = response.body().string();
                        Gson gson = new Gson();
                        FlightInfo flightInfo = gson.fromJson(flightJson, FlightInfo.class);
                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = flightInfo;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }
                });
                break;
        }
    }

    /**
     * 第二次登录进入该界面时，显示上一次的行程
     */
    private void reload() {
        preferences = getSharedPreferences("zhashut", MODE_PRIVATE);
        city = preferences.getString("city", "");
        endCity = preferences.getString("endCity", "");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tv_date.setText(DateUtil.getDate(calendar));
        tv_week.setText(DateUtil.getWeek(calendar));
    }
}
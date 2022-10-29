package vip.zhonghui.b.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import vip.zhonghui.b.R;
import vip.zhonghui.b.utils.TextUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private SharedPreferences preferences;
    private String username;
    private String password;
    private EditText et_name;
    private EditText et_psw;
    private CheckBox ck_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        reload();
    }

    private void initView() {
        preferences = getSharedPreferences("zhashut", MODE_PRIVATE);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        et_name.addTextChangedListener(this);
        et_psw.addTextChangedListener(this);
        ck_remember = findViewById(R.id.ck_remember);
        ck_remember.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isRemember", isChecked);
            edit.commit();
        });
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    /**
     * 接收数据
     */
    private void onResult() {
        username = preferences.getString("username", "");
        password = preferences.getString("password", "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                onResult();
                String inputName = et_name.getText().toString().trim();
                String inputPsw = et_psw.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(this, "登录失败，用户不存在，请先注册", Toast.LENGTH_SHORT).show();
                    et_psw.setText("");
                    return;
                } else if (!inputName.equals(username)) {
                    Toast.makeText(this, "登录失败，用户名不存在，请先注册", Toast.LENGTH_SHORT).show();
                    et_psw.setText("");
                    return;
                } else if (!inputPsw.equals(password)) {
                    Toast.makeText(this, "登录失败，密码错误", Toast.LENGTH_SHORT).show();
                    et_psw.setText("");
                    return;
                }
                loginSuccess();
                break;
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 登录成功
     */
    private void loginSuccess() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(username + ",欢迎登录");
        dialog.setPositiveButton("确定", (dialog1, which) -> {
            Intent intent = new Intent(this, SearchFlightActivity.class);
            startActivity(intent);
        });
        dialog.create();
        dialog.show();
    }

    /**
     * 记住密码
     */
    private void reload() {
        boolean isRemember = preferences.getBoolean("isRemember", false);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        if (isRemember) {
            et_name.setText(username);
            et_psw.setText(password);
            ck_remember.setChecked(true);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TextUtil.limitInput(et_name);
        TextUtil.limitInput(et_psw);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
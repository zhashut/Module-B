package vip.zhonghui.b.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vip.zhonghui.b.R;
import vip.zhonghui.b.utils.TextUtil;

public class RegisterActivity extends AppCompatActivity implements TextWatcher {

    private SharedPreferences preferences;
    private EditText et_name;
    private EditText et_psw;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("zhashut", MODE_PRIVATE);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        et_name.addTextChangedListener(this);
        et_psw.addTextChangedListener(this);
        findViewById(R.id.btn_register).setOnClickListener(v -> {
            username = et_name.getText().toString().trim();
            password = et_psw.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "注册失败，用户名不得为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.length() < 8) {
                Toast.makeText(this, "注册失败，密码不得少于8位大于16位", Toast.LENGTH_SHORT).show();
                return;
            }
            registerSuccess();
        });
    }

    /**
     * 注册成功
     */
    private void registerSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("username", username);
        edit.putString("password", password);
        edit.commit();
        startActivity(intent);
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 限制输入，只能输入数字和字母
     * @param s
     * @param start
     * @param before
     * @param count
     */
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
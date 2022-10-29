package vip.zhonghui.b.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:17
 * Description: No Description
 */
public class TextUtil {

    public static void limitInput(EditText et) {
        String etStr = et.getText().toString();
        String regEx = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(etStr);
        String resStr = matcher.replaceAll("").trim();
        if (!etStr.equals(resStr)) {
            et.setText(resStr);
            et.setSelection(resStr.length());
        }
    }

}

package vip.zhonghui.b.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:15
 * Description: No Description
 */
public class HttpUtil {

    private static final String APP_KEY = "";

    private static final String REQUEST_URL = "https://api.jisuapi.com/flight/query?";

    public static void doGetFlight(String city, String endCity, String date, okhttp3.Callback callback) {
        final String FLIGHT_URL = REQUEST_URL + "city=" + city + "&endcity=" + endCity + "&date=" + date + "&appkey=" + APP_KEY;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(FLIGHT_URL).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}

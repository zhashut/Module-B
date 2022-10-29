package vip.zhonghui.b.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:21
 * Description: No Description
 */
public class FlightPrice implements Serializable {

    @SerializedName("cabinname")
    private String cabinName;

    @SerializedName("cabincode")
    private String cabinCode;

    private String price;

    private String discount;

    public String getCabinName() {
        return cabinName;
    }

    public String getCabinCode() {
        return cabinCode;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }
}

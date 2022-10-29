package vip.zhonghui.b.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:21
 * Description: No Description
 */
public class FlightResult implements Serializable {

    @SerializedName("list")
    private List<FlightList> flightLists;

    public List<FlightList> getFlightLists() {
        return flightLists;
    }
}

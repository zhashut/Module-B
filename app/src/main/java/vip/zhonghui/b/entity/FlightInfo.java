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
public class FlightInfo implements Serializable {

    @SerializedName("result")
    private FlightResult flightResult;

    public FlightResult getFlightResult() {
        return flightResult;
    }

    public void setFlightResult(FlightResult flightResult) {
        this.flightResult = flightResult;
    }
}

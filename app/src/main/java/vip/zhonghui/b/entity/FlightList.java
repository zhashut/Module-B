package vip.zhonghui.b.entity;

import com.google.gson.annotations.SerializedName;

import java.io.PipedReader;
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
public class FlightList implements Serializable {

    @SerializedName("flightno")
    private String flightNo;

    private String airline;

    @SerializedName("departport")
    private String departPort;

    @SerializedName("arrivalport")
    private String arrivalPort;

    @SerializedName("departterminal")
    private String departT;

    @SerializedName("arrivalterminal")
    private String arrivalT;

    @SerializedName("departtime")
    private String departTime;

    @SerializedName("arrivaltime")
    private String arrivalTime;

    @SerializedName("minprice")
    private String minPrice;

    @SerializedName("pricelist")
    private List<FlightPrice> flightPrices;

    public String getFlightNo() {
        return flightNo;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartPort() {
        return departPort;
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public String getDepartT() {
        return departT;
    }

    public String getArrivalT() {
        return arrivalT;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public List<FlightPrice> getFlightPrices() {
        return flightPrices;
    }
}

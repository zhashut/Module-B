package vip.zhonghui.b.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vip.zhonghui.b.R;
import vip.zhonghui.b.activity.FlightOrderActivity;
import vip.zhonghui.b.entity.FlightList;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:46
 * Description: No Description
 */
public class FlightAdapter extends BaseAdapter {

    private Context context;
    private List<FlightList> flightLists;

    public FlightAdapter(Context context, List<FlightList> flightLists) {
        this.context = context;
        this.flightLists = flightLists;
    }

    @Override
    public int getCount() {
        return flightLists == null ? 0 : flightLists.size();
    }

    @Override
    public Object getItem(int position) {
        return flightLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_flight_layout, null);
            holder = new ViewHolder();
            holder.departTime = convertView.findViewById(R.id.tv_depart_time);
            holder.arrivalTime = convertView.findViewById(R.id.tv_arrival_time);
            holder.departPort = convertView.findViewById(R.id.tv_depart_port);
            holder.arrivalPort = convertView.findViewById(R.id.tv_arrival_port);
            holder.airline = convertView.findViewById(R.id.tv_airline);
            holder.flightNo = convertView.findViewById(R.id.tv_flightno);
            holder.minPrice = convertView.findViewById(R.id.tv_min_price);
            holder.icon = convertView.findViewById(R.id.iv_icon);
            holder.ll_flight = convertView.findViewById(R.id.ll_flight);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FlightList info = flightLists.get(position);
        holder.departTime.setText(info.getDepartTime());
        holder.arrivalTime.setText(info.getArrivalTime());
        holder.departPort.setText(info.getDepartPort() + info.getDepartT());
        holder.arrivalPort.setText(info.getArrivalPort() + info.getArrivalT());
        holder.airline.setText(info.getAirline());
        holder.flightNo.setText(info.getFlightNo());
        holder.minPrice.setText("￥" + info.getMinPrice());
        holder.icon.setImageResource(R.drawable.planeicon);
        holder.ll_flight.setOnClickListener(v -> {
            Intent intent = new Intent(context, FlightOrderActivity.class);
            intent.putExtra("info", info);
            context.startActivity(intent);
        });
        return convertView;
    }

    class ViewHolder {
        public TextView departTime;
        public TextView arrivalTime;
        public TextView departPort;
        public TextView arrivalPort;
        public TextView airline;
        public TextView flightNo;
        public TextView minPrice;
        public ImageView icon;
        public LinearLayout ll_flight;
    }
}

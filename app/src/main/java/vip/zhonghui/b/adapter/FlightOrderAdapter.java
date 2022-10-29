package vip.zhonghui.b.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vip.zhonghui.b.R;
import vip.zhonghui.b.entity.FlightPrice;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 4:18
 * Description: No Description
 */
public class FlightOrderAdapter extends BaseAdapter {

    private Context context;
    private List<FlightPrice> flightPrices;

    public FlightOrderAdapter(Context context, List<FlightPrice> flightPrices) {
        this.context = context;
        this.flightPrices = flightPrices;
    }

    @Override
    public int getCount() {
        return flightPrices == null ? 0 : flightPrices.size();
    }

    @Override
    public Object getItem(int position) {
        return flightPrices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_layout, null);
            holder = new ViewHolder();
            holder.price = convertView.findViewById(R.id.tv_price);
            holder.discount = convertView.findViewById(R.id.tv_discount);
            holder.cabinCode = convertView.findViewById(R.id.tv_cabincpde);
            holder.cabinName = convertView.findViewById(R.id.tv_cabinname);
            holder.btn_order = convertView.findViewById(R.id.btn_order);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FlightPrice info = flightPrices.get(position);
        holder.price.setText("￥" + info.getPrice());
        holder.discount.setText("优惠" + info.getDiscount() + "元");
        holder.cabinCode.setText(info.getCabinCode() + "舱");
        holder.cabinName.setText(info.getCabinName());
        holder.btn_order.setOnClickListener(v -> {
            Toast.makeText(context, "抱歉，此功能暂未开发", Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }

    class ViewHolder {
        public TextView price;
        public TextView discount;
        public TextView cabinCode;
        public TextView cabinName;
        public Button btn_order;
    }
}

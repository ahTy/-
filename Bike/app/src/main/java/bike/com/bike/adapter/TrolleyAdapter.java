package bike.com.bike.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bike.com.bike.R;
import bike.com.bike.entity.Goods;

/**
 * Created by TY on 2017/7/18.
 */

public class TrolleyAdapter extends BaseAdapter {
    private List<Goods> mData;
    private Context mContext;


    public TrolleyAdapter(List<Goods> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        //当前数据
        Goods goods =this.mData.get(i);
        View v =
                LayoutInflater.from(mContext).inflate(R.layout.troll_item_layout,null);
        ImageView img=(ImageView)v.findViewById(R.id.img);
        TextView brand=(TextView)v.findViewById(R.id.brand);
        TextView description=(TextView)v.findViewById(R.id.description);
        TextView count=(TextView)v.findViewById(R.id.count);
        TextView price=(TextView)v.findViewById(R.id.price);

        img.setImageResource(goods.getResid());
        brand.setText(goods.getBrand());
        //description.setText(goods.getDescription());
        description.setText(Html.fromHtml(goods.getDescription()));
        count.setText("x"+goods.getCount());
        price.setText(goods.getPrice());

        return v;
    }
}

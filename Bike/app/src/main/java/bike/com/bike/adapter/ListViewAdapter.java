package bike.com.bike.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import bike.com.bike.R;
import bike.com.bike.entity.Goods;

import java.util.List;

/**
 * Created by TY on 2017/5/14.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Goods> mData;
    private Context mContext;


    public ListViewAdapter(List<Goods> mData, Context mContext) {
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
                LayoutInflater.from(mContext).inflate(R.layout.pay_item_layout,null);
        ImageView img=(ImageView)v.findViewById(R.id.img);
        TextView brand=(TextView)v.findViewById(R.id.brand);
        TextView description=(TextView)v.findViewById(R.id.description);

        img.setImageResource(goods.getResid());
        brand.setText(goods.getBrand());
        //description.setText(goods.getDescription());
        description.setText(Html.fromHtml(goods.getDescription()));

        return v;
    }
}

package bike.com.bike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bike.com.bike.R;
import bike.com.bike.entity.News;

/**
 * Created by TY on 2017/5/22.
 */

//继承自BaseAdapter父类
public class NewsAdapter extends BaseAdapter {
    private ArrayList<News> mData;
    private Context mContext;

    public NewsAdapter(ArrayList<News> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News n=mData.get(position);

        View item = LayoutInflater.from(mContext).inflate(R.layout.news_item,null);//根据item生成布局
        ImageView img=(ImageView)item.findViewById(R.id.img);   //将item里的img对象的信息赋值给img对象
        TextView title=(TextView)item.findViewById(R.id.title);
        TextView ctime=(TextView)item.findViewById(R.id.ctime);

        title.setText(n.getTitle());
        ctime.setText(n.getCtime());
        String url=n.getPicUrl();
        Glide.with(mContext).load(url).into(img);

        return item;
    }
}

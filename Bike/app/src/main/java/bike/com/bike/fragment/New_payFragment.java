package bike.com.bike.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import bike.com.bike.GoodsActivity;
import bike.com.bike.HomeActivity;
import bike.com.bike.R;
import bike.com.bike.adapter.ListViewAdapter;
import bike.com.bike.adapter.RollPageViewAdapter;
import bike.com.bike.entity.Goods;
import bike.com.bike.entity.News;
import bike.com.bike.manager.GoodsManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class New_payFragment extends Fragment {
    private ListView lv;
    private List<Goods> mHotData;  //首页热销商品数据集合
    private ListViewAdapter lvAdapter;
    private RollPageViewAdapter rpvAdapter;
    private RollPagerView rpv;//轮播控件
    private List<ImageView> rpvData;//轮播的图片数据

    public New_payFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_pay, container, false);
        lv = (ListView)view.findViewById(R.id.lv);

        initView();
        mloadLBData();
        loadLvHotData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods goods = mHotData.get(position-1);
                Intent intent=new Intent(getActivity(),GoodsActivity.class);
                intent.putExtra("brand",goods.getBrand());
                intent.putExtra("category",goods.getCategory());
                intent.putExtra("goodId",goods.getGoodId());
                intent.putExtra("goodsName",goods.getGoodsName());
                intent.putExtra("count",goods.getCount());
                intent.putExtra("price",goods.getPrice());
                intent.putExtra("size",goods.getSize());
                intent.putExtra("description",goods.getDescription());
                intent.putExtra("resid",goods.getResid());
                startActivity(intent);
            }
        });
        return view;
    }
    //初始化视图
    private void initView(){
        mHotData = new ArrayList<>();
        //适配器
        lvAdapter = new ListViewAdapter(mHotData,getActivity());
        lv.setAdapter(lvAdapter);
        //定义ListView的头部布局
        View headerView =
                LayoutInflater.from(getActivity()).inflate(R.layout.pay_header_layout,null);
        //轮播控件
        rpv = (RollPagerView)headerView.findViewById(R.id.rpv);
        rpvData = new ArrayList<>();
        rpvAdapter = new RollPageViewAdapter(rpvData);
        rpv.setAdapter(rpvAdapter);
        rpv.setPlayDelay(2500);
        rpv.setAnimationDurtion(1000);
        lv.addHeaderView(headerView);
    }

    //加载轮播数据
    private void mloadLBData(){
        ArrayList<Goods> mGoods = GoodsManager.getInstacne().getGoods();
        rpvData.clear();
        for(Goods g : mGoods) {
            if(g.getCategory()=="new"){
                ImageView imgView = new ImageView(getContext());
                imgView.setScaleType(ImageView.ScaleType.FIT_XY);
                imgView.setImageResource(g.getResid());
                rpvData.add(imgView);
            }
        }
        rpvAdapter.notifyDataSetChanged();
    }

    //加载LV热销商品
    private void loadLvHotData(){
        ArrayList<Goods> mGoods = GoodsManager.getInstacne().getGoods();
        mHotData.clear();
        for(Goods g : mGoods) {
            if(g.getCategory()=="new"){
                mHotData.add(g);
            }
        }
        lvAdapter.notifyDataSetChanged();
    }
}
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

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import bike.com.bike.GoodsActivity;
import bike.com.bike.R;
import bike.com.bike.adapter.ListViewAdapter;
import bike.com.bike.adapter.RollPageViewAdapter;
import bike.com.bike.adapter.TrolleyAdapter;
import bike.com.bike.entity.Goods;
import bike.com.bike.manager.GoodsManager;
import bike.com.bike.manager.TrolleyManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrolleyFragment extends Fragment {
    private ListView lv;
    private List<Goods> mHotData;
    private TrolleyAdapter tlAdapter;

    public TrolleyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trolley, container, false);
        lv = (ListView)view.findViewById(R.id.lv);


        initView();
        loadTrolleyData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods goods = mHotData.get(position);
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){

        }else{
            initView();
            loadTrolleyData();
        }
    }

    //初始化视图
    private void initView(){
        mHotData = new ArrayList<>();
        //适配器
        tlAdapter = new TrolleyAdapter(mHotData,getActivity());
        lv.setAdapter(tlAdapter);
    }
    //加载购物车数据
    private void loadTrolleyData(){
        ArrayList<Goods> tGoods = TrolleyManager.getInstance().getGoods();
        mHotData.clear();
        for(Goods g : tGoods) {
            mHotData.add(g);
        }
        tlAdapter.notifyDataSetChanged();
    }
}
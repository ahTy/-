package bike.com.bike.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bike.com.bike.R;
import bike.com.bike.adapter.NewsAdapter;
import bike.com.bike.entity.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mine_NewsFragment extends Fragment implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    public ArrayList<News> mNewsData;
    private ListView lv;
    private RequestQueue mQueue;  //请求队列
    private NewsAdapter adapter;
    private RadioGroup newsbt;


    private String apikey="6186142e92cb77b768019d44aa5996fd";
    private String url="https://api.tianapi.com/health/?key="+apikey+"&num=10";

    public Mine_NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mine__news, container, false);

        lv=(ListView) view.findViewById(R.id.lv);
        mNewsData = new ArrayList<>();
        adapter =new NewsAdapter(mNewsData,getContext()) ;//建立适配器并传参（mData,mContext）
        lv.setAdapter(adapter);     //关联ListView试图和数据适配器
        lv.setOnItemClickListener(this);    //给每一个选项添加监听

        newsbt=(RadioGroup)view.findViewById(R.id.newsbt);
        newsbt.setOnCheckedChangeListener(this);
        newsbt.check(R.id.first);

//        wv = (WebView) findViewById(R.id.webView1);
        return view;
    }

    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
    }

    private void loadData() {
        mQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //JSON数据解析
                        Gson gson = new Gson();
                        try {
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject= jsonParser.parse(response).getAsJsonObject();
                            JsonArray array=jsonObject.get("newslist").getAsJsonArray();//仅取出newslist的内容
                            List<News> newsList =
                                    gson.fromJson(array, new TypeToken<List<News>>() {
                                    }.getType());
                            mNewsData.clear();
                            for (News n : newsList) {
                                mNewsData.add(n);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "网络异常！", Toast.LENGTH_LONG).show();
                    }
                }
        );
        //将请求添加的请求队列
        mQueue.add(request);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News n=(News) parent.getAdapter().getItem(position);
        Uri uri = Uri.parse(n.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
//        wv.getSettings().setJavaScriptEnabled(true);
////        wv.setScrollBarStyle(0);
//        WebSettings webSettings = wv.getSettings();
//        webSettings.setAllowFileAccess(true);
//        webSettings.setBuiltInZoomControls(true);
//        wv.loadUrl("http://www.baidu.com");
////加载数据
//        wv.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress == 100) {
//                    HomeActivity.this.setTitle("加载完成");
//                } else {
//                    HomeActivity.this.setTitle("加载中.......");
//
//
//                }
//            }
//        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.first:
                url="https://api.tianapi.com/health/?key="+apikey+"&num=10";
                loadData();
            break;
            case R.id.second:

                url="https://api.tianapi.com/travel/?key="+apikey+"&num=10";
                loadData();
                break;
            case R.id.third:
                url="https://api.tianapi.com/mobile/?key="+apikey+"&num=10";
                loadData();
                break;
            case R.id.forth:
                url="https://api.tianapi.com/it/?key="+apikey+"&num=10";
                loadData();
                break;
        }
    }
}

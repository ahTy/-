package bike.com.bike.manager;

import java.util.ArrayList;

import bike.com.bike.R;
import bike.com.bike.entity.Goods;

/**
 * Created by TY on 2017/7/18.
 */

public class TrolleyManager {
    private static TrolleyManager instance;
    private ArrayList<Goods> tGoods;
    private TrolleyManager(){
        this.tGoods=new ArrayList<>();
        Goods goods = new Goods("捷安特","new","1001","2018款XTC 800","1","2700元","27.5*18","轻量利器，竞赛几何，只为荣誉而战，XTC 800 再创27.5 寸轮径典范。",R.drawable.n1001);
        this.tGoods.add(goods);
    }
    public static TrolleyManager getInstance(){
        if(instance==null)
            instance = new TrolleyManager();
        return instance;
    }
    public ArrayList<Goods> getGoods(){
        return tGoods;
    }
    public void setGoods(String brand,String category,String goodId,String goodsName,String imgUrl,String price,String size,String description,int resid){
        Goods goods = new Goods(brand,category,goodId,goodsName,imgUrl,price,size,description,resid);
        boolean flag = false;
        for(Goods g : tGoods){
            if(g.getGoodId().equals(goodId)){
                g.setCount((Integer.parseInt(g.getCount())+1)+"");
                flag = true;
            }
        }
        if(!flag) {
            this.tGoods.add(goods);
        }
    }
}

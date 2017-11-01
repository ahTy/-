package bike.com.bike.manager;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import bike.com.bike.R;
import bike.com.bike.entity.Goods;

/**
 * Created by TY on 2017/7/17.
 */

public class GoodsManager {
    private static GoodsManager instance;
    private ArrayList<Goods> mGoods;
    private GoodsManager(){
        this.mGoods = new ArrayList<>();
        //新车
        Goods goods = new Goods("捷安特","new","1001","2018款XTC 800","100","2700元","27.5*18","&nbsp;&nbsp;&nbsp;&nbsp;轻量利器，竞赛几何，只为荣誉而战，XTC 800 再创27.5 寸轮径典范。",R.drawable.n1001);
        this.mGoods.add(goods);
        goods = new Goods("美利达","new","1002","维多利亚600","100","2198元","27.5*18","&nbsp;&nbsp;&nbsp;&nbsp;经典的钻石车架造型，更加具有青春运动感。",R.drawable.n1002);
        this.mGoods.add(goods);
        goods = new Goods("永久","new","1003","790标准版","100","1645元","24寸","&nbsp;&nbsp;&nbsp;&nbsp;2017夏季铝合金车架，男女款21速双碟刹。",R.drawable.n1003);
        this.mGoods.add(goods);
        goods = new Goods("永久","new","1004","YE880","100","888元","26英寸","&nbsp;&nbsp;&nbsp;&nbsp;男女式山地车新27速高配红蓝色，铝合金线/油碟刹。",R.drawable.n1004);
        this.mGoods.add(goods);
        goods = new Goods("凤凰","new","1005","700C途锐","100","988元","26英寸","&nbsp;&nbsp;&nbsp;&nbsp;700C铝合金车架，避震前叉，浩盟铝轮盘，密封中轴，机械碟刹。",R.drawable.n1005);
        this.mGoods.add(goods);

        //二手车
        goods = new Goods("捷安特","use","2001","Rincon 620-HD","100","800元","26英寸","Rincon严控质量标准，为您提供更加舒适的运动骑行。",R.drawable.u2001);
        this.mGoods.add(goods);
        goods = new Goods("捷安特","use","2002","Escape R3","100","900元","26英寸","Escape R3平把公路车采用不同于山地车的设计，在保证刚性的同时更加轻量化。",R.drawable.u2002);
        this.mGoods.add(goods);
        goods = new Goods("美利达","use","2003","挑战者600","100","1900元","26*15","独特的焊接技术达到平滑的焊接线，不仅美观，同时在焊接结构的强度丝毫不打折。",R.drawable.u2003);
        this.mGoods.add(goods);
        goods = new Goods("美利达","use","2004","2016款挑战者300","100","1500元","26*15","整车涂装更偏向运动感，但兼顾大气的同时不失稳重，彩色线条搭配更显时尚。",R.drawable.u2004);
        this.mGoods.add(goods);
        goods = new Goods("喜德盛","use","2005","逐日800","100","1000元","26*15.5","不同路况适配不同弹性，起伏路弹性舒适，越野性强，平路可锁死无避震，保护车架！",R.drawable.r3005);
        this.mGoods.add(goods);


        //租车
        goods = new Goods("崔克","ren","3001","Marlin 6","10","10元/小时","27.5*15.5","&nbsp;&nbsp;&nbsp;&nbsp;每一种车架尺寸配备恰当的车轮尺寸，性能卓越，外加无与伦比的骑行体验，使 Marlin 成为越野车手的完美选择。",R.drawable.r3001);
        this.mGoods.add(goods);
        goods = new Goods("喜德盛","ren","3002","传奇500","10","10元/小时","26英寸","&nbsp;&nbsp;&nbsp;&nbsp;传奇500轻松政府各种复杂路况。毋庸置疑的混合路面之王！",R.drawable.r3002);
        this.mGoods.add(goods);
        goods = new Goods("喜德盛","ren","3003","英雄300","10","10元/小时","27.5*16","&nbsp;&nbsp;&nbsp;&nbsp;XDS小燕把+志庆车头碗组+Shimano变速把手设计合理，操作迅捷，不负喜德盛性价比之王的称号。",R.drawable.r3003);
        this.mGoods.add(goods);
        goods = new Goods("永久","ren","3004","龙骑33速","10","10元/小时","26*17","&nbsp;&nbsp;&nbsp;&nbsp;根据行驶速度与自身体力，自由调节变速，驾驭感十足。",R.drawable.r3004);
        this.mGoods.add(goods);
        goods = new Goods("喜德盛","ren","3005","侠客530","10","10元/小时","27.5*15","&nbsp;&nbsp;&nbsp;&nbsp;X6超轻铝合金车架，27.5英寸大轮组，超轻操控，助你探索未知地域，畅享无限骑趣。",R.drawable.r3005);
        this.mGoods.add(goods);


    }
    public static GoodsManager getInstacne(){
        if(instance==null)
            instance = new GoodsManager();
        return instance;
    }

    public ArrayList<Goods> getGoods(){
        return mGoods;
    }
}

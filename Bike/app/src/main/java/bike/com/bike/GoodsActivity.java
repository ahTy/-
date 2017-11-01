package bike.com.bike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bike.com.bike.entity.Goods;
import bike.com.bike.fragment.TrolleyFragment;
import bike.com.bike.manager.GoodsManager;
import bike.com.bike.manager.TrolleyManager;

public class GoodsActivity extends AppCompatActivity {
    private ImageView img;
    private TextView name;
    private TextView price;
    private TextView brand;
    private TextView id;
    private TextView category;
    private TextView size;
    private Button shop;
    private Button buy;

    private String gbrand;
    private String gcategory;
    private String gid;
    private String gname;
    private String gcount;
    private String gprice;
    private String gsize;
    private String gdescription;
    private int gresid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_goods);
        img=(ImageView)findViewById(R.id.good_img);
        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        brand=(TextView)findViewById(R.id.brand);
        id=(TextView)findViewById(R.id.id);
        category=(TextView)findViewById(R.id.category);
        size=(TextView)findViewById(R.id.size);
        shop=(Button)findViewById(R.id.shop);
        buy=(Button)findViewById(R.id.buy);

        Intent intent=getIntent();
        gcount=intent.getStringExtra("count");
        gname=intent.getStringExtra("goodsName");
        gprice=intent.getStringExtra("price");
        gbrand=intent.getStringExtra("brand");
        gcategory=intent.getStringExtra("category");
        gid=intent.getStringExtra("goodId");
        gsize=intent.getStringExtra("size");
        gdescription=intent.getStringExtra("description");
        gresid=intent.getIntExtra("resid",0);

        name.setText(gname);
        price.setText(gprice);
        img.setImageResource(gresid);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        id.setText(gid);
        brand.setText(gbrand);
        category.setText(gcategory);
        String s=gsize;
        size.setText(s);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsActivity.this,"加入购物车成功！",Toast.LENGTH_SHORT).show();
                TrolleyManager.getInstance().setGoods(gbrand,gcategory,gid,gname,"1",gprice,gsize,gdescription,gresid);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsActivity.this,"购买成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

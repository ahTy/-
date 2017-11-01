package bike.com.bike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.baidu.mapapi.SDKInitializer;
import bike.com.bike.fragment.PayFragment;
import bike.com.bike.fragment.HomeFragment;
import bike.com.bike.fragment.MineFragment;
import bike.com.bike.fragment.Mine_NewsFragment;
import bike.com.bike.fragment.ActiviteFragment;
import bike.com.bike.fragment.TrolleyFragment;

public class HomeActivity extends AppCompatActivity
        implements
                    RadioGroup.OnCheckedChangeListener {
    private HomeFragment homeFragment;
    private ActiviteFragment activiteFragment;
    private PayFragment payFragment;
    private TrolleyFragment trolleyFragment;
    private MineFragment mineFragment;
    private Mine_NewsFragment mine_newsFragmentmin;
    private Mine_NewsFragment mine_newsFragment;
    private RadioGroup tabsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_home);
        initFragments();
        getSupportActionBar().hide();

        tabsContainer=(RadioGroup)findViewById(R.id.tabContainer);
        tabsContainer.setOnCheckedChangeListener(this);//给tabsContainer添加监听
        tabsContainer.check(R.id.home);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.home:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(activiteFragment)
                        .hide(payFragment)
                        .hide(trolleyFragment)
                        .hide(mineFragment)
                        .hide(mine_newsFragment)
                        .hide(mine_newsFragmentmin)
                        .commit();
                break;
            case R.id.pay:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .show(payFragment)
                        .hide(activiteFragment)
                        .hide(trolleyFragment)
                        .hide(mineFragment)
                        .hide(mine_newsFragment)
                        .hide(mine_newsFragmentmin)
                        .commit();
                break;
            case R.id.activite:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(payFragment)
                        .show(activiteFragment)
                        .hide(trolleyFragment)
                        .hide(mineFragment)
                        .hide(mine_newsFragment)
                        .hide(mine_newsFragmentmin)
                        .commit();
                break;
            case R.id.trolley:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(activiteFragment)
                        .show(trolleyFragment)
                        .hide(payFragment)
                        .hide(mineFragment)
                        .hide(mine_newsFragment)
                        .hide(mine_newsFragmentmin)
                        .commit();
                break;
            case R.id.mine:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .show(mineFragment)
                        .show(mine_newsFragmentmin)
                        .hide(mine_newsFragment)
                        .hide(activiteFragment)
                        .hide(payFragment)
                        .hide(trolleyFragment)
                        .commit();
                break;
        }
    }

    private void initFragments()
    {
        homeFragment=new HomeFragment();
        payFragment=new PayFragment();
        activiteFragment =new ActiviteFragment();
        trolleyFragment=new TrolleyFragment();
        mineFragment=new MineFragment();
        mine_newsFragment=new Mine_NewsFragment();
        mine_newsFragmentmin=new Mine_NewsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content,homeFragment)
                .add(R.id.content,payFragment)
                .add(R.id.content, activiteFragment)
                .add(R.id.content,trolleyFragment)
                .add(R.id.content,mineFragment)
                .add(R.id.content,mine_newsFragment)
                .add(R.id.contentm,mine_newsFragmentmin)
                .commit();
    }
}



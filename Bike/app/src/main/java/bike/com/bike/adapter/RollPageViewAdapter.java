package bike.com.bike.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by TY on 2017/5/14.
 */
public class RollPageViewAdapter extends StaticPagerAdapter{
    private List<ImageView> imData;
    public RollPageViewAdapter(List<ImageView> mData) {
        this.imData = mData;
    }
    @Override
    public View getView(ViewGroup container, int position) {
        return this.imData.get(position);
    }
    @Override
    public int getCount() {
        return this.imData.size();
    }
}
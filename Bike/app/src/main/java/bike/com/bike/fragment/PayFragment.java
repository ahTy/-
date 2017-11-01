package bike.com.bike.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import bike.com.bike.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment implements
        RadioGroup.OnCheckedChangeListener {
    private New_payFragment new_payFragment;
    private Used_payFragment used_payFragment;
    private Rent_payFragment rent_payFragment;
    private SlidingPaneLayout slidingPaneLayout;
    private ImageView Prompt;

    private RadioGroup payContainer;

    public PayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pay, container, false);
        payContainer=(RadioGroup)view.findViewById(R.id.payContainer);
        slidingPaneLayout=(SlidingPaneLayout)view.findViewById(R.id.SliP);
        Prompt=(ImageView)view.findViewById(R.id.Prompt);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Prompt.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {
                Prompt.setVisibility(View.VISIBLE);
            }
        });

        initFragments();
        payContainer.setOnCheckedChangeListener(this);//给tabsContainer添加监听
        payContainer.check(R.id.New);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup payContainer, int checkedId) {
        switch (checkedId){
            case R.id.New:
                getChildFragmentManager().beginTransaction()
                        .show(new_payFragment)
                        .hide(used_payFragment)
                        .hide(rent_payFragment)
                        .commit();
                break;
            case R.id.used:
                getChildFragmentManager().beginTransaction()
                        .hide(new_payFragment)
                        .show(used_payFragment)
                        .hide(rent_payFragment)
                        .commit();
                break;
            case R.id.rent:
                getChildFragmentManager().beginTransaction()
                        .hide(new_payFragment)
                        .hide(used_payFragment)
                        .show(rent_payFragment)
                        .commit();
                break;
        }
    }

    private void initFragments()
    {
        new_payFragment=new New_payFragment();
        used_payFragment=new Used_payFragment();
        rent_payFragment= new Rent_payFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.right,new_payFragment)
                .add(R.id.right,used_payFragment)
                .add(R.id.right,rent_payFragment)
                .commit();
    }
}

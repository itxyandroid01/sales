package cn.it.sales.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.it.sales.R;

/**
 * 即时通讯功能
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment6 extends Fragment {


    public SalesFragment6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.sales_fragment6, container, false);
        return rootView;
    }

}

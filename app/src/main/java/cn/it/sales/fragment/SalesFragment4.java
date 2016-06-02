package cn.it.sales.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.it.sales.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment4 extends Fragment {


    public SalesFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.sales_fragment4, container, false);
        return rootView;
    }

}

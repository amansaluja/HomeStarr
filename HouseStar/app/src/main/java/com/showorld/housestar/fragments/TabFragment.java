package com.showorld.housestar.fragments;

/**
 * Created by Aman on 04-07-2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.showorld.housestar.R;

public class TabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View android = inflater.inflate(R.layout.fragment_home, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Feed");
        return android;
    }

}

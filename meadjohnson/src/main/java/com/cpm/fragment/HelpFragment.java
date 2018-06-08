package com.cpm.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cpm.capitalfoods.R;
import com.cpm.gskgtsupervisor.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {

    Button btncall;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_help, container, false);

        btncall = (Button) view.findViewById(R.id.btn_call);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01149894989"));
                startActivity(callIntent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainMenuActivity) getActivity()).getSupportActionBar().setTitle("Help");
    }

}

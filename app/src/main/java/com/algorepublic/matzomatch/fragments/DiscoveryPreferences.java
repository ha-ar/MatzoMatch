package com.algorepublic.matzomatch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.algorepublic.matzomatch.R;
import com.androidquery.AQuery;
import com.appyvet.rangebar.RangeBar;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/1/15.
 */
public class DiscoveryPreferences extends Fragment {

    private SeekBar seekBar, seekBar2;
    private TextView textView, textView2,distance1,distance2;
    private Spinner spinner;
    ArrayList<String> list;
    ArrayAdapter<String> dataAdapter;
    RangeBar rangeBar;
    View view;
    AQuery aq;

    public static DiscoveryPreferences newInstance(){
        DiscoveryPreferences discoveryPreferences = new DiscoveryPreferences();
        return discoveryPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings, container, false);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        textView = (TextView) view.findViewById(R.id.textView1);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        rangeBar = (RangeBar) view.findViewById(R.id.rangebar);
        distance1 = (TextView) view.findViewById(R.id.distance1);
        distance2 = (TextView) view.findViewById(R.id.distance2);
        textView2 = (TextView) view.findViewById(R.id.textView1);

        textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getActivity(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getActivity(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        textView2.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                distance1.setText("" + leftPinValue);
                distance2.setText("" + rightPinValue);
            }
        });


        list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        return view;
    }


    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }
}

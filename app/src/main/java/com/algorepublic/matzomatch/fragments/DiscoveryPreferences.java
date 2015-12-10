package com.algorepublic.matzomatch.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.algorepublic.matzomatch.R;
import com.algorepublic.matzomatch.Services.CallBack;
import com.algorepublic.matzomatch.Services.ProfileServices;
import com.algorepublic.matzomatch.Utils.Constants;
import com.algorepublic.matzomatch.Utils.TinyDB;
import com.algorepublic.matzomatch.model.ModelPreferences;
import com.androidquery.AQuery;
import com.appyvet.rangebar.RangeBar;

import java.util.ArrayList;

/**
 * Created by ahmad on 12/1/15.
 */
public class DiscoveryPreferences extends Fragment {

    private RangeBar seekBar;
    private TextView textView, textView2,distance1,distance2;
    private Spinner spinner;
    ArrayList<String> list;
    ArrayAdapter<String> dataAdapter;
    RangeBar rangeBar;
    View view;
    AQuery aq;
    public TinyDB tinyDB;
    public Switch man,woman;
    ProfileServices profileServices;
    ProgressDialog pd;

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
        tinyDB = new TinyDB(getActivity());
        seekBar = (RangeBar) view.findViewById(R.id.seekBar1);
        textView = (TextView) view.findViewById(R.id.textView1);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        rangeBar = (RangeBar) view.findViewById(R.id.rangebar);
        distance1 = (TextView) view.findViewById(R.id.distance1);
        distance2 = (TextView) view.findViewById(R.id.distance2);
        textView2 = (TextView) view.findViewById(R.id.textView1);
        man = (Switch) view.findViewById(R.id.man);
        woman = (Switch) view.findViewById(R.id.woman);
        aq = new AQuery(view);
        profileServices = new ProfileServices(getActivity(),view);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Saving");

        if (!tinyDB.getString(Constants.PREF_GENDER).equals("")){
            if (tinyDB.getString(Constants.PREF_GENDER).equals("1")) {
                man.setChecked(true);
                woman.setChecked(false);
            } else {
                man.setChecked(false);
                woman.setChecked(true);
            }
        }
        if (!tinyDB.getString(Constants.DISTANCE).equals("")){
            int k = Integer.valueOf(tinyDB.getString(Constants.DISTANCE));
            textView.setText(tinyDB.getString(Constants.DISTANCE));
            seekBar.setSeekPinByValue(k);
        }
        if (!tinyDB.getString(Constants.UPPER_AGE).equals("") && !tinyDB.getString(Constants.LOWER_AGE).equals("")){
            int loweAge = Integer.valueOf(tinyDB.getString(Constants.LOWER_AGE));
            int upperAge = Integer.valueOf(tinyDB.getString(Constants.UPPER_AGE));
            distance1.setText(tinyDB.getString(Constants.LOWER_AGE));
            distance2.setText(tinyDB.getString(Constants.UPPER_AGE));
            rangeBar.setRangePinsByValue(loweAge,upperAge);
        }

        seekBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                textView.setText(rightPinValue);
                tinyDB.putString(Constants.DISTANCE, rightPinValue);
            }
        });

        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                distance1.setText(leftPinValue);
                distance2.setText(rightPinValue);
                tinyDB.putString(Constants.UPPER_AGE, rightPinValue);
                tinyDB.putString(Constants.LOWER_AGE,leftPinValue);
            }
        });

        man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    man.setChecked(true);
                    tinyDB.putString(Constants.USER_GENDER,"1");
                    woman.setChecked(false);
                } else {
                    man.setChecked(false);
                }
            }
        });
        woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    woman.setChecked(true);
                    tinyDB.putString(Constants.USER_GENDER,"2");
                    man.setChecked(false);
                }else {
                    woman.setChecked(false);
                }
            }
        });

        list = new ArrayList<String>();
        list.add("Man");
        list.add("Woman");
        list.add("Man & Woman");
        dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        aq.id(R.id.save).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                Log.e("data",    tinyDB.getString(Constants.USER_GENDER)+ tinyDB.getString(Constants.LOWER_AGE)
                        + tinyDB.getString(Constants.PREF_GENDER)+ tinyDB.getString(Constants.UPPER_AGE)+ tinyDB.getString(Constants.DISTANCE));
                profileServices.updatePreferences("8E4A3EA7-80C4-4961-90CB-DB96B9FB38D3",
                        "38453441334541372D383043342D343936312D393043422D44kmY4YZwj7fhcouS61swo4239364239464233384433kmY4YZwj7fhcouS61swo",
                         tinyDB.getString(Constants.USER_GENDER), tinyDB.getString(Constants.LOWER_AGE)
                        , tinyDB.getString(Constants.PREF_GENDER), tinyDB.getString(Constants.UPPER_AGE), tinyDB.getString(Constants.DISTANCE),
                        "2", new CallBack(DiscoveryPreferences.this,"DiscoveryPreferences"));
            }
        });
        return view;
    }

    public void DiscoveryPreferences(Object caller,Object model){
        ModelPreferences.getObj().setList((ModelPreferences) model);
        if (ModelPreferences.getObj().errMsg.equals("Preferences updated successfully.")){
            pd.dismiss();
            Snackbar.make(view,"Setting Saved",Snackbar.LENGTH_SHORT).show();
        }else {
            pd.dismiss();
            Snackbar.make(view,"Something Went Wrong",Snackbar.LENGTH_SHORT).show();
        }
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

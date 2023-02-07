package net.kcci.HomeIot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;


public class Fragment2 extends Fragment {
    MainActivity mainActivity;
    Button buttonCondition;
    ImageView imageViewLed;
    ImageView imageViewAir;
    ImageView imageViewBlind;
    Switch switchLed;
    Switch switchBlind;
    Switch switchAir;
    TextView textViewSun;
    TextView textViewTemp;
    TextView textViewHumi;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        mainActivity = (MainActivity)getActivity();
        buttonCondition = view.findViewById(R.id.buttonCondition);
        imageViewLed = view.findViewById(R.id.imageViewLed);
        imageViewAir = view.findViewById(R.id.imageViewAir);
        imageViewBlind = view.findViewById(R.id.imageViewBlind);
        switchLed = view.findViewById(R.id.switchLed);
        switchBlind = view.findViewById(R.id.switchBlind);
        switchAir = view.findViewById(R.id.switchAir);
        textViewSun = view.findViewById(R.id.textViewSun);
        textViewTemp = view.findViewById(R.id.textViewTemp);
        textViewHumi = view.findViewById(R.id.textViewHumi);

        buttonCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null)
                mainActivity.clientThread.sendData("[LHG_ARD]GETSENSOR\n");
            }
        });


        switchLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null) {
                    if(switchLed.isChecked()){
                    mainActivity.clientThread.sendData("[LHG_ARD]LEDON\n");
                    switchLed.setChecked(false);
                }else{
                    mainActivity.clientThread.sendData("[LHG_ARD]LEDOFF\n");
                    switchLed.setChecked(true);
                }
            }
            }
        });
        switchBlind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null) {
                    mainActivity.clientThread.sendData("[LHG_ARD]BLINDON\n");
                    switchBlind.setChecked(false);
                }else{
                    mainActivity.clientThread.sendData("[LHG_ARD]BLINDOFF\n");
                    switchBlind.setChecked(true);
                }
            }
        });
        switchAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null) {
                    mainActivity.clientThread.sendData("[LHG_ARD]AIRON\n");
                    switchAir.setChecked(false);
                }else{
                    mainActivity.clientThread.sendData("[LHG_ARD]AIROFF\n");
                    switchAir.setChecked(true);
                }
            }
        });



        return view;
    }
    void recvDataProcess(String strRecvData)
    {
        String[] splitLists = strRecvData.toString().split("\\[|]|@|\\r");
        for(int i=0; i< splitLists.length; i++)
            Log.d("recvDataProcess"," i: " + i + ", value: " +splitLists[i]);
        if(splitLists[2].equals("LEDON")) {
            imageViewLed.setImageResource(R.drawable.led_on);
            switchLed.setChecked(true);
        } else if(splitLists[2].equals("LEDOFF")) {
            imageViewLed.setImageResource(R.drawable.led_off);
            switchLed.setChecked(false);
        } else if(splitLists[2].equals("BLINDON")) {
            imageViewBlind.setImageResource(R.drawable.blinds_on);
            switchBlind.setChecked(true);
        } else if(splitLists[2].equals("BLINDOFF")) {
            imageViewBlind.setImageResource(R.drawable.blinds_off);
            switchBlind.setChecked(false);
        }
        else if(splitLists[2].equals("AIRON")) {
            imageViewAir.setImageResource(R.drawable.air_on);
            switchAir.setChecked(true);
        } else if(splitLists[2].equals("AIROFF")) {
            imageViewAir.setImageResource(R.drawable.air_off);
            switchAir.setChecked(false);
        }
        else if(splitLists[2].equals("SENSOR")) {
            textViewSun.setText(splitLists[3] + "%");
            textViewTemp.setText(splitLists[4] + "C");
            textViewHumi.setText(splitLists[5] + "%");
        }

    }
}

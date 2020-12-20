package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cardsgame.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import Callbacks.Callback_List;
import Objects.DB;
import Objects.Location;
import Objects.Record;
import Utils.MySPV3;

import static Utils.MySPV3.KEYS.KEY_DATABASE;

public class Fragment_List extends Fragment {

    private TextView list_LBL_title;
    private ListView list_topTen_view;
    private Button list_BTN_changeActivity;
    private ArrayList<String> topTen;
    private String data ,jsonInString;
    private Callback_List callback_list;
    private Gson gson;
    private DB dataBase;



    public void setCallBack_list(Callback_List callback_list) {
        this.callback_list = callback_list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);

        //initiate variables
        topTen = new ArrayList<>();
        gson = new Gson();
        jsonInString=MySPV3.getInstance().getString(KEY_DATABASE,"");
        if(jsonInString!=""){
        dataBase = gson.fromJson(jsonInString, DB.class);}

        if(dataBase!=null) {
            for (int i = 0; i < dataBase.getRecords().size(); i++) {
                topTen.add(dataBase.getRecords().get(i).toString());
            }
        }


        ///Test for an item
       // Location location = new Location( -33.852, 151.211 );
       // Record record = new Record( "name", 0 , location);
      //  topTen.add("record1");
      //  topTen.add(record.toString());
        /////

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, topTen);
        findViews(view);
        list_topTen_view.setAdapter(adapter);


        list_topTen_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // data = parent.getAdapter().getItem();
               // data = ((TextView) view).getText().toString();



                //get the location
                String val = adapter.getItem(position);

                //callback_list.addMarkerToMap();
                //jsontosobj
                //data.Location


                if (callback_list != null) {
                   // callback_list.addMarkerToMap( data.Location.lon,data.Location.lon);
                  //  callback_list.addMarkerToMap(32.07158054366349, 34.81063892756903);
                    callback_list.addMarkerToMap(dataBase.getRecords().get(position).getLocation().getLat(),dataBase.getRecords().get(position).getLocation().getLon());
                }

                //data = getLocat

                //Log.d("pttt", "onCreateView - data from list: "+ data);
                Log.d("pttt", "onCreateView - val from list: "+ val);
                Log.d("pttt", "position: "+ position);


            }
        });


        return view;


    }




    private void findViews(View view) {
        list_LBL_title = view.findViewById(R.id.list_LBL_title);
        list_topTen_view= view.findViewById(R.id.list_topTen_view);
        list_BTN_changeActivity = view.findViewById(R.id.list_BTN_changeActivity);
    }

    public void addToList(String record) {
        ///method to add all the elements into the list

    }

}

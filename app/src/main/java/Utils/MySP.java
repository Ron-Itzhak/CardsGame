package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MySP {
    public static String MY_PREFS_NAME2 = "High Score Table";
    private SharedPreferences prefs;


    public MySP(Context context){
        prefs = context.getSharedPreferences(MY_PREFS_NAME2, Context.MODE_PRIVATE);

    }

    /////retrieve data
   // String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
   // int idName = prefs.getInt("idName", -1); //0 is the default value.

        //Log.d("pttt","name"+name);
        ///Log.d("pttt","idName"+idName);




    /////////////
    //SharedPreferences.Editor editor = preferences.edit();
    //editor.remove("productId");
    //editor.remove("purchaseToken");
    //editor.remove("orderId");
    // editor.commit();



    public void putString(String key,String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,value);
        editor.apply();

    }


    public String getString(String key,String value){
        return prefs.getString(key,value);
    }

    public void removeKey(String key){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();

    }




}

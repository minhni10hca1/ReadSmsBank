package bank.ngocdung.com.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import bank.ngocdung.com.readsmsbank.R;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "TMVNGOCDUNG";
    public static final String KEY_LIST_SMS = "LIST_SMS";
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {

        sharedPreferences = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void CreateSession(String key, List<Sms> lstSms){
        // if status login -> 0 is logout and 1 is login
        Gson gson = new Gson();
        String json = gson.toJson(lstSms);
        editor.putString(key, json);
        editor.commit();
    }
    public String getSesstion(String key){
        return sharedPreferences.getString(key, "");
    }
    public void DeleteSesstion(){
        editor.clear();
        editor.commit();
    }
}

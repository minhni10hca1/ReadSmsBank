package bank.ngocdung.com.readsmsbank;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.ngocdung.com.session.SessionManager;
import bank.ngocdung.com.session.Sms;

public class MainActivity extends AppCompatActivity {

    public Handler handler;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sessionManager = new SessionManager(this);
//        if(ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.READ_SMS")== PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                processSms();
//                Gson gson = new Gson();
//                String response = sessionManager.getSesstion(SessionManager.KEY_LIST_SMS);
//                List<Sms> lst = gson.fromJson(response,
//                        new TypeToken<List<Sms>>(){}.getType());
//                if(lst != null) {
//                    Log.e("size lst", String.valueOf(lst.size()));
//                    for (int i = 0; i < lst.size(); i++) {
//                        Log.e("sms-------", lst.get(i).getMsg());
//                        Log.e("time-------", lst.get(i).getTime());
//                        Log.e("address-------", lst.get(i).getAddress());
//                        Log.e("status_db-------", lst.get(i).getStatusInsertDb());
//
//                    }
//                }
//            }
//        }else
//        {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, 123);
//        }
        CreatFirstStart();

    }

    private void CreatFirstStart(){
        Toast.makeText(this, "Service is running", Toast.LENGTH_SHORT).show();
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                // process read sms
                if(ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.READ_SMS")== PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Log.d("vao lan",String.valueOf(number));
                        number +=1 ;
                        processSms();
//                        Gson gson = new Gson();
//                        String response = sessionManager.getSesstion(SessionManager.KEY_LIST_SMS);
//                        List<Sms> lst = gson.fromJson(response,
//                                new TypeToken<List<Sms>>(){}.getType());
//                        Log.e("begin onstart with size", String.valueOf(lst.size()));
//                        for (int i = 0; i < lst.size(); i++) {
//                            Log.e("sms-------", lst.get(i).getMsg());
//                            Log.e("time-------", lst.get(i).getTime());
//                            Log.e("address-------", lst.get(i).getAddress());
//                            Log.e("status_db-------", lst.get(i).getStatusInsertDb());
//
//                        }
                    }
                }else
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, 123);
                }
            }

        };

        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
                    try {
                       Thread.sleep(180000); // 3p
                       // Thread.sleep(60000); // 1 p
//                        Thread.sleep(90000); //1.5 phut
                        //Thread.sleep(120000); //1.5 phut
                        handler.sendEmptyMessage(0);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int number = 1;

    @Override
    protected void onStart() {
        super.onStart();
        // begin


//        Toast.makeText(this, "Service running", Toast.LENGTH_SHORT).show();
//        handler = new Handler(){
//
//            @Override
//            public void handleMessage(Message msg) {
//                // TODO Auto-generated method stub
//                super.handleMessage(msg);
//                // process read sms
//                if(ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.READ_SMS")== PackageManager.PERMISSION_GRANTED) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        Log.d("vao lan",String.valueOf(number));
//                        number +=1 ;
//                        processSms();
////                        Gson gson = new Gson();
////                        String response = sessionManager.getSesstion(SessionManager.KEY_LIST_SMS);
////                        List<Sms> lst = gson.fromJson(response,
////                                new TypeToken<List<Sms>>(){}.getType());
////                        Log.e("begin onstart with size", String.valueOf(lst.size()));
////                        for (int i = 0; i < lst.size(); i++) {
////                            Log.e("sms-------", lst.get(i).getMsg());
////                            Log.e("time-------", lst.get(i).getTime());
////                            Log.e("address-------", lst.get(i).getAddress());
////                            Log.e("status_db-------", lst.get(i).getStatusInsertDb());
////
////                        }
//                    }
//                }else
//                {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, 123);
//                }
//            }
//
//        };
//
//        new Thread(new Runnable(){
//            public void run() {
//                // TODO Auto-generated method stub
//                while(true)
//                {
//                    try {
//                        Thread.sleep(180000); // 3p
//                        //Thread.sleep(60000); // 1 p
//                        //Thread.sleep(90000); //1.5 phut
//                        handler.sendEmptyMessage(0);
//
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }).start();



    }

    public  Sms getSmsExit(List<Sms> listSms, Sms smsCheck ){
        Sms sms = new Sms();
        if(listSms == null)
            return sms;
        for (Sms smsIn: listSms){
            if (smsCheck.getTime().equals(smsIn.getTime()))
                return  smsIn;
        }
        return  sms;
    }

    public  boolean checkCondition(Sms sms){
        boolean result = false;
        if(sms.getMsg().contains("0071001050872") || sms.getMsg().contains("101006940404") || sms.getMsg().contains("1903206262570") || sms.getMsg().contains("60110000880877") || sms.getMsg().contains("060090217278") || sms.getMsg().contains("19028412233010") || sms.getMsg().contains("102000452120 ") || sms.getMsg().contains("210915151054699") || sms.getMsg().contains("210915151054327") ){
            if (sms.getMsg().contains("KB$TMV"))
                result = false;
            else
                result = true;
        }
        return result;
    }

    public List<Sms> processSms() {
        List<Sms> lstSms = new ArrayList<Sms>();
        Sms objSms = new Sms();
        Uri message = Uri.parse("content://sms/inbox");
        ContentResolver cr = this.getContentResolver();

        Cursor c = cr.query(message, null, null, null, null);
        this.startManagingCursor(c);
        int totalSMS = c.getCount();
//        Log.d("totalSMS", String.valueOf(totalSMS));
        // get list sms from sesstion
        List<Sms> lstSmsFromSession = new ArrayList<Sms>();
        Gson gson = new Gson();
        String response = sessionManager.getSesstion(SessionManager.KEY_LIST_SMS);
        lstSmsFromSession = gson.fromJson(response,
                new TypeToken<List<Sms>>(){}.getType());
        // end
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new Sms();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c
                        .getColumnIndexOrThrow("address")));
                objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
//                objSms.setReadState(c.getString(c.getColumnIndex("read")));
                objSms.setTime(c.getString(c.getColumnIndexOrThrow("date")));
//                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
//                    objSms.setFolderName("inbox");
//                } else {
//                    objSms.setFolderName("sent");
//                }

                // check obj
                if ((checkCondition(objSms))) {
                    Sms smsGetExits = getSmsExit(lstSmsFromSession, objSms);
                    String newStatusInsertDb = "0";
                    // call api
                    if (smsGetExits.getTime() == null) {
                        // new msg --> call api -> check status insert db de update lai
                        // insert objSms (new)
                        // xong set lai status cho objSms
                        newStatusInsertDb = CallApiNew(objSms);
                    } else {
                        // co roi
                        if (smsGetExits.getStatusInsertDb().equals("0")) {
                            // ma truoc insert that bai => insert lai objsms
                            // call api insert lai => check status de update lai
                            newStatusInsertDb = CallApiNew(objSms);
                            Log.d("statusCode2", newStatusInsertDb);
                        } else {
                            // sms nay da insert thanh cong truoc do roi -> chi update lai status
                            newStatusInsertDb = "1";
                        }
                    }
                    // end
                    //set lai status insert db
                    objSms.setStatusInsertDb(newStatusInsertDb);
                    lstSms.add(objSms);
                }
                c.moveToNext();
            }
            // update lai list
            Log.d("totalSMSSave", String.valueOf(lstSms.size()));
            sessionManager.DeleteSesstion();
            sessionManager.CreateSession(SessionManager.KEY_LIST_SMS,lstSms);
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        //c.close();

        return lstSms;
    }
    String status_call_api = "0";
    // call api
    private String CallApi(Sms sms) {
       // String status_call_api = "0";
        status_call_api = "0";
        try {
            String URL = "http://ngocdunggroup.com:8018/api/BankMessage/Save";
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("bankname",sms.getAddress());
            jsonBody.put("content", sms.getMsg());
            JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        //Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                        String statusCode = response.getString("statusCode");
                        if(statusCode.equals("200"))
                            status_call_api = "1";
                        else
                            status_call_api = "0";

                        Log.e("Response call api",response.toString());
                    }catch (Exception ex){
                        Log.e("Response call api",ex.getMessage());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    status_call_api = "0";
                    Log.e("error123",error.getMessage());
                }
            }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> headers = new HashMap<>();
//                    headers.put("API_KEY", "059659c3b9fcb36c3c4fc20ced40befa");//put your token here
//                    headers.put("Content-Type", "application/json");
//                    return headers;
//                }
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        HashMap headers = new HashMap();
                    headers.put("API_KEY", "059659c3b9fcb36c3c4fc20ced40befa");//put your token here
                    headers.put("Content-Type", "application/json");
                    return headers;
                    }
            };
//            VolleyApplication.getInstance().addToRequestQueue(jsonOblect);

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(jsonOblect);

        } catch (JSONException e) {
            status_call_api = "0";
            Log.e("error123",e.getMessage());
            //e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
        return   status_call_api;

}


    private  void setStatusInsertDBAgain(Sms sms, String status_call_api){
        Gson gson = new Gson();
        String response = sessionManager.getSesstion(SessionManager.KEY_LIST_SMS);
        List<Sms> lst = gson.fromJson(response,
                new TypeToken<List<Sms>>(){}.getType());
        for(Sms smsUpdate : lst){
            if (smsUpdate.getTime().equals(sms.getTime())) {
                smsUpdate.setStatusInsertDb(status_call_api);
                break;
            }
        }
        sessionManager.DeleteSesstion();
        sessionManager.CreateSession(SessionManager.KEY_LIST_SMS,lst);

    }
    private String CallApiNew(final Sms sms) {
        // String status_call_api = "0";
        status_call_api = "0";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("bankname",sms.getAddress());
            jsonBody.put("content", sms.getMsg());

            //String URL = "http://ngocdunggroup.com:8018/api/BankMessage/Save";
            String URL = "http://45.119.81.160:8008/api/BankMessage/Save";
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    URL, jsonBody,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.d("response22", response.toString());
//                            pDialog.hide();
                            try {
                                String statusCode = response.getString("statusCode");
                                Log.d("statusCode1", statusCode);
//                                Log.d("response", response.toString());
                                if (statusCode.equals("200") || statusCode.equals("6003"))
                                    status_call_api = "1";
                                else
                                    status_call_api = "0";
                                // set lai trang thai cho sms nay
                                setStatusInsertDBAgain(sms,status_call_api);
                            }catch (Exception ex){

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                     //       Log.d("response11", error.getMessage());
                            status_call_api = "0";
                            setStatusInsertDBAgain(sms,status_call_api);
                        }
            }) {

//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("bankname",sms.getAddress());
//                    params.put("content", sms.getMsg());
//                    Log.d("response33", "333");
//                    return params;
//                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("API_KEY", "059659c3b9fcb36c3c4fc20ced40befa");//put your token here
                   // Log.d("response44", "44");
                    return headers;
                }
            };

           // this.getIns

//            VolleyApplication.getInstance().addToRequestQueue(jsonOblect);
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(jsonObjReq);


        } catch (Exception e) {
            status_call_api = "0";
            Log.e("error123",e.getMessage());
            //e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
        return   status_call_api;

    }

}

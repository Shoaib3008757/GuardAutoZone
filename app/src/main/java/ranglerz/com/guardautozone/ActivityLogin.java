package ranglerz.com.guardautozone;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.otto.Subscribe;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityLogin extends BaseActvitvityForDrawer{
    Context mContext;
    private String TAG = ActivityLogin.class.getSimpleName();
    LinearLayout linearLayoutOfList;
    ListView listView;
    Button login, fbLogin;
    EditText mobileNumber, loginPin;
    EditText pin;
    TextView tv_null_result_from_url;
    Button verifyPin;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "loginPref" ;
    String URLRESULT;
    String myJSON;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;
    private String serverUrl;
    private static final String TAG_RESULTS = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NUMBER = "mobile";
    private static final String TAG_FNAME = "first_name";
    private static final String TAG_LNAME = "last_name";
    private static final String TAG_CITY = "city";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_DATE = "date";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_activity_login);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_activity_login, null, false);
        mDrawerLayout.addView(contentView, 0);

        initialization();
        loginClickHandling();



    }//end of onViewCreated

    public void initialization(){
        login = (Button)findViewById(R.id.b_login);
        listView = (ListView)findViewById(R.id.listView_Results);
        mobileNumber = (EditText)findViewById(R.id.login_name);
        tv_null_result_from_url = (TextView)findViewById(R.id.tv_null_result);
        personList = new ArrayList<HashMap<String, String>>();


    }//end of initialization

    public void loginClickHandling() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobileNumber.getText().length() == 0) {
                    mobileNumber.setError("");
                } else {
                    String mNumber = mobileNumber.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    // send data to server from here
                    String mMobileNumber = mobileNumber.getText().toString();
                    // String mLoginPin = loginPin.getText().toString();

                    Toast.makeText(getApplicationContext(), "Mobile Number: " + mMobileNumber, Toast.LENGTH_SHORT).show();


                    String mobileNo = mobileNumber.getText().toString().trim();
                    serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+mobileNo+"&type=json";
                    new GetContacts().execute();



/*
                    AsyncDataClass asyncRequestObject = new AsyncDataClass();
                        asyncRequestObject.execute(serverUrl, mMobileNumber);
*/



                   /* final Dialog dialog = new Dialog(ActivityLogin.this);
                    dialog.setContentView(R.layout.custome_dialog_layout_for_pin);
                    dialog.setTitle("Please Enter PIN");
                    dialog.setCancelable(false);
                    pin = (EditText) dialog.findViewById(R.id.ed_verify_pin);

                    //check from server if the number is registered

                    verifyPin = (Button) dialog.findViewById(R.id.bt_verify);
                    verifyPin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (pin.getText().length() == 0) {
                                Toast.makeText(getApplicationContext(), "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                            } else if (pin.getText().length() == 6) {
                                //Handling data here

                                String pinNumber = pin.getText().toString().trim();
                                int pinNumberInteger = Integer.parseInt(pinNumber);
                                Toast.makeText(getApplicationContext(), "Successfully login with " + pinNumber + " Pin Number", Toast.LENGTH_SHORT).show();

                                sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putInt("LOGIN", pinNumberInteger);
                                editor.commit();
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Inserted to SharePreferences", Toast.LENGTH_SHORT).show();

                                //temprory starting registered activity
                                Intent registerdActivity = new Intent(ActivityLogin.this, ActivityRegister.class);
                                startActivity(registerdActivity);

                                //do nevegation on login successs

                            } else {
                                Toast.makeText(getApplicationContext(), "Pin Not Valid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    dialog.show();*/
                }
            }
        });

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ActivityLogin.this, "Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(serverUrl);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String first_name = c.getString("first_name");
                        String last_name = c.getString("last_name");
                        String address = c.getString("address");
                        String mobile = c.getString("mobile");
                        String city = c.getString("city");
                        String date = c.getString("date");

                        Log.e("TAG", "VVVVV " + id);
                        Log.e("TAG", "VVVVV " + first_name);
                        Log.e("TAG", "VVVVV " + last_name);
                        Log.e("TAG", "VVVVV " + address);
                        Log.e("TAG", "VVVVV " + mobile);
                        Log.e("TAG", "VVVVV " + city);
                        Log.e("TAG", "VVVVV " + date);



                        // Phone node is JSON Object


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("first_name", first_name);
                        contact.put("last_name", last_name);
                        contact.put("address", address);
                        contact.put("mobile", mobile);
                        contact.put("city", city);
                        contact.put("date", date);




                        // adding contact to contact list
                        personList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            tv_null_result_from_url.setText("Nothing found");
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            BaseAdapter adapter = new SimpleAdapter(ActivityLogin.this, personList,
                    R.layout.user_result_from_server_layout, new String[]{"id", "first_name","last_name","address", "mobile", "city", "date"},
                    new int[]{R.id.id, R.id.firs_name, R.id.last_name, R.id.address, R.id.mobile, R.id.city, R.id.date});

            adapter.notifyDataSetChanged();
           listView.setAdapter(adapter);



        }
    }



}


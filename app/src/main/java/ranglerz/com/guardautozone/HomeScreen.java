package ranglerz.com.guardautozone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeScreen extends AppCompatActivity {

    ImageView bt_history;
    ImageView arrowImageHome, arrowImageMaitenace, arrowImageNearestGuardAuto, arrowImageAboutCompany, arrowImageGuideBook, arrowImageNewsEvents, arrowImageFeedBack, arrowImageReferFreind;
    EditText pin;
    Button verifyPin;
    String TAG = "HomeScreen Actvity: ";
    ListView listView;

    ArrayList<HashMap<String, String>> personList;
    private String serverUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        init();
        viewUserHistory();
        arrowImageClicHome();
        arrowImageClicMaintainace();
        arrowImageClicNearestGuardAuto();
        arrowImageClicAboutCompeny();
        arrowImageClicGuideBook();
        arrowImageClicNewsEvents();
        arrowImageClicFeedback();
        arrowImageClicReferFreind();
    }


    public void init(){
        bt_history = (ImageView) findViewById(R.id.bt_history);
        listView = (ListView)findViewById(R.id.listView_Results);
        personList = new ArrayList<HashMap<String, String>>();
        arrowImageHome = (ImageView) findViewById(R.id.arrow_image_home);
        arrowImageMaitenace = (ImageView) findViewById(R.id.arrow_image_maintenace_screen);
        arrowImageNearestGuardAuto = (ImageView) findViewById(R.id.arrow_image_nearest_guard_auto);
        arrowImageAboutCompany = (ImageView) findViewById(R.id.arrow_image_about_company);
        arrowImageGuideBook = (ImageView) findViewById(R.id.arrow_image_guide_book);
        arrowImageNewsEvents = (ImageView) findViewById(R.id.arrow_image_news_events);
        arrowImageFeedBack = (ImageView) findViewById(R.id.arrow_image_feedback);
        arrowImageReferFreind = (ImageView) findViewById(R.id.arrow_image_refer_freind);
    }

    public void arrowImageClicHome(){
        arrowImageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testAnimationActvity = new Intent(HomeScreen.this, TestAnimation.class);
                startActivity(testAnimationActvity);
                finish();
            }
        });
    }

    public void arrowImageClicMaintainace(){
        arrowImageMaitenace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Your Are currently on Maintenance Screen", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void arrowImageClicNearestGuardAuto(){
        arrowImageNearestGuardAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapActvity = new Intent(HomeScreen.this, MapsActivity.class);
                startActivity(mapActvity);
                finish();
            }
        });

    }
    public void arrowImageClicAboutCompeny(){
        arrowImageAboutCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutComapyActvity = new Intent(HomeScreen.this, AboutActivity.class);
                startActivity(aboutComapyActvity);
                //finish();
            }
        });
    }
    public void arrowImageClicGuideBook(){

        arrowImageGuideBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Soon Available", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void arrowImageClicNewsEvents(){

        arrowImageNewsEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Soon Available", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void arrowImageClicFeedback(){

        arrowImageFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Soon Available", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void arrowImageClicReferFreind(){

        arrowImageReferFreind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Soon Available", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void viewUserHistory(){
        bt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent checkHistoryActvity = new Intent(HomeScreen.this, CheckHistory.class);
                startActivity(checkHistoryActvity);


                //////////////////
                //pub fatching data from server here
             /*   Toast.makeText(HomeScreen.this, "You Click on submit", Toast.LENGTH_SHORT).show();
                createNetErrorDialog();
*/
                ////////////////////////////////


               /* serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile=03332242702&type=json";
                new GetContacts().execute();*/


                   /* final Dialog dialog = new Dialog(HomeScreen.this);
                    dialog.setContentView(R.layout.custome_dialog_layout_for_pin);
                    dialog.setTitle("Please Enter PIN");
                    dialog.setCancelable(false);
                    pin = (EditText) dialog.findViewById(R.id.ed_verify_pin);

                    //check from server if the number is registered

                    verifyPin = (Button) dialog.findViewById(R.id.bt_verify);
                    verifyPin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (pin.getText().length() == 0 || pin.getText().length()<10) {
                                Toast.makeText(getApplicationContext(), "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                            } else  {
                                //Handling data here

                                String pinNumber = pin.getText().toString().trim();
                                int pinNumberInteger = Integer.parseInt(pinNumber);

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+pin+"&type=json";
                                new GetContacts().execute();

                                Toast.makeText(getApplicationContext(), "Successfully login with " + pinNumber + " Pin Number", Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                                //do nevegation on login successs

                            }
                        }
                    });


                    dialog.show();*/
            }
        });

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(HomeScreen.this, "Json Data is downloading",Toast.LENGTH_LONG).show();

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
                            Toast.makeText(getApplicationContext(), "Nothing Found: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            //tv_null_result_from_url.setText("Nothing found");
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

            String pFirstName = personList.get(0).get("first_name");
            String pLastName = personList.get(0).get("last_name");
            String pAddress = personList.get(0).get("address");
            String pMobile = personList.get(0).get("mobile");
            String pCity = personList.get(0).get("city");
            String pDate = personList.get(0).get("date");

          /*  Toast.makeText(HomeScreen.this, "First Name: " + pFirstName
                    + "Last Name: " + pLastName
                    + "Address: " + pAddress
                    + "Mobile: " + pMobile
                    + "City: " + pCity
                    + "Date: " + pDate, Toast.LENGTH_SHORT).show();
*/

            final AlertDialog.Builder dialogb = new AlertDialog.Builder(HomeScreen.this);
            dialogb.setTitle("User History");
            dialogb.setMessage("First Name: " + pFirstName
                    + "\n" + "Last Name: " + pLastName
                    + "\n" + "Address: " + pAddress
                    + "\n" + "Mobile: " + pMobile
                    + "\n" + "City: " + pCity
                    + "\n" + "Date: " + pDate);

            dialogb.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialogb.show();



           /* BaseAdapter adapter = new SimpleAdapter(HomeScreen.this, personList,
                    R.layout.user_result_from_server_layout, new String[]{"id", "first_name","last_name","address", "mobile", "city", "date"},
                    new int[]{R.id.id, R.id.firs_name, R.id.last_name, R.id.address, R.id.mobile, R.id.city, R.id.date});

            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
*/


        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    protected void createNetErrorDialog() {

        if (isNetworkAvailable()==false){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need a network connection to use this application. Please turn on mobile network or Wi-Fi in Settings.")
                    .setTitle("Unable to connect")
                    .setCancelable(false)
                    .setPositiveButton("Settings",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    startActivity(i);
                                }
                            }
                    )
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    HomeScreen.this.finish();
                                }
                            }
                    );
            AlertDialog alert = builder.create();
            alert.show();
        }else {


             serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile=03332242702&type=json";
            new GetContacts().execute();
        }
    }//end of createNetErrorDialog

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(HomeScreen.this, TestAnimation.class);
        startActivity(i);
        finish();
    }

}

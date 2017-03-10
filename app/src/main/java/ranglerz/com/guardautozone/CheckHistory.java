package ranglerz.com.guardautozone;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class CheckHistory extends AppCompatActivity {

    Button viewProfile, viewVehicles, viewVisits, viewVisitsWithDateRange, prartLocater, locationFinder;
    ListView resultsListView;
    private String serverUrl;

    int codes = 0;

    private DatePickerDialog datePictkerDialog;
    private SimpleDateFormat dateFormatter;

    ArrayList<HashMap<String, String>> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_history);

        init();
        buttonClickHanler();



    }

    public void init(){
        viewProfile = (Button) findViewById(R.id.bt_view_profile);
        viewVehicles = (Button) findViewById(R.id.bt_view_all_vehicles);
        viewVisits = (Button) findViewById(R.id.bt_view_all_visits);
        viewVisitsWithDateRange = (Button) findViewById(R.id.bt_view_visits_with_data_range);
        prartLocater = (Button) findViewById(R.id.bt_parts_locater);
        locationFinder = (Button) findViewById(R.id.bt_location_finder);

        resultsListView = (ListView) findViewById(R.id.result_lv);

        personList = new ArrayList<HashMap<String, String>>();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


    }

    public void buttonClickHanler(){
        viewProfileeClickHandler();
        viewVehiclesClickHandler();
        viewVisitseClickHandler();
        viewVisitsWithDateRangeClickHandler();
        prartLocatereClickHandler();
        locationFinderClickHandler();

    }

    public void viewProfileeClickHandler(){
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codes = 1;

                final Dialog viewProfileDialog = new Dialog(CheckHistory.this);
                viewProfileDialog.setContentView(R.layout.view_profile_dialog);

                final EditText viewProfileDialogNumber = (EditText) viewProfileDialog.findViewById(R.id.et_view_profile_dialog_number);
                Button viewProfileDialogButton = (Button) viewProfileDialog.findViewById(R.id.bt_view_profile_dialog);

                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = viewProfileDialogNumber.getText().length();

                        if (checkEditTextLeanght<10){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {

                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                            String edNumber = viewProfileDialogNumber.getText().toString();

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+edNumber+"&type=json";
                                new GetContacts().execute();
                                viewProfileDialog.dismiss();


                            }
                        }
                    }
                });

                viewProfileDialog.show();

            }
        });
    }

    public void viewVehiclesClickHandler(){

        viewVehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                codes = 2;

                final Dialog viewVehiclesDialog = new Dialog(CheckHistory.this);
                viewVehiclesDialog.setContentView(R.layout.view_profile_dialog);

                final EditText viewProfileDialogNumber = (EditText) viewVehiclesDialog.findViewById(R.id.et_view_profile_dialog_number);
                Button viewProfileDialogButton = (Button) viewVehiclesDialog.findViewById(R.id.bt_view_profile_dialog);

                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = viewProfileDialogNumber.getText().length();

                        if (checkEditTextLeanght<10){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {

                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                                String edNumber = viewProfileDialogNumber.getText().toString();

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+edNumber+"&vehicle=mycar&type=json";
                                new GetContacts().execute();
                                viewVehiclesDialog.dismiss();


                            }
                        }
                    }
                });

                viewVehiclesDialog.show();


            }
        });
    }

    public void viewVisitseClickHandler(){

        viewVisits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                codes = 3;

                final Dialog viewVehiclesDialog = new Dialog(CheckHistory.this);
                viewVehiclesDialog.setContentView(R.layout.view_profile_dialog);

                final EditText viewProfileDialogNumber = (EditText) viewVehiclesDialog.findViewById(R.id.et_view_profile_dialog_number);
                Button viewProfileDialogButton = (Button) viewVehiclesDialog.findViewById(R.id.bt_view_profile_dialog);

                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = viewProfileDialogNumber.getText().length();

                        if (checkEditTextLeanght<10){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {

                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                                String edNumber = viewProfileDialogNumber.getText().toString();

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+edNumber+"&visit=myvisit&type=json";
                                new GetContacts().execute();
                                viewVehiclesDialog.dismiss();


                            }
                        }
                    }
                });

                viewVehiclesDialog.show();


            }
        });
    }

    public void viewVisitsWithDateRangeClickHandler(){


        viewVisitsWithDateRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                codes = 4;
                final Dialog viewVisitsWithDateRangeDialog = new Dialog(CheckHistory.this);
                viewVisitsWithDateRangeDialog.setContentView(R.layout.view_date_range_dialog);

                final EditText viewProfileDialogNumber = (EditText) viewVisitsWithDateRangeDialog.findViewById(R.id.et_view_profile_dialog_number);
               final EditText startDate = (EditText) viewVisitsWithDateRangeDialog.findViewById(R.id.start_date);
                final EditText endDate = (EditText) viewVisitsWithDateRangeDialog.findViewById(R.id.end_date);
                Button viewProfileDialogButton = (Button) viewVisitsWithDateRangeDialog.findViewById(R.id.bt_view_profile_dialog);


                //creating dialog for startDate
                startDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar newCalendar = Calendar.getInstance();
                        datePictkerDialog = new DatePickerDialog(CheckHistory.this, new DatePickerDialog.OnDateSetListener() {


                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                startDate.setText(dateFormatter.format(newDate.getTime()));
                            }

                        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


                        datePictkerDialog.show();
                        datePictkerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    }
                });//end of start Date picker


                //endDate picker

                endDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar newCalendar = Calendar.getInstance();
                        datePictkerDialog = new DatePickerDialog(CheckHistory.this, new DatePickerDialog.OnDateSetListener() {


                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                endDate.setText(dateFormatter.format(newDate.getTime()));
                            }

                        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


                        datePictkerDialog.show();
                        datePictkerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    }
                });


                //setiing onlick button
                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = viewProfileDialogNumber.getText().length();
                        int checkStartDate = startDate.getText().length();
                        int checkEndDate = startDate.getText().length();

                        if (checkStartDate<1 || checkEndDate < 1){
                            Toast.makeText(CheckHistory.this, "Please Enter Date Range", Toast.LENGTH_SHORT).show();
                        }
                       else if (checkEditTextLeanght<10){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {

                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                                String edNumber = viewProfileDialogNumber.getText().toString();
                                String dStartDate = startDate.getText().toString();
                                String dEndDate = endDate.getText().toString();

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?mobile="+edNumber+"&visit=myvisit&dfrom="+dStartDate+"&dto="+dEndDate+"&type=json";
                                new GetContacts().execute();
                                viewVisitsWithDateRangeDialog.dismiss();


                            }
                        }
                    }
                });

                viewVisitsWithDateRangeDialog.show();

            }
        });

    }

    public void prartLocatereClickHandler(){

        prartLocater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codes = 5;


                final Dialog partLocaterDialog = new Dialog(CheckHistory.this);
                partLocaterDialog.setContentView(R.layout.make_model_dialog);

                final EditText carMake = (EditText) partLocaterDialog.findViewById(R.id.et_car_make);
                final EditText startYear = (EditText)partLocaterDialog.findViewById(R.id.start_years);
                final EditText endYear = (EditText)partLocaterDialog.findViewById(R.id.end_year);

                Button viewProfileDialogButton = (Button) partLocaterDialog.findViewById(R.id.bt_view_profile_dialog);


                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = carMake.getText().length();
                        int checkstartYearLeanght = carMake.getText().length();
                        int checkEndYearLeanght = carMake.getText().length();

                        if (checkstartYearLeanght<3 || checkEndYearLeanght<3){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Years", Toast.LENGTH_SHORT).show();
                        }

                       else if (checkEditTextLeanght<3){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {

                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                                String dCarMake = carMake.getText().toString();
                                String dstartYear = startYear.getText().toString();
                                String dEndYear = endYear.getText().toString();

                                String[] splitedString = dCarMake.split(" ");

                                if(splitedString.length!=2){
                                    serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?make="+dCarMake+"&model="+dstartYear+"-"+dEndYear+"&type=json";
                                }else {
                                    String command = splitedString[0];
                                    String person = splitedString[1];

                                    String topass = command + "%20" + person;

                                    Log.e("TAGE", "VALUE 1 " + command);
                                    Log.e("TAGE", "VALUE 2 " + person);
                                    Log.e("TAGE", "VALUE 3 " + topass);


                                    serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?make=" + topass + "&model=" + dstartYear + "-" + dEndYear + "&type=json";
                                    //serverUrl =  "http://dev.controloye.com/misc/Irum/API/service.php?make=Suzuki%20Swift&model=2011-2016&type=json";
                                    Log.e("TAGE", "VALUE 4 " + serverUrl);
                                }

                                new GetContacts().execute();
                                partLocaterDialog.dismiss();


                            }
                        }
                    }
                });

                partLocaterDialog.show();


            }
        });
    }

    public void locationFinderClickHandler(){

        locationFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                codes = 6;

                final Dialog locationFinderDialog = new Dialog(CheckHistory.this);
                locationFinderDialog.setContentView(R.layout.view_location_finder_dialog);

                final EditText d_cityName = (EditText) locationFinderDialog.findViewById(R.id.et_city_name);
                Button viewProfileDialogButton = (Button) locationFinderDialog.findViewById(R.id.bt_view_profile_dialog);

                viewProfileDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int checkEditTextLeanght = d_cityName.getText().length();

                        if (checkEditTextLeanght<2){
                            Toast.makeText(CheckHistory.this, "Please Enter Valid Number", Toast.LENGTH_SHORT).show();
                        }else {


                            if (isNetworkAvailable()==false){
                                Toast.makeText(CheckHistory.this, "Please Enable your Internet Connection", Toast.LENGTH_SHORT).show();
                            }else {

                                String city_name = d_cityName.getText().toString();

                                serverUrl = "http://dev.controloye.com/misc/Irum/API/service.php?city="+city_name+"&type=json";
                                new GetContacts().execute();
                                locationFinderDialog.dismiss();


                            }
                        }
                    }
                });

                locationFinderDialog.show();

            }
        });
    }



    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(CheckHistory.this, "Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(serverUrl);

            Log.e("TAG", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");


                    if (codes==1){

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
                        String dob = c.getString("date_of_birth");

                        Log.e("TAG", "VVVVV " + id);
                        Log.e("TAG", "VVVVV " + first_name);
                        Log.e("TAG", "VVVVV " + last_name);
                        Log.e("TAG", "VVVVV " + address);
                        Log.e("TAG", "VVVVV " + mobile);
                        Log.e("TAG", "VVVVV " + city);
                        Log.e("TAG", "VVVVV " + date);
                        Log.e("TAG", "VVVVV " + dob);

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
                        contact.put("date_of_birth", dob);

                        // adding contact to contact list
                        personList.add(contact);
                    }
                    }//end for view Profile


                    if (codes==2){

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String reg_mark = c.getString("reg_mark");
                            String make = c.getString("make");
                            String vehicle_type = c.getString("vehical_type");
                            String model = c.getString("model");


                            Log.e("TAG", "VVVVV " + reg_mark);
                            Log.e("TAG", "VVVVV " + make);
                            Log.e("TAG", "VVVVV " + vehicle_type);
                            Log.e("TAG", "VVVVV " + model);

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("reg_mark", reg_mark);
                            contact.put("make", make);
                            contact.put("vehicle_type", vehicle_type);
                            contact.put("model", model);

                            // adding contact to contact list
                            personList.add(contact);
                        }
                    }//end for view my cars


                    if (codes==3 || codes==4){

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String voucher_date = c.getString("voucher_date");
                            String d_code = c.getString("d_code");
                            String reg_mark = c.getString("reg_mark");
                            String due_milage = c.getString("due_milage");
                            String pressent_milage = c.getString("present_milage");
                            String item_name = c.getString("item_name");
                            String quantity = c.getString("quantity");
                            String discount = c.getString("discount");
                            String cost = c.getString("cost");
                            String fullname = c.getString("fullname");

                            Log.e("TAG", "VVVVV " + voucher_date);
                            Log.e("TAG", "VVVVV " + d_code);
                            Log.e("TAG", "VVVVV " + reg_mark);
                            Log.e("TAG", "VVVVV " + due_milage);
                            Log.e("TAG", "VVVVV " + pressent_milage);
                            Log.e("TAG", "VVVVV " + item_name);
                            Log.e("TAG", "VVVVV " + quantity);
                            Log.e("TAG", "VVVVV " + discount);
                            Log.e("TAG", "VVVVV " + cost);
                            Log.e("TAG", "VVVVV " + fullname);

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("voucher_date", voucher_date);
                            contact.put("d_code", d_code);
                            contact.put("reg_mark", reg_mark);
                            contact.put("due_milage", due_milage);
                            contact.put("present_milage", pressent_milage);
                            contact.put("item_name", item_name);
                            contact.put("quantity", quantity);
                            contact.put("discount", discount);
                            contact.put("cost", cost);
                            contact.put("fullname", fullname);

                            // adding contact to contact list
                            personList.add(contact);
                        }
                    }//end for view my cars



                    if (codes==5){

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String make = c.getString("make");
                            String year = c.getString("year");
                            String oil_filter = c.getString("oil_filter");
                            String air_filter = c.getString("air_filter");
                            String fuel_filter = c.getString("fuel_filter");
                            String mileage1 = c.getString("mileage1");
                            String recommended_oil1 = c.getString("recommended_oil1");
                            String mileage2 = c.getString("mileage2");
                            String recommended_oil2 = c.getString("recommended_oil2");
                            String mileage3 = c.getString("mileage3");
                            String recommended_oil3 = c.getString("recommended_oil3");

                            Log.e("TAG", "VVVVV " + make);
                            Log.e("TAG", "VVVVV " + year);
                            Log.e("TAG", "VVVVV " + oil_filter);
                            Log.e("TAG", "VVVVV " + air_filter);
                            Log.e("TAG", "VVVVV " + fuel_filter);
                            Log.e("TAG", "VVVVV " + mileage1);
                            Log.e("TAG", "VVVVV " + recommended_oil1);
                            Log.e("TAG", "VVVVV " + mileage2);
                            Log.e("TAG", "VVVVV " + recommended_oil2);
                            Log.e("TAG", "VVVVV " + mileage3);
                            Log.e("TAG", "VVVVV " + recommended_oil3);

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("make", make);
                            contact.put("year", year);
                            contact.put("oil_filter", oil_filter);
                            contact.put("air_filter", air_filter);
                            contact.put("fuel_filter", fuel_filter);
                            contact.put("mileage1", mileage1);
                            contact.put("recommended_oil1", recommended_oil1);
                            contact.put("mileage2", mileage2);
                            contact.put("recommended_oil2", recommended_oil2);
                            contact.put("mileage3", mileage3);
                            contact.put("recommended_oil3", recommended_oil3);

                            // adding contact to contact list
                            personList.add(contact);
                        }
                    }//end for make and model

                    if (codes==6){

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String address = c.getString("address");
                            String telephone = c.getString("telephone");
                            String latitude = c.getString("latitude");
                            String longitude = c.getString("longitude");

                            Log.e("TAG", "VVVVV " + address);
                            Log.e("TAG", "VVVVV " + telephone);
                            Log.e("TAG", "VVVVV " + latitude);
                            Log.e("TAG", "VVVVV " + longitude);

                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("address", address);
                            contact.put("telephone", telephone);
                            contact.put("latitude", latitude);
                            contact.put("longitude", longitude);

                            // adding contact to contact list
                            personList.add(contact);
                        }
                    }//end for location finder




                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Nothing Found: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            //tv_null_result_from_url.setText("Nothing found");
                        }
                    });

                }

            } else {
                Log.e("TAG", "Couldn't get json from server.");
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


            if (codes==1) {
                BaseAdapter adapter = new SimpleAdapter(CheckHistory.this, personList,
                        R.layout.user_result_from_server_layout, new String[]{"id", "first_name", "last_name", "address", "mobile", "city", "date", "date_of_birth"},
                        new int[]{R.id.id, R.id.firs_name, R.id.last_name, R.id.address, R.id.mobile, R.id.city, R.id.date, R.id.dob});

                adapter.notifyDataSetChanged();
                resultsListView.setAdapter(adapter);
            }

            if (codes==2){
                BaseAdapter adapter = new SimpleAdapter(CheckHistory.this, personList,
                        R.layout.result_mycars_layout, new String[]{"reg_mark", "make", "vehicle_type", "model"},
                        new int[]{R.id.reg_mark, R.id.make, R.id.vehical_type, R.id.model});

                adapter.notifyDataSetChanged();
                resultsListView.setAdapter(adapter);

            }


            if (codes==3 || codes==4){


                BaseAdapter adapter = new SimpleAdapter(CheckHistory.this, personList,
                        R.layout.result_myvisits_layout, new String[]{"voucher_date", "d_code", "reg_mark", "due_milage", "present_milage", "item_name", "quantity", "discount", "cost", "fullname"},
                        new int[]{R.id.voucher_date, R.id.d_code, R.id.reg_mark, R.id.due_milage, R.id.present_milage, R.id.item_name, R.id.quantity, R.id.discount, R.id.cost, R.id.fullname});

                adapter.notifyDataSetChanged();
                resultsListView.setAdapter(adapter);

            }

            if (codes==5){




                BaseAdapter adapter = new SimpleAdapter(CheckHistory.this, personList,
                        R.layout.result_make_model, new String[]{"make", "year", "oil_filter", "air_filter", "fuel_filter", "mileage1", "recommended_oil1", "mileage2", "recommended_oil2", "mileage3", "recommended_oil3"},
                        new int[]{R.id.make, R.id.year, R.id.oil_filter, R.id.air_filter, R.id.fuel_filter, R.id.mileage1, R.id.recommended_oil1, R.id.mileage2, R.id.recommended_oil2, R.id.mileage3, R.id.recommended_oil3});

                adapter.notifyDataSetChanged();
                resultsListView.setAdapter(adapter);

            }

            if (codes==6){




                BaseAdapter adapter = new SimpleAdapter(CheckHistory.this, personList,
                        R.layout.result_find_location, new String[]{"address", "telephone", "latitude", "longitude"},
                        new int[]{R.id.address, R.id.telephone, R.id.latitude, R.id.longitude});

                adapter.notifyDataSetChanged();
                resultsListView.setAdapter(adapter);

            }





            viewProfile.setVisibility(View.GONE);
            viewVisitsWithDateRange.setVisibility(View.GONE);
            viewVisits.setVisibility(View.GONE);
            viewVehicles.setVisibility(View.GONE);
            locationFinder.setVisibility(View.GONE);
            prartLocater.setVisibility(View.GONE);

            resultsListView.setVisibility(View.VISIBLE);




        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}

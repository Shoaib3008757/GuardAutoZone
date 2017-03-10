package ranglerz.com.guardautozone;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by User-10 on 07-Dec-16.
 */
public class JSONParser {

    public static String[] id;
    public static String[] first_name;
    public static String[] last_name;
    public static String[] address;
    public static String[] mobile;
    public static String[] city;
    public static String[] date;

    public static final String JSON_ARRAY = "data";
    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_CITY = "city";
    public static final String KEY_DATE = "date";


    private JSONArray data = null;

    private String json;

    public JSONParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            data = jsonObject.getJSONArray(JSON_ARRAY);

            id = new String[data.length()];
            first_name = new String[data.length()];
            last_name = new String[data.length()];
            mobile = new String[data.length()];
            address = new String[data.length()];
            city = new String[data.length()];
            date = new String[data.length()];

            for(int i=0;i<data.length();i++){
                JSONObject jo = data.getJSONObject(i);
                id[i] = jo.getString(KEY_ID);
                first_name[i] = jo.getString(KEY_FIRST_NAME);
                last_name[i] = jo.getString(KEY_LAST_NAME);
                address[i] = jo.getString(KEY_ADDRESS);
                mobile[i] = jo.getString(KEY_MOBILE);
                city[i] = jo.getString(KEY_CITY);
                date[i] = jo.getString(KEY_DATE);


            }

            Log.e("TAGE", "Values Of JSON: " + id );
            Log.e("TAGE", "Values Of JSON: " + first_name );
            Log.e("TAGE", "Values Of JSON: " + last_name );
            Log.e("TAGE", "Values Of JSON: " + address );
            Log.e("TAGE", "Values Of JSON: " + city );
            Log.e("TAGE", "Values Of JSON: " + date );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
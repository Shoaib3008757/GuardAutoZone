package ranglerz.com.guardautozone;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FbLoginTest extends AppCompatActivity {



    LoginButton loginButton;
    Button showFriendListButton;
    CallbackManager callbackManager;
    LoginManager loginManager;
    JSONArray jArry;
    String Name;
    String FEmail;

    String BirthDay;
    String Gender;


    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login_test);

//inisializing facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        // inisializing callback manger
        callbackManager = CallbackManager.Factory.create();
        //loginButton.setReadPermissions("user_friends");
        AppEventsLogger.activateApp(this);
       // loginButton = (LoginButton) findViewById(R.id.login_button);
        infoText = (TextView)findViewById(R.id.info);
       // showFriendListButton = (Button)findViewById(R.id.showfriendlilst);

        JSONArray rawName = new JSONArray();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile", "user_friends"));



        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                       // showFriendListButton.setVisibility(View.VISIBLE);
                        AccessToken accessToken = loginResult.getAccessToken();
                        final Profile profile = Profile.getCurrentProfile();

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        Log.v("LoginActivity Response ", response.toString());

                                        try {
                                            Name = object.getString("name");

                                            FEmail = object.getString("email");
                                            // BirthDay = object.getString("birthday");

                                            Gender = object.getString("gender");

                                            Log.v("Email = ", " " + FEmail);

                                            //infoText.setText("Name: " + Name + "\n" + "Email: " + FEmail + "\n" + "\n" + "Gender: " + Gender);
                                            //Toast.makeText(getApplicationContext(), "Name " + Name + "Email: " + FEmail, Toast.LENGTH_LONG).show();
/*
                                            Bundle objectId = new Bundle();
                                            objectId.putString("email", FEmail);
                                            objectId.putString("name", Name);
                                            FragmentLogin passId = new FragmentLogin();
                                            passId.setArguments(objectId);*/

                                            Intent intent=new Intent();
                                            intent.putExtra("name",Name);
                                            intent.putExtra("email", FEmail);
                                            setResult(1,intent);
                                            setResult(2, intent);
                                            finish();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender");
                        request.setParameters(parameters);
                        request.executeAsync();

                        new GraphRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/me/friends",
                                null,
                                HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    public void onCompleted(GraphResponse response) {
                                        //Intent intent  = new Intent(FBLogin.this, FriendList.class);
                                        try {

                                            JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                            jArry = rawName;
                                            ;
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                        ).executeAsync();


                    }

                    @Override
                    public void onCancel() {
                        // Log.d(TAG_CANCEL,"On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        // Log.d(TAG_ERROR,error.toString());
                    }
                });



    }//end of onCreate


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);


    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }




}

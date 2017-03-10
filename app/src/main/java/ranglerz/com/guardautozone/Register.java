package ranglerz.com.guardautozone;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.zip.Inflater;

/**
 * Created by User-10 on 05-Dec-16.
 */
public class Register extends Fragment {

    Button register, registerWithFb;
    EditText registerFullName, registerEmail, registerPassword, registerConfirmPassword, registerMobileNumber, registerCarRegisterationNumber;

    CallbackManager callbackManager;

    LoginButton loginButton;

    String name, email;
    JSONArray jArry;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_registeration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialization of views

        register = (Button)view.findViewById(R.id.b_register);
        registerWithFb = (Button)view.findViewById(R.id.b_register_with_fb);
        registerFullName = (EditText)view.findViewById(R.id.register_name);
        registerEmail = (EditText)view.findViewById(R.id.register_email);
        registerPassword = (EditText)view.findViewById(R.id.register_password1);
        registerConfirmPassword = (EditText)view.findViewById(R.id.register_password2);
        registerMobileNumber = (EditText)view.findViewById(R.id.mobile);
        registerCarRegisterationNumber = (EditText)view.findViewById(R.id.car_registrationNumber);

        registerPassword.setVisibility(View.GONE);
        registerConfirmPassword.setVisibility(View.GONE);


        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(getActivity().getApplicationContext());
        //loginButton = (LoginButton) view.findViewById(R.id.loginButton);

        //*********************


        registerUser();
        //registerWithFb();
       // fbLoginButton();


    }//end of onViewCreated


    public void registerWithFb(){

        registerWithFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FbLoginTest.class);
                getActivity().startActivityForResult(i, 2);
            }
        });
    }

    public void fbLoginButton(){

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookUserInfor();
            }
        });



    }


    public void facebookUserInfor(){

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        // inisializing callback manger
        callbackManager = CallbackManager.Factory.create();
        //loginButton.setReadPermissions("user_friends");
        AppEventsLogger.activateApp(getActivity().getApplicationContext());


        JSONArray rawName = new JSONArray();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile", "user_friends"));



        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

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
                                            name = object.getString("name");

                                            email = object.getString("email");

                                            Log.v("Email = ", " " + email);

                                            // infoText.setText("Name: " + Name + "\n" + "Email: " + FEmail + "\n" + "Age: " + BirthDay + "\n" + "Gender: " + Gender);
                                            registerFullName.setText("" + email);
                                            registerEmail.setText("" + email);
                                            Toast.makeText(getActivity().getApplicationContext(), "Name " + name + "Email: " + email, Toast.LENGTH_LONG).show();


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



    }




    //register button click handling
    public void registerUser() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (registerFullName.getText().length() == 0) {
                    registerFullName.setError("");

                } else if (registerEmail.getText().length() == 0) {
                    registerEmail.setError("");
                /*} else if (registerPassword.getText().length() == 0) {
                    registerPassword.setError("");
                } else if (registerConfirmPassword.getText().length() == 0) {
                    registerConfirmPassword.setError("");
*/
                } else if (registerMobileNumber.getText().length() == 0) {
                    registerMobileNumber.setError("");

                } else if (registerCarRegisterationNumber.getText().length() == 0) {
                    registerCarRegisterationNumber.setError("");

                } else {
                    String email = registerEmail.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (email.matches(emailPattern)) {
                        Toast.makeText(getActivity(), "valid email address", Toast.LENGTH_SHORT).show();
                    } else {
                        registerEmail.setError("Invalid Email");
                        Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }


                    String password = registerPassword.getText().toString();
                    String confirmPassword = registerConfirmPassword.getText().toString();
                    /*if (!password.equals(confirmPassword)) {
                        registerPassword.setError("Not Match");
                        registerConfirmPassword.setText("");
                        Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
                    } else {*/
                        // send data to server from here
                        String userEmail = registerEmail.getText().toString();
                        String fullName = registerFullName.getText().toString();
                        String password1 = registerPassword.getText().toString();
                        String passwordConfirm = registerConfirmPassword.getText().toString();
                        String mobileNumber = registerMobileNumber.getText().toString();
                        String carRegistratinNumber = registerCarRegisterationNumber.getText().toString();


                        Toast.makeText(getActivity(), "Name: " + fullName + "\n"
                                + "Email: " + userEmail + "\n"
                                + " Password1: " + password1 + "\n"
                                + " Password2: " + passwordConfirm + "\n"
                                + " Mobile Number: " + mobileNumber + "\n"
                                + " Car Registration Number: " + carRegistratinNumber, Toast.LENGTH_SHORT).show();
                    //}

                }

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(getActivity().getApplicationContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.activateApp(getActivity().getApplicationContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        ActivityResultBus.getInstance().register(mActivityResultSubscriber);
    }

    @Override
    public void onStop() {
        super.onStop();
        ActivityResultBus.getInstance().unregister(mActivityResultSubscriber);
    }

    private Object mActivityResultSubscriber = new Object() {
        @Subscribe
        public void onActivityResultReceived(ActivityResultEvent event) {
            int requestCode = event.getRequestCode();
            int resultCode = event.getResultCode();
            Intent data = event.getData();
            onActivityResult(requestCode, resultCode, data);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Don't forget to check requestCode before continuing your job
        if (requestCode == 2) {
            // Do your job

            Intent intent=getActivity().getIntent();
            if (intent!=null) {
                String name = data.getStringExtra("name");
                String email = data.getStringExtra("email");
                registerEmail.setText("" + email);
                registerFullName.setText("" + name);

                registerWithFb.setVisibility(View.GONE);
            }
            //Toast.makeText(getActivity().getApplicationContext(), "Name: "+ name+ " Email: "+ email, Toast.LENGTH_SHORT).show();
        }
    }


    // Bundle bundle = new Bundle();


}

package ranglerz.com.guardautozone;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;
import com.squareup.otto.Subscribe;

/**
 * Created by User-10 on 05-Dec-16.
 */


public class FragmentLogin extends Fragment {

    Button login, fbLogin;
    EditText mobileNumber, loginPin;
    EditText pin;
    Button verifyPin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.layout_login, container, false);

        if (getArguments()!=null) {

            String mobileNumber = getArguments().getString("name");
            String loginPin = getArguments().getString("email");

            Toast.makeText(getActivity().getApplicationContext(), "Here Are Values " + mobileNumber + "Email " + loginPin, Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialization of views
        login = (Button)view.findViewById(R.id.b_login);
        fbLogin = (Button) view.findViewById(R.id.fbLogin);
        mobileNumber = (EditText)view.findViewById(R.id.login_name);
        //loginPin = (EditText)view.findViewById(R.id.loginPassword);
        fbLogin.setVisibility(View.GONE);

        loginClickHandling();
        loginWithFacebook();

        //startingAnimationImageActvity();



    }//end of onViewCreated

    public void loginClickHandling(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobileNumber.getText().length() == 0) {
                    mobileNumber.setError("");
                }/* else if
                        (loginPin.getText().length() == 0) {
                    loginPin.setError("");

                }*/ else {
                    String mNumber = mobileNumber.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                   /* if (email.matches(emailPattern)) {
                        Toast.makeText(getActivity(), "valid email address", Toast.LENGTH_SHORT).show();
                    } else {
                        loginEmail.setError("Invalid Email");
                        Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }*/
                    // send data to server from here
                    String mMobileNumber = mobileNumber.getText().toString();
                    // String mLoginPin = loginPin.getText().toString();

                    Toast.makeText(getActivity(), "Mobile Number: " + mMobileNumber, Toast.LENGTH_SHORT).show();

                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.custome_dialog_layout_for_pin);
                    dialog.setTitle("Please Enter PIN");
                    dialog.setCancelable(false);
                    pin = (EditText) dialog.findViewById(R.id.ed_verify_pin);


                    verifyPin = (Button) dialog.findViewById(R.id.bt_verify);
                    verifyPin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (pin.getText().length() == 0) {
                                Toast.makeText(getActivity(), "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                            } else if (pin.getText().length() == 6) {
                                //Handling data here

                                String pinNumber = pin.getText().toString().trim();
                                int pinNumberInteger = Integer.parseInt(pinNumber);
                                Toast.makeText(getActivity(), "Successfully login with " + pinNumber + " Pin Number", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                                //do nevegation on login successs

                            } else {
                                Toast.makeText(getActivity(), "Pin Not Valid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    dialog.show();
                }
            }
        });

    }


    public void loginWithFacebook(){

        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FbLoginTest.class);
                getActivity().startActivityForResult(i, 1);
            }
        });
    }

    public void startingAnimationImageActvity(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ImageAnimation.class);
                startActivity(i);
            }
        });
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
        if (requestCode == 1) {
            // Do your job
//            String name = data.getStringExtra("name");

            Intent intent=getActivity().getIntent();
            if (intent!=null) {
                String email = data.getStringExtra("email");
                //loginEmail.setText("" + email);

                fbLogin.setVisibility(View.GONE);
            }

            //Toast.makeText(getActivity().getApplicationContext(), "Name: "+ name+ " Email: "+ email, Toast.LENGTH_SHORT).show();
        }
    }


        // Bundle bundle = new Bundle();




    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(getActivity().getApplicationContext());

        if (getArguments()!=null) {

            String name = getArguments().getString("name");
            String email = getArguments().getString("email");



            Toast.makeText(getActivity().getApplicationContext(), "Here Are Values " + name + "Email " + email, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.activateApp(getActivity().getApplicationContext());
    }
}



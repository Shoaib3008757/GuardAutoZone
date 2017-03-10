package ranglerz.com.guardautozone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PinVerificationActivity extends AppCompatActivity {

    EditText ed_pinVerification, ed_phoneVerification;
    ImageView sb_buttom;
    TextView clickHereToRegisterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verification);

        init();
        submitButtonclick();
        clickHereTextClick();
    }

    public void init(){
        ed_pinVerification = (EditText) findViewById(R.id.ed_pin);
        sb_buttom = (ImageView) findViewById(R.id.bt_sumbit);
        ed_phoneVerification = (EditText) findViewById(R.id.ed_phone);

        clickHereToRegisterPhone = (TextView) findViewById(R.id.tv_clickHere);
    }

    public void submitButtonclick(){



        sb_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_pinVerification.getVisibility()==View.VISIBLE){

                int pinCodeLenght = ed_pinVerification.getText().length();
                if (pinCodeLenght<5 || pinCodeLenght==0){
                    Toast.makeText(PinVerificationActivity.this, "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                }else {
                    //starting new Activity
                    //Toast.makeText(PinVerificationActivity.this, "Pin Verified Successfully", Toast.LENGTH_SHORT).show();
                    Intent userRegistrationActvity = new Intent(PinVerificationActivity.this, UserRegistrationActvity.class);
                    startActivity(userRegistrationActvity);
                    finish();
                }
                }
                if (ed_phoneVerification.getVisibility()==View.VISIBLE){
                    int phonLeangh = ed_phoneVerification.getText().length();
                    if (phonLeangh<11 || phonLeangh==0){
                        Toast.makeText(PinVerificationActivity.this, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                    }else {


                        ed_pinVerification.setVisibility(View.VISIBLE);
                        ed_phoneVerification.setVisibility(View.GONE);
                        clickHereToRegisterPhone.setVisibility(View.GONE);
                        Toast.makeText(PinVerificationActivity.this, "Please Wait! You Will Receive Pin Shortly", Toast.LENGTH_SHORT).show();
                        //clickHereToRegisterPhone.setText("Please wait For Pin Code From Server");
                    }
                }
            }
        });
    }

    public void clickHereTextClick(){

        clickHereToRegisterPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = clickHereToRegisterPhone.getText().toString();
                Log.e("TAGE", "TTTEESSSTT " + text);
                if (text.equals("Click Here To Register")){
                ed_pinVerification.setVisibility(View.GONE);
                ed_phoneVerification.setVisibility(View.VISIBLE);
                clickHereToRegisterPhone.setText("Click Here Already Registered");

            }
                if (text.equals("Click Here Already Registered")){
                    ed_pinVerification.setVisibility(View.VISIBLE);
                    ed_phoneVerification.setVisibility(View.GONE);
                    clickHereToRegisterPhone.setText("Click Here To Register");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PinVerificationActivity.this, TestAnimation.class);
        startActivity(i);
        finish();
    }
}

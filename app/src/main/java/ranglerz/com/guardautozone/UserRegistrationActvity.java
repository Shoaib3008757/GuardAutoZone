package ranglerz.com.guardautozone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserRegistrationActvity extends AppCompatActivity {

    ImageView sb_buttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration_actvity);
        init();
        sunmitButtonclick();

    }

    public void init(){
        sb_buttn = (ImageView) findViewById(R.id.user_sb_button);

    }

    public void sunmitButtonclick(){
        sb_buttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeScreenActvity = new Intent(UserRegistrationActvity.this, HomeScreen.class);
                startActivity(homeScreenActvity);
                finish();
            }
        });

    }
}


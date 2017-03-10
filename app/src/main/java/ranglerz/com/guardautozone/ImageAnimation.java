package ranglerz.com.guardautozone;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ImageAnimation extends BaseActvitvityForDrawer {
    String TAG = "ImageAnimation";
    Context context;
    ImageView imageView;
    ArrayList<ImageView> imageHolder;
    public int currentimageindex=0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;
    Button btViewHistory;

    private int[] IMAGE_IDS = {
            R.drawable.guard_ani_image1, R.drawable.guard_image_ani_2, R.drawable.guard_image_ani_3,
            R.drawable.gurad_image_ani_4
    };


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "loginPref" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_image_animation);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_image_animation, null, false);
        mDrawerLayout.addView(contentView, 0);

        ViewHistoryButton();


        final Handler mHandler = new Handler();
        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 2000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);


    }//end of onCreate

    public void onClick(View v) {

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateandSlideShow() {


        slidingimage = (ImageView)findViewById(R.id.animationImage1);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;

        final Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.slide);
        slidingimage.startAnimation(rotateimage);

        slidingimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateimage.cancel();
                slidingimage.clearAnimation();
            }
        });


    }

    public void ViewHistoryButton(){
        btViewHistory = (Button)findViewById(R.id.bt_view_history);
        //setting onclick Listener
        btViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
                int loginPin =  sharedpreferences.getInt("LOGIN", 0);



                if (loginPin>1) {
                    Log.d(TAG, "LOGIN PIN " + loginPin);

                    //have to start view history actvity here

                    //this is temporary test start we have to start history class instead of this
                    /*Intent loginIntent = new Intent(ImageAnimation.this, ActivityLogin.class);
                    startActivity(loginIntent);
*/

                    Intent loginIntent = new Intent(ImageAnimation.this, TestAnimation.class);
                    startActivity(loginIntent);


                }else {
                   /* Intent loginIntent = new Intent(ImageAnimation.this, ActivityLogin.class);
                    startActivity(loginIntent);*/

                    Intent loginIntent = new Intent(ImageAnimation.this, TestAnimation.class);
                    startActivity(loginIntent);
                }

            }
        });
    }


}

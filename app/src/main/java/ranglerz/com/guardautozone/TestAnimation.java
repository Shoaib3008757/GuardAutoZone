package ranglerz.com.guardautozone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestAnimation extends AppCompatActivity {

    private View coverView;
    private ImageView homeImageAni,homeVis;
    private ImageView aboutImageAni,aboutVis;
    private ImageView nearestImageAni,nearestVis;
    private ImageView checkHistoryImageAni, checkHistoryVis;
    private TextView home, about, nearest_guard_auto_zone, check_hostory;
    LinearLayout linearLayoutHome, linearLayoutAbout, linearLayoutNearest, linearLayoutCheckHistory;

    ImageView testIncreaes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);





        init();

        moveRightOnHomeClick();
        moveLeftOnHomeClick();

      /*  moveAboutRight();
        moiveAboutLeft();
*/

       moveAboutRight();
        moiveAboutLeft();

        moveNearestRight();
        moiveNearestLeft();

        moveRightOnCheckHistoryClick();
        moveLeftOnCheckHistoryClick();


        gotoHomeActvity();
        gotoAboutActvity();
        gotoMapActvity();
        gotoLoginActvity();

        startIncreasing();

    }//end of onCreate

    public void init(){
        homeImageAni = (ImageView) findViewById(R.id.home_ani);
        homeVis = (ImageView) findViewById(R.id.home_iamge);

        aboutImageAni = (ImageView) findViewById(R.id.about_ani);
        aboutVis = (ImageView) findViewById(R.id.about_iamge);

        nearestImageAni = (ImageView) findViewById(R.id.neartest_ani);
        nearestVis = (ImageView) findViewById(R.id.nearest_iamge);

        checkHistoryImageAni = (ImageView) findViewById(R.id.check_history_ani);
        checkHistoryVis = (ImageView) findViewById(R.id.check_history_image);

        linearLayoutHome = (LinearLayout) findViewById(R.id.home_linear_layout);
        linearLayoutHome.setVisibility(View.GONE);

        linearLayoutAbout = (LinearLayout) findViewById(R.id.about_linear_layout);
        linearLayoutAbout.setVisibility(View.GONE);

        linearLayoutNearest = (LinearLayout) findViewById(R.id.nearest_linear_layout);
        linearLayoutNearest.setVisibility(View.GONE);

        linearLayoutCheckHistory = (LinearLayout) findViewById(R.id.check_history_linear_layout);
        linearLayoutCheckHistory.setVisibility(View.GONE);


        home = (TextView) findViewById(R.id.tv_home);
        about = (TextView) findViewById(R.id.tv_about);
        nearest_guard_auto_zone = (TextView) findViewById(R.id.tv_nearest_guard_auto_zone);
        check_hostory = (TextView) findViewById(R.id.tv_chceck_history_guard_auto_zone);

        testIncreaes = (ImageView) findViewById(R.id.testAnim);
    }//end of init

    public void startIncreasing(){
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.grow_image);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                testIncreaes.setImageResource(R.drawable.welcome_tag1);
                anim.setFillAfter(true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        testIncreaes.setImageResource(R.drawable.welcome_tag1);
        testIncreaes.startAnimation(anim);
    }

    public void moveRightOnHomeClick(){

        homeImageAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeImageAni.post(new Runnable() {

                    @Override
                    public void run() {
                        moveToRightHome();
                    }
                });
            }
        });

    }//end of hoem click to right

    public void moveLeftOnHomeClick(){
        homeVis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutHome.setVisibility(View.GONE);
                homeImageAni.post(new Runnable() {
                    @Override
                    public void run() {
                        moveToLeftHome();
                    }
                });
            }
        });
    }//end of home click to left





    private void moveToRightHome() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_home_to_right);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                homeImageAni.setVisibility(View.GONE);
                linearLayoutHome.setVisibility(View.VISIBLE);
                //coverView.setVisibility(View.GONE);



            }
        });

            homeImageAni.startAnimation(animation);

    }


    private void moveToLeftHome() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_home_to_left);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                homeImageAni.setVisibility(View.VISIBLE);
                linearLayoutHome.setVisibility(View.GONE);

            }
        });

        homeImageAni.startAnimation(animation);



    }


   //*******************************


    public void moveAboutRight(){

        aboutImageAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutImageAni.post(new Runnable() {

                    @Override
                    public void run() {
                        moveToRightAbout();
                    }
                });
            }
        });


    }public void moiveAboutLeft(){
        aboutVis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutAbout.setVisibility(View.GONE);
                aboutImageAni.post(new Runnable() {
                    @Override
                    public void run() {
                        moveToLeftAbout();
                    }
                });
            }
        });
    }

    private void moveToRightAbout() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_about_to_right);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                aboutImageAni.setVisibility(View.GONE);
                linearLayoutAbout.setVisibility(View.VISIBLE);
                //coverView.setVisibility(View.GONE);
            }
        });
        aboutImageAni.startAnimation(animation);
    }


    private void moveToLeftAbout() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_about_to_left);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                aboutImageAni.setVisibility(View.VISIBLE);
                linearLayoutAbout.setVisibility(View.GONE);
                //coverView.setVisibility(View.GONE);
            }
        });
        aboutImageAni.startAnimation(animation);
    }

    //*****************************

    public void moveNearestRight(){

        nearestImageAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearestImageAni.post(new Runnable() {

                    @Override
                    public void run() {
                        moveToRightNearest();
                    }
                });
            }
        });


    }public void moiveNearestLeft(){
        nearestVis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutNearest.setVisibility(View.GONE);
                nearestImageAni.post(new Runnable() {
                    @Override
                    public void run() {
                        moveToLeftNearest();
                    }
                });
            }
        });
    }

    private void moveToRightNearest() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_nearest_to_right);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nearestImageAni.setVisibility(View.GONE);
                linearLayoutNearest.setVisibility(View.VISIBLE);
                //coverView.setVisibility(View.GONE);
            }
        });
        nearestImageAni.startAnimation(animation);
    }


    private void moveToLeftNearest() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_nearest_to_left);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nearestImageAni.setVisibility(View.VISIBLE);
                linearLayoutNearest.setVisibility(View.GONE);
                //coverView.setVisibility(View.GONE);
            }
        });
        nearestImageAni.startAnimation(animation);
    }

    public void gotoHomeActvity(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAnimation.this, "You are currently at Home", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void gotoAboutActvity(){
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutActvity = new Intent(TestAnimation.this, AboutActivity.class);
                startActivity(aboutActvity);
            }
        });
    }//end of gotoAbout Actvity


    public void gotoMapActvity(){
        nearest_guard_auto_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapActvity = new Intent(TestAnimation.this, MapsActivity.class);
                startActivity(mapActvity);
            }
        });
    }//end of gotoMapActvity


    public void gotoLoginActvity(){
        check_hostory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting loginActvity
                Intent pinVerificationActvity = new Intent(TestAnimation.this, PinVerificationActivity.class);
                startActivity(pinVerificationActvity);
                finish();
            }
        });
    }


    //*********************************************

    public void moveRightOnCheckHistoryClick(){

        checkHistoryImageAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkHistoryImageAni.post(new Runnable() {

                    @Override
                    public void run() {
                        moveToRightCheckHistory();
                    }
                });
            }
        });

    }//end of hoem click to right

    public void moveLeftOnCheckHistoryClick(){
        checkHistoryVis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutCheckHistory.setVisibility(View.GONE);
                checkHistoryImageAni.post(new Runnable() {
                    @Override
                    public void run() {
                        moveToLeftCheckHistory();
                    }
                });
            }
        });
    }//end of history click to left





    private void moveToRightCheckHistory() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_check_history_to_right);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                checkHistoryImageAni.setVisibility(View.GONE);
                linearLayoutCheckHistory.setVisibility(View.VISIBLE);
                //coverView.setVisibility(View.GONE);



            }
        });

        checkHistoryImageAni.startAnimation(animation);

    }


    private void moveToLeftCheckHistory() {

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_check_history_to_left);
        //Know when it ends to change visibility
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                checkHistoryImageAni.setVisibility(View.VISIBLE);
                linearLayoutCheckHistory.setVisibility(View.GONE);

            }
        });

        checkHistoryImageAni.startAnimation(animation);



    }

}


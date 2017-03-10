package ranglerz.com.guardautozone;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class BaseActvitvityForDrawer extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;


    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_actvitvity_for_drawer);
        view = new View(this);


        /**
         *Setup the DrawerLayout and NavigationView
         */




        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;



        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

       /* mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragments()).commit();*/
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                if (menuItem.getItemId() == R.id.nav_item_home) {
                    //home activity
                    Toast.makeText(getApplicationContext(), "Home Activity", Toast.LENGTH_SHORT).show();
                    Intent mainActvity = new Intent(BaseActvitvityForDrawer.this, MainActivity.class);
                    startActivity(mainActvity);
                    finish();
                }

                if (menuItem.getItemId() == R.id.nav_item_nearest_location) {
                    //map activity
                    Intent mapActivity = new Intent(BaseActvitvityForDrawer.this, MapsActivity.class);
                    startActivity(mapActivity);
                }

                if (menuItem.getItemId()==R.id.nav_item_about_company){
                    Intent mapActivity = new Intent(BaseActvitvityForDrawer.this, AboutActivity.class);
                    startActivity(mapActivity);
                    finish();
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



    }//end on Create

  /*  @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

}

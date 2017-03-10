package ranglerz.com.guardautozone;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by User-10 on 05-Dec-16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfButtons;

   public PagerAdapter(FragmentManager fm, int buttons){
       super(fm);
       numberOfButtons = buttons;
   }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentLogin login = new FragmentLogin();
                return login;

            case 1:
                Register register = new Register();
                return register;
         default:
             return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfButtons;
    }
}

package ranglerz.com.guardautozone;

/**
 * Created by User-10 on 08-Dec-16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConnectFragment extends Fragment {

public ConnectFragment() {
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);

        return rootView;
        }

        }
package ranglerz.com.guardautozone;

/**
 * Created by User-10 on 31-Jan-17.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Belal on 9/22/2015.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] id;
    private String[] first_name;
    private String[] last_name;
    private String[] address;
    private String[] city;
    private String[] date;
    private Activity context;

    public CustomList(Activity context, String[] id, String[] first_name, String[] last_name, String[] address, String[] city, String[] date) {
        super(context, R.layout.list_view_layout_result, id);
        this.context = context;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.date = date;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout_result, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewFirstName = (TextView) listViewItem.findViewById(R.id.textViewFirstName);
        TextView textViewLastName = (TextView) listViewItem.findViewById(R.id.textViewLastName);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewCity = (TextView) listViewItem.findViewById(R.id.textViewCity);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textVieDate);

        textViewId.setText(id[position]);
        textViewFirstName.setText(first_name[position]);
        textViewLastName.setText(last_name[position]);
        textViewAddress.setText(address[position]);
        textViewCity.setText(city[position]);
        textViewDate.setText(date[position]);

        return listViewItem;
    }
}
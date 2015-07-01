package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DatePicker extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml
        set(navMenuTitles, navMenuIcons);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showOverviewByDate(View v){
        Intent intent = new Intent(getApplicationContext(), Overview.class);

        android.widget.DatePicker datepicker = (android.widget.DatePicker) findViewById(R.id.datepicker);
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth()+1;
        int year = datepicker.getYear();
        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);

        startActivity(intent);
    }
}

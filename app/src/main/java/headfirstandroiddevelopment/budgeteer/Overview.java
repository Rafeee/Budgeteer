package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class Overview extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private int day;
    private int month;
    private int year;
    private String strMonth;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
       day = intent.getIntExtra("day", 0);
        month = intent.getIntExtra("month", 0);
        year = intent.getIntExtra("year", 0);
        category = intent.getStringExtra("category");


        switch(month){
            case 1:
                strMonth = "Januar";
                break;
            case 2:
                strMonth = "Februar";
                break;
            case 3:
                strMonth = "März";
                break;
            case 4:
                strMonth = "April";
                break;
            case 5:
                strMonth = "Mai";
                break;
            case 6:
                strMonth = "Juni";
                break;
            case 7:
                strMonth = "Juli";
                break;
            case 8:
                strMonth = "August";
                break;
            case 9:
                strMonth = "September";
                break;
            case 10:
                strMonth = "Oktober";
                break;
            case 11:
                strMonth = "November";
                break;
            case 12:
                strMonth = "Dezember";
                break;
            default:
                strMonth = "";
                break;
        }
        showOverviewByDate();
        /*if(intent.getStringExtra("lastView").equals("date")){
            showOverviewByDate();
        } else{
            showOverviewByCategory();
        }*/

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml
        set(navMenuTitles, navMenuIcons);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void showOverviewByDate(){
        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openReadable();

        List<Konto> kontoByDate = kontoDAO.getKontoByDate(month, year);
        ArrayAdapter adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);

        TextView title = (TextView) findViewById(R.id.overviewTitle);
        title.setText(strMonth+" "+year+":");

        for(Konto konto : kontoByDate){
            adapter.add(konto);
        }
        ListView overview = (ListView) findViewById(R.id.listoverview);
        overview.setAdapter(adapter);
        kontoDAO.close();
    }

    public void showOverviewByCategory(){
        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openReadable();

        List<Konto> kontoByCategory = kontoDAO.getKontoByCategory(category);
        ArrayAdapter adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);

        TextView title = (TextView) findViewById(R.id.overviewTitle);
        title.setText(category);

        ListView overview = (ListView) findViewById(R.id.listoverview);
        overview.setAdapter(adapter);
        kontoDAO.close();


    }

}

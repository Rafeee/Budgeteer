package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
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
    private Locale swiss = new Locale("de","CH");

    private int day;
    private int month;
    private int year;
    private String category;
    private String strMonth;
    private Boolean repeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();

        if (intent.hasExtra("day")){
            day = intent.getIntExtra("day", 0);
            month = intent.getIntExtra("month", 0);
            year = intent.getIntExtra("year", 0);
        }
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
        if(intent.getStringExtra("lastView").equals("date")){
            showOverviewByDate();
        } else{
            showOverviewByCategory();
        }
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
        Intent intent = getIntent();
        String spinner = intent.getStringExtra("spinner");
        List<Konto> kontoData;
        TextView title = (TextView) findViewById(R.id.overviewTitle);
        if (spinner.equals("-")) {
            title.setText(strMonth + " " + year);
            kontoData = kontoDAO.getKontoByDate(month, year);
        }else{
            title.setText(spinner+"\n"+strMonth + " " + year);
            kontoData = kontoDAO.getKontoByDateAndCategory(month, year, spinner);
        }
        generateResult(kontoData);

        kontoDAO.close();
    }

    public void showOverviewByCategory(){
        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openReadable();

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        List<Konto> kontoData = kontoDAO.getKontoByCategory(category);

        TextView title = (TextView) findViewById(R.id.overviewTitle);
        title.setText(category);

        generateResult(kontoData);

        kontoDAO.close();
    }

    public void generateResult(List<Konto> kontoData){
        Double result = 0.;
        ArrayAdapter adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String currency = preferences.getString("Currency", "0");
        NumberFormat formatter = null;


        for(Konto konto : kontoData){
            adapter.add(konto);
            if (konto.getCategory().equals("Income")){
                result += konto.getAmount();
            }else {
                result -= konto.getAmount();
            }        }


        if (currency.equals("0")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.US);
        } else if (currency.equals("1")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        } else if (currency.equals("2")) {
            formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        } else if (currency.equals("3")) {
            formatter = NumberFormat.getCurrencyInstance(swiss);
        }

        String formatResult = formatter.format(result);
        TextView tvResult = (TextView) findViewById(R.id.result);
        if (result < 0.0) {
            tvResult.setTextColor(Color.RED);
        }else{
            tvResult.setTextColor(Color.GREEN);
        }
        tvResult.setText("Total: " + formatResult);
        ListView overview = (ListView) findViewById(R.id.listoverview);
        overview.setAdapter(adapter);
    }
}

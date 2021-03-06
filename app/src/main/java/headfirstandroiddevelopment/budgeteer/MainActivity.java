package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private Locale swiss = new Locale("de","CH");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml
        set(navMenuTitles, navMenuIcons);

        /*show icons*/
        ImageButton icon1 = (ImageButton) findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.apartment);
        ImageButton icon2 = (ImageButton) findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.clothes);
        ImageButton icon3 = (ImageButton) findViewById(R.id.icon3);
        icon3.setImageResource(R.drawable.phone);
        ImageButton icon4 = (ImageButton) findViewById(R.id.icon4);
        icon4.setImageResource(R.drawable.bill);
        ImageButton icon5 = (ImageButton) findViewById(R.id.icon5);
        icon5.setImageResource(R.drawable.transport);
        ImageButton icon6 = (ImageButton) findViewById(R.id.icon6);
        icon6.setImageResource(R.drawable.food);
        ImageButton icon7 = (ImageButton) findViewById(R.id.icon7);
        icon7.setImageResource(R.drawable.sports);
        ImageButton icon8 = (ImageButton) findViewById(R.id.icon8);
        icon8.setImageResource(R.drawable.health);
        ImageButton icon9 = (ImageButton) findViewById(R.id.icon9);
        icon9.setImageResource(R.drawable.entertainment);
        ImageButton icon10 = (ImageButton) findViewById(R.id.icon10);
        icon10.setImageResource(R.drawable.pet);
        ImageButton icon11 = (ImageButton) findViewById(R.id.icon11);
        icon11.setImageResource(R.drawable.car);
        ImageButton icon12 = (ImageButton) findViewById(R.id.icon12);
        icon12.setImageResource(R.drawable.income);

             /* Prüfen ob repeatMonth aktiviert ist */
        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openReadable();

        List<Konto> konti = kontoDAO.getAll();
        /*Alle Konti durchgehen, wenn repeat auf 1 ist -> Datensatz nochmal sichern und repeat auf 0 setzen */
        for (Konto konto : konti) {
            if (konto.getRepeatMonth() == 1) {
                int day = konto.getDay();
                int month = konto.getMonth();
                int year = konto.getYear();
                int id = konto.getId();
                int currentDay = 30; //getCurrentDay();  TODO Zahl nach tests ersetzen
                int currentMonth = 7; //getCurrentMonth();
                int currentYear = 2015; //getCurrentYear();
                int compareToday;
                int compareYear = currentYear;
                /* nicht ganz richtig: bei 31.5 wiederholt es den Eintrag am 30.6 und dieser wird statt am 31.7 am 30.7. wiederholt */
                if((day == 31)&&(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)){
                    compareToday = 30;
                }else{
                    compareToday = day;
                }
                if (month == 12){
                    month = 0;
                    compareYear -= 1;
                }

                if ((compareToday == currentDay) &&((month == (currentMonth)-1))&& (year == compareYear)){
                    if((day == 31)&&(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)){
                        currentDay = 30;
                    }
                    Double amount = konto.getAmount();
                    String category = konto.getCategory();
                    String description = konto.getDescription();

                    kontoDAO.alterKontoNoRepeat(id);

                    konto.setDay(currentDay);
                    konto.setMonth(month + 1);
                    konto.setYear(currentYear);
                    konto.setAmount(Double.valueOf(amount));
                    konto.setCategory(category);
                    konto.setDescription(description);
                    konto.setRepeatMonth(1);
                    kontoDAO.insertKonto(konto);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /* Toast und ListView anzeigen */
        showInputs();
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

    public void showInputs() {
        Intent intent = getIntent();


        if (intent.hasExtra("amount")) {
            String date = intent.getStringExtra("date");
            Double amount = intent.getDoubleExtra("amount", 1.0);
            String category = intent.getStringExtra("name");
            String time = intent.getStringExtra("time");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String currency = preferences.getString("Currency", "0");
            NumberFormat formatter = null;

            if (currency.equals("0")) {
                formatter = NumberFormat.getCurrencyInstance(Locale.US);
            } else if (currency.equals("1")) {
                formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            } else if (currency.equals("2")) {
                formatter = NumberFormat.getCurrencyInstance(Locale.UK);
            } else if (currency.equals("3")) {
                formatter = NumberFormat.getCurrencyInstance(swiss);
            }

            String formamount = formatter.format(amount);

            // Dem User mitteilen, was gespeichert wurde
            String message = String.valueOf(formamount + " saved in '" + category + "' \n on " + String.valueOf(date));
            // Mit for Schleife die Dauer des Toasts verdoppeln
            final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            LinearLayout layout = (LinearLayout) toast.getView();
            if (layout.getChildCount() > 0) {
                TextView tv = (TextView) layout.getChildAt(0);
                tv.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                toast.show();
            }
        }
    }

    public void openCategory(View v) {

        Intent intent = new Intent(getApplicationContext(), Category.class);
        String category = v.getTag().toString();

        intent.putExtra("name", category);
        String nameLowercase = category.toLowerCase();

        intent.putExtra("iconName", nameLowercase);
        startActivity(intent);
    }

    public static Integer getCurrentDay(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("DD");
            String currentTimeStamp = dateFormat.format(new Date());  // Find todays date

            return Integer.parseInt(currentTimeStamp.toString());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public static Integer getCurrentMonth(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
            String currentTimeStamp = dateFormat.format(new Date());  // Find todays date

            return Integer.parseInt(currentTimeStamp.toString());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public static Integer getCurrentYear(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            String currentTimeStamp = dateFormat.format(new Date());  // Find todays date

            return Integer.parseInt(currentTimeStamp.toString());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public void openDeleteEntry(View v){
        Intent intent = new Intent(getApplicationContext(), DeleteEntries.class);

        startActivity(intent);
    }

}
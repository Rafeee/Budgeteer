package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
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
import java.util.Locale;

public class MainActivity extends BaseActivity {
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml
        set(navMenuTitles,navMenuIcons);

        /*show icons*/
        ImageButton icon1 = (ImageButton) findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.apartment);
        ImageButton icon2 = (ImageButton) findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.clothes);
        ImageButton icon3 = (ImageButton) findViewById(R.id.icon3);
        icon3.setImageResource(R.drawable.phone);
        ImageButton icon4 = (ImageButton) findViewById(R.id.icon4);
        icon4.setImageResource(R.drawable.car);
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
        icon10.setImageResource(R.drawable.pets);
        ImageButton icon11 = (ImageButton) findViewById(R.id.icon11);
        icon11.setImageResource(R.drawable.apartment);
        ImageButton icon12 = (ImageButton) findViewById(R.id.icon12);
        icon12.setImageResource(R.drawable.income);

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

            // Betrag als Euro formatieren
            // TODO: Verschiedene Währungen unterscheiden
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            String formamount = formatter.format(amount);

            // Dem User mitteilen, was gespeichert wurde
            String message = String.valueOf(formamount + " saved in '" + category + "' \n on " + String.valueOf(date)+" heute: "+time);
            // Mit for Schleife die Dauer des Toasts verdoppeln
            /*for (int i = 0; i < 2; i++) {*/
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

    /*public void openOverview(View v) {
        Intent intent = new Intent(getApplicationContext(), DatePicker.class);
        startActivity(intent);
    }*/

}
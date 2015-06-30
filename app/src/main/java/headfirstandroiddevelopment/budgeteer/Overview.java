package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class Overview extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /*final static String MY_DB_NAME = "Budgeteer";
    final static String MY_DB_TABLE = "konto";
    final static String tag = "ensacom";
*/

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        showOverviewByDate();
        /*setContentView(R.layout.activity_main);*/

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.overview_activity));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
        int day = 1;
        int month = 6;
        int year = 2015;

        List<Konto> kontoByDate = kontoDAO.getKontoByDate(month, year);
        ArrayAdapter adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);

        adapter.add("Übersicht vom "+day+"."+month+"."+year);

        for(Konto konto : kontoByDate){
            adapter.add(konto);
        }

        ListView overview = (ListView) findViewById(R.id.listoverview);
        overview.setAdapter(adapter);
        kontoDAO.close();
    }
     /*   Intent intent = getIntent();
        if (intent.hasExtra("amount")) {
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            String date = day + "." + month + "." +year;
            Double amount = intent.getDoubleExtra("amount", 1.0);
            String category = intent.getStringExtra("name");

            // Betrag als Euro formatieren
            // TODO: Verschiedene Währungen unterscheiden
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            String formamount = formatter.format(amount);

            // Dem User mitteilen, dass Eingabe gespeichert wurde
            String message = String.valueOf(formamount+ " am " + String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year) + " in " + category + " gespeichert");
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();

            // Daten in Liste ausgeben
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            adapter.add("Übersicht von '" +category+"'");

            adapter.add(date +" : "+formamount);

            ListView list = (ListView) findViewById(R.id.listoverview);
            list.setAdapter(adapter);
        }
    }*/
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.overview_container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Overview) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

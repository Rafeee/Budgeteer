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
import android.widget.ListView;
import android.widget.Toast;

import java.text.NumberFormat;


public class Overview extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    final static String MY_DB_NAME = "Budgeteer";
    final static String MY_DB_TABLE = "konto";
    final static String tag = "ensacom";

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        getInputs();

        /*http://android-developers.de/thread/414-der-umgang-mit-der-sqlite-datenbank/*/
        onCreateDBAndDBTabled();
        dropDB();
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

    /** Datenbank und Tabellen erstellen wenn noch nicht vorhanden */

    /** TODO: Testen ob Datenbankconnection funktioniert
     *
     */
    private void onCreateDBAndDBTabled()
    {
        SQLiteDatabase myDB = null;
        try {
            myDB = this.openOrCreateDatabase(MY_DB_NAME, MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + MY_DB_TABLE
                            + " (konto_id integer primary key autoincrement, datum integer(100), betrag real(100));");

            Intent intent = getIntent();
            int day = intent.getIntExtra("day", 0);
            Double amount = 12.3; /* zum testen: später double amount */
             /*String category = intent.getStringExtra("name");*/

            myDB.execSQL("INSERT INTO "+MY_DB_TABLE+" (datum, betrag) "
                +"VALUES ('"+day+"',"+
                "'"+amount+
                "');");
            Log.v(tag, "Insert new Entry: " + day + ", " + amount);
            } catch (Exception e){
                Log.v(tag, e.getMessage());
            }
    }
    public void dropDB(){


    }
    public void getInputs(){
        Intent intent = getIntent();
        if (intent.hasExtra("amount")) {
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            String date = day + "." + month + "." +year;

            String category = intent.getStringExtra("name");

            NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
            String message = String.valueOf( ".- am " + String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year) + " in " + category + " gespeichert");

            /** Dem User mitteilen, dass Eingabe gespeichert wurde */
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();

            /* Daten in Liste ausgeben */
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            adapter.add("Übersicht: " +category);
            adapter.add("Speicherdatum: "+date);

            ListView list = (ListView) findViewById(R.id.listoverview);
            list.setAdapter(adapter);
        }
    }


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
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

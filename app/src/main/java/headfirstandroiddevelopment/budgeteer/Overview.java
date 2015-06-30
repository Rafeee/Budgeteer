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
import android.widget.ListView;
import android.widget.Toast;

import java.text.NumberFormat;


public class Overview extends BaseActivity {

    final static String MY_DB_NAME = "Budgeteer";
    final static String MY_DB_TABLE = "konto";
    final static String tag = "ensacom";

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load titles from strings.xml

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);//load icons from strings.xml
        set(navMenuTitles, navMenuIcons);

        getInputs();

        /*http://android-developers.de/thread/414-der-umgang-mit-der-sqlite-datenbank/*/
        onCreateDBAndDBTabled();
        dropDB();
        /*setContentView(R.layout.activity_main);*/

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

    /** Datenbank und Tabellen erstellen wenn noch nicht vorhanden */

    /**
     * TODO: Testen ob Datenbankconnection funktioniert
     */
    private void onCreateDBAndDBTabled() {
        SQLiteDatabase myDB = null;
        try {
            myDB = this.openOrCreateDatabase(MY_DB_NAME, MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + MY_DB_TABLE
                    + " (konto_id integer primary key autoincrement, datum integer(100), betrag real(100));");

            Intent intent = getIntent();
            int day = intent.getIntExtra("day", 0);
            Double amount = 12.3; /* zum testen: später double amount */
             /*String category = intent.getStringExtra("name");*/

            myDB.execSQL("INSERT INTO " + MY_DB_TABLE + " (datum, betrag) "
                    + "VALUES ('" + day + "'," +
                    "'" + amount +
                    "');");
            Log.v(tag, "Insert new Entry: " + day + ", " + amount);
        } catch (Exception e) {
            Log.v(tag, e.getMessage());
        }
    }

    public void dropDB() {


    }

    public void getInputs() {
        Intent intent = getIntent();
        if (intent.hasExtra("amount")) {
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            String date = day + "." + month + "." + year;

            String category = intent.getStringExtra("name");

            NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
            String message = String.valueOf(".- am " + String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year) + " in " + category + " gespeichert");

            /** Dem User mitteilen, dass Eingabe gespeichert wurde */
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();

            /* Daten in Liste ausgeben */
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            adapter.add("Übersicht: " + category);
            adapter.add("Speicherdatum: " + date);

            ListView list = (ListView) findViewById(R.id.listoverview);
            list.setAdapter(adapter);
        }
    }

}

package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Overview extends ActionBarActivity {

    final static String MY_DB_NAME = "Budgeteer";
    final static String MY_DB_TABLE = "konto";
    final static String tag = "ensacom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);






        Intent intent = getIntent();
        if (intent.hasExtra("amount")) {
        /*if(intent.getStringExtra("year")!= ""){*/
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            String category = intent.getStringExtra("name");
            String amount = intent.getStringExtra("amount");
            String message = "$" + String.valueOf(amount) + " am " + String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year) + " in " + category + " gespeichert";

            /** Dem User mitteilen, dass Eingabe gespeichert wurde */
            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();

            /* Daten in Liste ausgeben */
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            adapter.add(intent.getStringExtra("name"));
            ListView list = (ListView) findViewById(R.id.listoverview);

        }
        /*http://android-developers.de/thread/414-der-umgang-mit-der-sqlite-datenbank/*/
        onCreateDBAndDBTabled();
        dropDB();
        /*setContentView(R.layout.activity_main);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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
}

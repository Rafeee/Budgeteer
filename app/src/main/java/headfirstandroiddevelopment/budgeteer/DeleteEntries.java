package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class DeleteEntries extends ActionBarActivity {
    private int position=0;
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entries);

        //deleteEntry(2); Funktioniert
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllEntries();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_entries, menu);
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
    public void getAllEntries(){

        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openReadable();

        List<Konto> kontoData = kontoDAO.getAll();
        ArrayAdapter adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);
        for(Konto konto : kontoData) {
            id = konto.getId();
            adapter.add(konto);
        }
        ListView overview = (ListView) findViewById(R.id.deleteEntries);
        overview.setAdapter(adapter);
        //TODO onItemClickListener der position / idkonto zurückgibt
        overview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();
                Toast.makeText(getBaseContext(), ""+id, Toast.LENGTH_LONG).show();
            }
        });
        this.position = overview.getSelectedItemPosition();


        kontoDAO.close();
    }
    public void showPosition(View v){
        Intent intent = new Intent(this, DeleteEntries.class);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), ("Datensatz gelöscht ("+this.position+")"), Toast.LENGTH_LONG);
        toast.show();
    }
    public void deleteEntry(int id){
        KontoDAO kontoDAO = new KontoDAO(this);
        kontoDAO.openWritable();

        List<Konto> kontoData = kontoDAO.getAll();

        for (Konto konto : kontoData){
            if (konto.getId() == id ){
                kontoDAO.deleteKonto(id);}
        }
    }
}

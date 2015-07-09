package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


public class DeleteEntries extends ActionBarActivity {
    private int position=0;
    private Integer selectedKontoId;
    private ArrayAdapter<Konto> adapter;
    private HashMap<Integer, Konto> kontoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entries);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedKontoId == 0) {
                    Toast.makeText(getApplicationContext(), "Please select Entry", Toast.LENGTH_LONG).show();
                } else {
                    deleteEntry(selectedKontoId);
                    adapter.remove(kontoData.get(selectedKontoId));
                    kontoData.remove(selectedKontoId);
                    adapter.notifyDataSetChanged();
                }
            }
        });
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

        kontoData= kontoDAO.getAllHash();

        adapter = new ArrayAdapter<Konto>(this, android.R.layout.simple_list_item_1);
        for(Konto konto : kontoData.values()) {
            adapter.add(konto);
        }
        ListView overview = (ListView) findViewById(R.id.deleteEntries);
        overview.setAdapter(adapter);
        //TODO onItemClickListener der position / idkonto zurückgibt
        overview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();
                Konto konto = (Konto) parent.getItemAtPosition(position);
                selectedKontoId = konto.getId();
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
        kontoDAO.deleteKonto(id);
        kontoDAO.close();
    }
}

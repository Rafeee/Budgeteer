package headfirstandroiddevelopment.budgeteer;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStream;


public class Category extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        EditText editAmount = (EditText) findViewById(R.id.amount);
        final String valueAmount = editAmount.getText().toString();

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("name");
        TextView title = (TextView) findViewById(R.id.categoryTitle);
        title.setText(categoryName);

        String iconName = intent.getStringExtra("iconName");
        ImageView ivIcon = (ImageView) findViewById(R.id.iconCategory);
        ivIcon.setImageResource(getResources().getIdentifier(iconName, "drawable", getPackageName()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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
    public void save(View v){
        EditText amount = (EditText) findViewById(R.id.amount);
        DatePicker date = (DatePicker) findViewById(R.id.datePicker);

        if (!amount.getText().toString().equals("")) {
            Intent intent = new Intent(getApplicationContext(), Overview.class);

            TextView category = (TextView) findViewById(R.id.categoryTitle);
            String categoryName = category.getText().toString();
            int day = date.getDayOfMonth();
            int month = date.getMonth() + 1;
            int year = date.getYear();

            intent.putExtra("amount", amount.getText().toString());
            intent.putExtra("day", day);
            intent.putExtra("month",  month);
            intent.putExtra("year",  year);
            intent.putExtra("name", categoryName);

            startActivity(intent);
        }
        /** Dem Benutzer mitteilen, dass keine Eingabe gemacht wurde
         *
         */
        else
           Toast.makeText(getApplicationContext(), "Please Enter Amount", Toast.LENGTH_LONG).show();
    }
}

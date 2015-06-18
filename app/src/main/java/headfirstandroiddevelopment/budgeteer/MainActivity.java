package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*show icons*/
        ImageButton icon1 = (ImageButton) findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.apartment);
        ImageButton icon2 = (ImageButton) findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.phone);
        ImageButton icon3 = (ImageButton) findViewById(R.id.icon3);
        icon3.setImageResource(R.drawable.shoppingbasket);
        ImageButton icon4 = (ImageButton) findViewById(R.id.icon4);
        icon4.setImageResource(R.drawable.luxus);
        ImageButton icon5 = (ImageButton) findViewById(R.id.icon5);
        icon5.setImageResource(R.drawable.apartment);
        ImageButton icon6 = (ImageButton) findViewById(R.id.icon6);
        icon6.setImageResource(R.drawable.phone);
        ImageButton icon7 = (ImageButton) findViewById(R.id.icon7);
        icon7.setImageResource(R.drawable.shoppingbasket);
        ImageButton icon8 = (ImageButton) findViewById(R.id.icon8);
        icon8.setImageResource(R.drawable.luxus);
        ImageButton icon9 = (ImageButton) findViewById(R.id.icon9);
        icon9.setImageResource(R.drawable.apartment);
        ImageButton icon10 = (ImageButton) findViewById(R.id.icon10);
        icon10.setImageResource(R.drawable.luxus);
        ImageButton icon11 = (ImageButton) findViewById(R.id.icon11);
        icon11.setImageResource(R.drawable.apartment);
        ImageButton icon12 = (ImageButton) findViewById(R.id.icon12);
        icon12.setImageResource(R.drawable.apartment);

        Intent intent = getIntent();
        if (intent.getIntExtra("day", 0) != 0) {
        /*if(intent.getStringExtra("year")!= ""){*/
            int day = intent.getIntExtra("day", 0);
            int month = intent.getIntExtra("month", 0);
            int year = intent.getIntExtra("year", 0);
            String category = intent.getStringExtra("name");
            String amount = intent.getStringExtra("amount");
            String message = "$" + String.valueOf(amount) + " am " + String.valueOf(day) + "." + String.valueOf(month) + "." + String.valueOf(year) +" in " + category +" gespeichert";

            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void openCategory(View v) {

        Intent intent = new Intent(getApplicationContext(), Category.class);
        String category = v.getTag().toString();

        intent.putExtra("name", category);
        String nameLowercase = category.toLowerCase();

        intent.putExtra("iconName", nameLowercase);
        startActivity(intent);
    }
}

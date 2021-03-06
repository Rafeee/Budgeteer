package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class CategoryPicker extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
    public void showOverview(View v){
        Intent intent = new Intent (getApplicationContext(), Overview.class);
        String category = v.getTag().toString();
        intent.putExtra("category", category);
        intent.putExtra("lastView", "category");
        startActivity(intent);
    }
}

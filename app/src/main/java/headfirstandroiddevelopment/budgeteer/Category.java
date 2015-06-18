package headfirstandroiddevelopment.budgeteer;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        EditText editAmount = (EditText) findViewById(R.id.editText);
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
}

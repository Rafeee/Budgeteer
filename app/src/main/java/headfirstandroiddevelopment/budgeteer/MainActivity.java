package headfirstandroiddevelopment.budgeteer;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;



public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.main_layout));

        /*show icons*/
        ImageButton icon1 = (ImageButton) findViewById(R.id.icon1);
        icon1.setImageResource(R.drawable.apartment);
        ImageButton icon2 = (ImageButton) findViewById(R.id.icon2);
        icon2.setImageResource(R.drawable.phone);
        ImageButton icon3 = (ImageButton) findViewById(R.id.icon3);
        icon3.setImageResource(R.drawable.food);
        ImageButton icon4 = (ImageButton) findViewById(R.id.icon4);
        icon4.setImageResource(R.drawable.luxus);
        ImageButton icon5 = (ImageButton) findViewById(R.id.icon5);
        icon5.setImageResource(R.drawable.apartment);
        ImageButton icon6 = (ImageButton) findViewById(R.id.icon6);
        icon6.setImageResource(R.drawable.phone);
        ImageButton icon7 = (ImageButton) findViewById(R.id.icon7);
        icon7.setImageResource(R.drawable.food);
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

    public void openCategory(View v) {

        Intent intent = new Intent(getApplicationContext(), Category.class);
        String category = v.getTag().toString();

        intent.putExtra("name", category);
        String nameLowercase = category.toLowerCase();

        intent.putExtra("iconName", nameLowercase);
        startActivity(intent);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
// update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
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
package id.hw.labs.movieupdate.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.hw.labs.movieupdate.R;
import id.hw.labs.movieupdate.fragments.MovieFragment;
import id.hw.labs.movieupdate.fragments.TVShowsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // check if only orientation changed
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_movie);
            setSelectedFragment(R.id.nav_movie);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        setSelectedFragment(item.getItemId());
        return true;
    }

    public void setSelectedFragment(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_movie) {
            fragment = new MovieFragment();
        } else if (id == R.id.nav_tv) {
            fragment = new TVShowsFragment();
        } else if (id == R.id.nav_contactdev) {
            StringBuilder sbMessage = new StringBuilder();
            try {
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
                sbMessage.append("Device Info:\n");
                sbMessage.append("v"+info.versionName+"("+info.versionCode+")\n");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            // Message
            sbMessage.append("Brand: "+android.os.Build.BRAND + "\n");
            sbMessage.append("Device: "+android.os.Build.DEVICE + "\n");
            sbMessage.append("Manufacture: "+android.os.Build.MANUFACTURER + "\n");
            sbMessage.append("Model: "+android.os.Build.MODEL + "\n");

            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + getResources().getString(R.string.app_name)
                    + "&body=" + sbMessage.toString() + "Feedback: "
                    + "&to=" + getResources().getString(R.string.email_developer));
            i.setData(data);
            startActivity(Intent.createChooser(i, "Send Feedback"));
        }

        if(fragment!=null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}

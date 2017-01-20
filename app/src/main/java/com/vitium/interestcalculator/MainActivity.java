package com.vitium.interestcalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;

import android.support.v4.app.Fragment;


public class MainActivity extends AppCompatActivity {


    DrawerLayout mDrawer;
    Toolbar toolbar;
    NavigationView nvDrawer;

    ActionBarDrawerToggle drawerToggle;

    int currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new SimpleFragment()).commit();
            setTitle("Simple");
        }



        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        mDrawer.addDrawerListener(drawerToggle);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);


    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstaBundle) {
        super.onPostCreate(savedInstaBundle);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.simple:
                fragmentClass = SimpleFragment.class;
                currentMenu = R.id.simple;
                break;
            case R.id.compound:
                fragmentClass = CompoundFragment.class;
                currentMenu = R.id.compound;
                break;
            case R.id.about:
                fragmentClass = AboutFragment.class;
                currentMenu = R.id.about;
                break;
            default:
                fragmentClass = SimpleFragment.class;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            //Log.v("main", "fragment set");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(!menuItem.isChecked())
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            //Log.v("main", "fragment commit");
            currentMenu = menuItem.getItemId();
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());



        mDrawer.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}

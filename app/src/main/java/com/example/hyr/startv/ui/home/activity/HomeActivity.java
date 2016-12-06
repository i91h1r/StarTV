package com.example.hyr.startv.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.example.hyr.startv.R;
import com.example.hyr.startv.ui.download.DownloadActivity;
import com.example.hyr.startv.ui.home.SubFragment;
import com.example.hyr.startv.ui.home.VChartFragment;
import com.example.hyr.startv.ui.home.fragment.HomeFragment;
import com.example.hyr.startv.ui.home.fragment.NavigationFragment;
import com.example.hyr.startv.ui.search.activity.SearchActivity;

/**
 * Description:
 * 作者：hyr on 2016/11/3 09:22
 * 邮箱：2045446584@qq.com
 */
public class HomeActivity extends SearchActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    LinearLayout iconLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private int currentTabIndex;
    private Fragment[] fragments;
    private int index;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initToolbar();

        initNavigation();

        initFragment();

    }


    private void initFragment() {

        HomeFragment homeFragment = HomeFragment.getInstance();

        NavigationFragment navigationFragment = NavigationFragment.getInstance();

        VChartFragment vChartFragment = VChartFragment.getInstance();

        SubFragment subFragment = SubFragment.getInstance();

        fragments = new Fragment[] {
            homeFragment,
            navigationFragment,
            vChartFragment,
            subFragment
        };

        getSupportFragmentManager().beginTransaction()
            .add(R.id.container, homeFragment).show(homeFragment)
            .commit();
    }


    private void initNavigation() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        ColorStateList colorStateList = getApplicationContext().getResources()
            .getColorStateList(R.color.navigation_menu_item_color);

        navigationView.setItemTextColor(colorStateList);

        navigationView.setItemIconTintList(colorStateList);
    }


    private void initToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iconLayout = (LinearLayout) findViewById(R.id.icon_layout);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);

        toolbar.setContentInsetsAbsolute(0, 0);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        iconLayout.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

    }


    public static void open(Context context) {
        Intent intent = new Intent();

        intent.setClass(context, HomeActivity.class);

        context.startActivity(intent);

    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        iniSearchView();
    }


    private void iniSearchView() {

        setSearchView();

        customSearchView(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                searchView.open(true, item);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:

                changeFragmentIndex(item, 0);

                toolbar.setVisibility(View.VISIBLE);

                break;

            case R.id.nav_navigation:
                changeFragmentIndex(item, 1);

                toolbar.setVisibility(View.GONE);
                break;

            case R.id.nav_v:
                changeFragmentIndex(item, 2);
                break;

            case R.id.nav_sub:

                changeFragmentIndex(item, 3);
                break;

            case R.id.nav_my:

                break;

            case R.id.download:

                DownloadActivity.open(this);


                break;
        }

        item.setChecked(true);

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    private void changeFragmentIndex(MenuItem item, int currentIndex) {

        index = currentIndex;
        switchFragment();
        item.setChecked(true);

    }


    private void switchFragment() {

        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded()) {
            trx.add(R.id.container, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;

    }

}

package com.cn.guojinhu.dota2book.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cn.guojinhu.dota2book.base.BaseActivity;
import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.ui.equipments.EquipmentsFragment;
import com.cn.guojinhu.dota2book.ui.heroes.HeroesFragment;
import com.cn.guojinhu.dota2book.ui.news.news.NewsFragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    private MainPresenter mMainPresenter;

    private static final int NEWS = 0;
    private static final int HEROES = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        mMainPresenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.start();
    }

    @Override
    public void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void initListener() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {
        disableSwipeBack();
        setSupportActionBar(mToolbar);
        //该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
        //mDrawerToggle与mDrawerLayout的打开状态同步
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("Vo7ice", "onConfigurationChanged");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mMainPresenter.manageFragment(item);
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter p) {
        mMainPresenter = (MainPresenter) checkNotNull(p);
    }

    @Override
    public boolean IsDrawerOpened() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }


    @Override
    public void closeDrawerIfNeeded() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void switch2News() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new NewsFragment(), getResources().getString(R.string.tag_news));
        ft.commit();
        mToolbar.setTitle(R.string.tag_news);
    }

    @Override
    public void switch2Heroes() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new HeroesFragment(), getResources().getString(R.string.tag_heroes));
        ft.commit();
        mToolbar.setTitle(R.string.tag_heroes);
    }

    @Override
    public void switch2Equipments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new EquipmentsFragment(), getResources().getString(R.string.tag_goods));
        ft.commit();
        mToolbar.setTitle(R.string.tag_goods);
    }
}

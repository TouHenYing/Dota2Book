package com.cn.guojinhu.dota2book.ui.main;

import android.view.MenuItem;

import com.cn.guojinhu.dota2book.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/3.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    private MainContract.View mMainView;

    private int mCurrentTagId = -1;

    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;

        mMainView = checkNotNull(mMainView, "mMainView cannot be null!");

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        switch (mCurrentTagId) {
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            default:
            case R.id.nav_heroes:
                mMainView.switch2Heroes();
                break;
        }
    }

    @Override
    public void manageFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            case R.id.nav_heroes:
                mMainView.switch2Heroes();
                break;
            case R.id.nav_goods:
                mMainView.switch2Equipments();
        }
        mCurrentTagId = item.getItemId();
        item.setChecked(true);
        mMainView.closeDrawerIfNeeded();
    }
}

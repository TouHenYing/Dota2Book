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

    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;

        mMainView = checkNotNull(mMainView, "mMainView cannot be null!");

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        mMainView.switch2News();
    }

    @Override
    public void manageFragment(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_news:
                mMainView.switch2News();
                break;
        }
        item.setChecked(true);
        mMainView.closeDrawerIfNeeded();
    }
}

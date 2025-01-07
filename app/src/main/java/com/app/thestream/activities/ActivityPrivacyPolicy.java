package com.app.thestream.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.thestream.Config;
import com.app.thestream.R;
import com.app.thestream.databases.prefs.SharedPref;
import com.app.thestream.models.Settings;
import com.app.thestream.rests.ApiInterface;
import com.app.thestream.rests.RestAdapter;
import com.app.thestream.utils.Constant;
import com.app.thestream.utils.Tools;
import com.facebook.shimmer.ShimmerFrameLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPrivacyPolicy extends AppCompatActivity {

    SharedPref sharedPref;
    Settings post;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.getTheme(this);
        setContentView(R.layout.activity_privacy_policy);
        Tools.setNavigation(this);
        sharedPref = new SharedPref(this);
        webView = findViewById(R.id.web_view);
        Tools.displayContent(this, webView, sharedPref.getPrivacyPolicy());
        setupToolbar();

    }

    public void setupToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (sharedPref.getIsDarkTheme()) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorToolbarDark));
        } else {
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_setting_privacy));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}

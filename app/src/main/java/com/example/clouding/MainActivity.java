package com.example.clouding;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
//    AdView mAdView;
    InterstitialAd mInterstitialAd;
    int i = 1;
    int click = 0;
    
    View background_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-2312628352208346~2944470807");

        frameLayout = findViewById(R.id.f1);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.f1, new Fragment_Three());
        fragmentTransaction.commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        background_view = findViewById( R.id.background_view );
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-2312628352208346/5353375241");
//        ca-app-pub-2312628352208346/5353375241
//        ca-app-pub-3940256099942544/1033173712 nice
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when the ad is displayed.
//            }
//
//            @Override
//            public void onAdClicked() {
//                // Code to be executed when the user clicks on an ad.
//                click++;
//
//
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when the interstitial ad is closed.
//
//            }
//        });


//        fab.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//
//                return false;
//
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click==1){
                    background_view.setAlpha( 0f );
                    click++;
                }else {
                    background_view.setAlpha( 1f );
                    click--;
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (i == 0) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.f1, new Fragment_Three());
            fragmentTransaction.commit();
            i = 1;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.f1, new Fragment_One());
            fragmentTransaction.commit();
//            if (click <=2) {
//
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                    Toast.makeText(getApplicationContext(),"This is add",Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }
//                mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
//
//            }else{
//                click--;
//            }
        } else if (id == R.id.nav_gallery) {
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Two() );
            fragmentTransaction.commit();
        } else if (id == R.id.nav_slideshow) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Three() );
            fragmentTransaction.commit();
        } else if (id == R.id.nav_tools) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Four() );
            fragmentTransaction.commit();
        } else if (id == R.id.nav_share) {
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Five() );
            fragmentTransaction.commit();
        } else if (id == R.id.nav_send) {
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Six() );
            fragmentTransaction.commit();
        } else if (id == R.id.recycler) {
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Seven() );
            fragmentTransaction.commit();
        } else if (id == R.id.bar) {
            i = 0;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.f1, new Fragment_Eight() );
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}

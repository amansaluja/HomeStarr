package com.showorld.housestar.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.showorld.housestar.R;

public class MainActivity extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Search");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Notification");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("Account");


        tab1.setIndicator("", getResources().getDrawable(R.mipmap.home));
        //tab1.setIndicator("Home", getResources().getDrawable(R.drawable.icons));
        tab1.setContent(new Intent(this, HomeActivity.class));

        tab2.setIndicator("", getResources().getDrawable(R.mipmap.search));
        tab2.setContent(new Intent(this, SearchActivity.class));

        tab3.setIndicator("", getResources().getDrawable(R.mipmap.notification));
        tab3.setContent(new Intent(this, NotificationActivity.class));

        tab4.setIndicator("", getResources().getDrawable(R.mipmap.accounts));
        tab4.setContent(new Intent(this, AccountActivity.class));

        //finish();

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

    }

}
package com.showorld.housestar.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Aman on 04-07-2015.
 */
public class NotificationActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView  tv=new TextView(this);
        tv.setTextSize(15);
        tv.setGravity(Gravity.CENTER);
        tv.setText("Hey! Your dish received 100 views. Congrats! \n\n You have 99 followers now...");

        setContentView(tv);
    }
}

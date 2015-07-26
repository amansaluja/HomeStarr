package com.showorld.housestar.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.showorld.housestar.R;
import com.showorld.housestar.activities.BasketItemListActivity;
//import com.servicecall.app.activity.MyIssuesListActivity;
//import com.servicecall.app.activity.SelectCategoryActivity;
//import com.servicecall.app.event.DummyEvent;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.helpers.UpdateItemsCount;
import com.showorld.housestar.model.BasketItem;

import java.lang.reflect.Method;
import java.util.ArrayList;

//import javax.inject.Inject;

//import de.greenrobot.event.EventBus;

/**
 * Created by Vaibhav on 6/14/2015.
 */
public class BaseActivity extends AppCompatActivity {

    //@Inject
    //protected EventBus eventBus;

    private boolean isStarted = false;

    Intent i;

    int hot_number = 0;
    TextView ui_hot;
    ImageView hotlist_bell;
    ArrayList<BasketItem> basketItems;
    BasketItemDAO basketItemDAO;

    int basketComplaintSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //eventBus.register(this);
        //ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        //eventBus.unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStarted = true;
    }

    @Override
    protected void onStop() {
        isStarted = false;
        super.onStop();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        new UpdateItemsCount(this.getBaseContext(), menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.cartRL:
                i = new Intent(getBaseContext(), BasketItemListActivity.class);
                startActivity(i);
                return true;
            case R.id.cart:
                i = new Intent(getBaseContext(), BasketItemListActivity.class);
                startActivity(i);
                return true;
            case R.id.numberItems:
                i = new Intent(getBaseContext(), BasketItemListActivity.class);
                startActivity(i);
                return true;
            case R.id.action_cart:
                i = new Intent(getBaseContext(), BasketItemListActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    /*public void onEventMainThread(DummyEvent event) {

    }*/
}

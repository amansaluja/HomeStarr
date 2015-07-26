package com.showorld.housestar.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.showorld.housestar.R;

/**
 * Created by Aman on 04-07-2015.
 */
public class HomeActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        ImageView newRecipe = (ImageView) findViewById(R.id.newrecipe);
        newRecipe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addRecipe = new Intent(getApplicationContext(), NewRecipeActivity.class);
                startActivity(addRecipe);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        HomeActivity.this.finish();
    }
}

package com.showorld.housestar.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.showorld.housestar.R;
import com.showorld.housestar.adapters.GridViewAdapter;
import com.showorld.housestar.base.BaseActivity;


public class NewRecipeActivity extends BaseActivity {

    AbsListView absListView;
    public String RecipeType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_grid);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("What are you going to make?");

        Resources res = getResources();
        final String[] recipeType = res.getStringArray(R.array.recipeType);
        TypedArray recipeImages = res.obtainTypedArray(R.array.recipeImages);

        absListView = (AbsListView) findViewById(R.id.gridView);
        absListView.setAdapter(new GridViewAdapter(this, recipeType, recipeImages));

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                RecipeType = recipeType[position];

                Intent typeIntent = new Intent(getApplicationContext(), IngredientTypeActivity.class);
                typeIntent.putExtra("RecipeType", RecipeType);
                startActivity(typeIntent);

                /*switch (position) {
                    case 0:

                    case 1:

                }*/
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(NewRecipeActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}

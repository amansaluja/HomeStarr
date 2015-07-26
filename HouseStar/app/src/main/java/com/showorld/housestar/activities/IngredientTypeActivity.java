package com.showorld.housestar.activities;

/**
 * Created by Aman on 05-07-2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import com.showorld.housestar.R;
import com.showorld.housestar.adapters.ListViewAdapter;
import com.showorld.housestar.adapters.ListViewAdapterYo;
import com.showorld.housestar.base.BaseActivity;


public class IngredientTypeActivity extends BaseActivity {

    AbsListView absListView;
    public String IngredientType = "";

    public String Options[];
    public int OptionImages[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_list);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Select the ingredients!");

        Resources res = getResources();
        final String[] listItems = res.getStringArray(R.array.listItems);
        TypedArray listImages = res.obtainTypedArray(R.array.listImages);

        final Resources resName = getResources();

        absListView = (AbsListView) findViewById(R.id.listView);
        absListView.setAdapter(new ListViewAdapterYo(this, listItems, listImages));

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                IngredientType = listItems[position];

                switch (position) {
                    case 0:
                        Options = new String[]{"Olive Oil", "Coconut Oil", "Mustard Oil", "Sunflower Oil",
                                "Ghee", "Refined Oil"};
                        OptionImages = new int[]{R.mipmap.olive, R.mipmap.coconut, R.mipmap.mustard,
                                R.mipmap.sunflower, R.mipmap.ghee, R.mipmap.refined};

                        break;

                    case 1:
                        Options = new String[]{"Safed Namak", "Haldi", "Lal Mirch", "Kali Mirch",
                                "Dhania Powder", "Kala Namak", "Jeera", "Jeera Powder", "Garam Masala"};
                        OptionImages = new int[]{R.mipmap.namak, R.mipmap.haldi, R.mipmap.lalmirch,
                                R.mipmap.kalimirch, R.mipmap.dhania, R.mipmap.kalanamak, R.mipmap.jeera,
                                R.mipmap.jeerapowder, R.mipmap.garam};
                        break;

                    case 2:
                        Options = new String[]{"Pyaaz", "Tamatar", "Adrak", "Lasun",
                                "Dhania Patta", "Pudina", "Cheeni", "Dahi", "Baraf", "Kacha Aam"};
                        OptionImages = new int[]{R.mipmap.pyaaz, R.mipmap.tamatar, R.mipmap.adrak,
                                R.mipmap.lasun, R.mipmap.dhaniapatta, R.mipmap.pudina, R.mipmap.cheeni,
                                R.mipmap.dahi, R.mipmap.baraf, R.mipmap.kachaaam};
                        break;

                    case 3:
                        Options = new String[]{"Gehu Aata", "Chawal", "Besan", "Sattu Aata",
                                "Maida", "Makki Aata"};
                        OptionImages = new int[]{R.mipmap.aata, R.mipmap.chawal, R.mipmap.besan,
                                R.mipmap.sattu, R.mipmap.maida, R.mipmap.makki};
                        break;

                    case 4:
                        Options = new String[]{"Doodh", "Paani"};
                        OptionImages = new int[]{R.mipmap.doodh, R.mipmap.paani};
                        break;

                    case 5:
                        Options = new String[]{"Moong Dal", "Kabuli Chana", "Rajma", "Kali Dal",
                                "Kala Chana", "Hari Moong", "Toor Dal"};
                        OptionImages = new int[]{R.mipmap.moong, R.mipmap.kabuli, R.mipmap.rajma,
                                R.mipmap.kali, R.mipmap.kalachana, R.mipmap.harimoong, R.mipmap.toor};
                        break;

                    case 6:
                        Options = new String[]{"Aaloo", "Bhindi", "Gobi", "Paneer"};
                        OptionImages = new int[]{R.mipmap.aalo, R.mipmap.bhindi, R.mipmap.gobi,
                                R.mipmap.paneer};
                        break;

                    case 7:
                        Options = new String[]{"Chicken"};
                        OptionImages = new int[]{R.mipmap.nonvegetarian};
                        break;

                    case 8:
                        Options = new String[]{"Apple", "Mango", "Orange", "Pineapple",
                                "Grapes", "Papaya"};
                        OptionImages = new int[]{R.mipmap.apple, R.mipmap.aam, R.mipmap.orange,
                                R.mipmap.pineapple, R.mipmap.grapes, R.mipmap.papaya};
                        break;

                    case 9:
                        Options = new String[]{"Butter", "Cheese", "Chocolate Powder", "Cocoa Powder",
                                "Baking Soda", "Vinegar", "Soya Sauce", "Vanilla Essence", "Bread", "Cream"};
                        OptionImages = new int[]{R.mipmap.butter, R.mipmap.cheese, R.mipmap.chocolate,
                                R.mipmap.cocoa, R.mipmap.bakingsoda, R.mipmap.vinegar, R.mipmap.soya,
                                R.mipmap.vanila, R.mipmap.bread, R.mipmap.cream};
                        break;
                }

                Intent j = new Intent(IngredientTypeActivity.this, IngredientNameActivity.class);
                j.putExtra("Options", Options);
                j.putExtra("OptionImages", OptionImages);
                startActivity(j);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(IngredientTypeActivity.this, NewRecipeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}

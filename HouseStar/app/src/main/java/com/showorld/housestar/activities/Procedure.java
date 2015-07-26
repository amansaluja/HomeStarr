package com.showorld.housestar.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.showorld.housestar.R;
import com.showorld.housestar.adapters.ProcedureListAdapter;
import com.showorld.housestar.helpers.BasketItemDAO;
import com.showorld.housestar.helpers.ProcedureListDAO;
import com.showorld.housestar.model.ProcedureList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 16-07-2015.
 */
public class Procedure  extends Activity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String TAG = "";
    private Context context;
    ImageView add;
    Button seeEntry;
    Button photo;
    Uri selectedImageUri;

    AbsListView listViewProcedure;
    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);

        context = getApplicationContext();

        add = (ImageView) findViewById(R.id.newstep);
        seeEntry = (Button) findViewById(R.id.entry);
        photo = (Button) findViewById(R.id.picture);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent addRecipe = new Intent(getApplicationContext(), BaseProcedure.class);
                startActivity(addRecipe);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //openImageIntent();
                new captureImage().execute();
            }
        });

        seeEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Entry = new Intent(getApplicationContext(), SeeEntry.class);
                Entry.putExtra("ImagePath", "");
                startActivity(Entry);
                //launchSeeEntryActivity();
            }
        });

        //get the ListView Reference
        listViewProcedure = (AbsListView) findViewById(R.id.listViewProc);

        final ProcedureListDAO procedureListDAO = new ProcedureListDAO(getApplicationContext());
        final ArrayList<ProcedureList> procedureList;
        procedureList = procedureListDAO.getAllProcedureList();

        listViewProcedure.setAdapter(new ProcedureListAdapter(this, procedureList));

        listViewProcedure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ProcedureList pItem = procedureList.get(position);
                Log.d("output", pItem.getItemList());
                OpenPopUp(view, pItem);
            }
        });
    }

    public void OpenPopUp(View arg0, final ProcedureList pItem) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.popup_procedure, null);

        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnOkay = (Button) popupView.findViewById(R.id.okay);
        Button btnChange = (Button) popupView.findViewById(R.id.change);
        final TextView procedure = (TextView) popupView.findViewById(R.id.spinnerProc);
        final TextView comment = (TextView) popupView.findViewById(R.id.cComment);
        final TextView allItems = (TextView) popupView.findViewById(R.id.allItems);
        final TextView hours = (TextView) popupView.findViewById(R.id.timeHour);
        final TextView minutes = (TextView) popupView.findViewById(R.id.timeMinute);
        LinearLayout screen = (LinearLayout) popupView.findViewById(R.id.popupscreen);

        procedure.setText(pItem.getSelectProcedure().toString());
        comment.setText(pItem.getComment().toString());
        allItems.setText(pItem.getItemList().toString());
        hours.setText(pItem.getTimeHours().toString());
        minutes.setText(pItem.getTimeMinutes().toString());

        popupWindow.setFocusable(true);

        final ProcedureListDAO pDao = new ProcedureListDAO(getApplicationContext());

        btnOkay.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        btnChange.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Procedure.this);
                alertDialogBuilder
                        .setTitle("Remove from Procedure")
                        .setMessage("Are you sure you want to remove this step from Procedure?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                pDao.removeProcedureList(pItem);
                                listViewProcedure.invalidateViews();

                                Intent i = new Intent(Procedure.this, BaseProcedure.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        screen.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //popupWindow.showAsDropDown(procedure, 20, -30);
        popupWindow.showAtLocation(listViewProcedure, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Procedure.this, BasketItemListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    //Launching camera app to capture image
    private class captureImage extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... args) {
            //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

            final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
            root.mkdirs();
            final String fname = "img_"+ System.currentTimeMillis() + ".jpg";
            final File sdImageMainDirectory = new File(root, fname);
            outputFileUri = Uri.fromFile(sdImageMainDirectory);

            // Camera.
            final List<Intent> cameraIntents = new ArrayList<Intent>();
            final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            final PackageManager packageManager = getPackageManager();
            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for(ResolveInfo res : listCam) {
                final String packageName = res.activityInfo.packageName;
                final Intent intent = new Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(packageName);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                cameraIntents.add(intent);
            }

            // Filesystem.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            // Chooser of filesystem options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

            // Add the camera options.
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
            //chooserIntent.putExtra(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(chooserIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

            return null;
        }
    }
    private String imagePath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
                    } else {
                        isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                }

                if (isCamera) {
                    selectedImageUri = outputFileUri;
                    imagePath = selectedImageUri.getPath().toString();
                } else {
                    Uri img = data == null ? null : data.getData();
                    //Uri img = data.getData();
                    imagePath = getRealPathFromURI(img);
                    //launchSeeEntryActivity();

                }

                launchSeeEntryActivity();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void launchSeeEntryActivity() {
        Intent i = new Intent(Procedure.this, SeeEntry.class);

        i.putExtra("ImagePath", imagePath);

        startActivity(i);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);//managedQuery
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);

    }

}

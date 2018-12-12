//package com.example.hashi.week3daily2alararmmanager.service;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.app.KeyguardManager;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.PowerManager;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.lineztech.farhan.cabbiidriver.R;
//import com.lineztech.farhan.cabbiidriver.fragments.AboutUsFragment;
//import com.lineztech.farhan.cabbiidriver.fragments.ContactUsFragment;
//import com.lineztech.farhan.cabbiidriver.fragments.NotificationFragment;
//import com.lineztech.farhan.cabbiidriver.fragments.PassengerRequest;
//import com.lineztech.farhan.cabbiidriver.fragments.RecentTripFragment;
//import com.lineztech.farhan.cabbiidriver.fragments.SettingsFragment;
//import com.lineztech.farhan.cabbiidriver.interfaces.ShowStatus;
//import com.lineztech.farhan.cabbiidriver.service.BackgroundService;
//import com.lineztech.farhan.cabbiidriver.universal.AppController;
//import com.lineztech.farhan.cabbiidriver.universal.CircularNetworkImageView;
//import com.lineztech.farhan.cabbiidriver.universal.MyRequestQueue;
//import com.lineztech.farhan.cabbiidriver.update_profile.UpdateProfile;
//import com.lineztech.farhan.cabbiidriver.utils.CONSTANTS;
//import com.lineztech.farhan.cabbiidriver.utils.GPSTracker;
//import com.lineztech.farhan.cabbiidriver.utils.Utils;
//import com.mikhaellopez.circularimageview.CircularImageView;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class MainActivityDrawer extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
//
//    private static String TAG = Login.class.getSimpleName();
//    public static boolean isPressed = false;
//    private Toolbar mToolbar;
//    Context context;
//    private FragmentDrawer drawerFragment;
//    private int STORAGE_PERMISSION_CODE = 23;
//    private com.android.volley.toolbox.ImageLoader imageLoader;
//    CircularNetworkImageView driver_profile_pic;
//    TextView tv_drivername, tv_email;
//    Bitmap image_bitmap;
//    Spinner spinner;
//    ShowStatus showStatus;
//    RelativeLayout relativelay_yes, relativelay_busy, relativelay_no;
//    public static boolean isSpinnerOpned = false;
//    String status;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        context = MainActivityDrawer.this;
//
//  /*      if (gps.canGetLocation()) {
////            latitude = gps.getLatitude();
////            longitude = gps.getLongitude();
//
//        } else {
//            gps.showSettingsAlert();
//        }*/
////        FirebaseMessaging.getInstance().subscribeToTopic("test");
////        FirebaseInstanceId.getInstance().getToken();
//
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//
////        powerOnCell();
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        drawerFragment = (FragmentDrawer)
//                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
//        drawerFragment.setDrawerListener(this);
//        driver_profile_pic = (CircularNetworkImageView) findViewById(R.id.driver_profile_pic);
//        tv_drivername = (TextView) findViewById(R.id.tv_drivername);
//        tv_email = (TextView) findViewById(R.id.tv_email);
//
//        relativelay_yes = (RelativeLayout) findViewById(R.id.relativelay_yes);
//        relativelay_busy = (RelativeLayout) findViewById(R.id.relativelay_busy);
//        relativelay_no = (RelativeLayout) findViewById(R.id.relativelay_no);
//
//        //Toast.makeText(this, "OnCreate is called", Toast.LENGTH_LONG).show();
//
//        relativelay_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                status = "ONLINE";
//                Utils.savePreferences("status_driver", status, context);
//                Utils.savePreferences("driver_online", "true", context);
//                spinner.setSelection(0);
//                relativelay_yes.setVisibility(View.VISIBLE);
//                relativelay_busy.setVisibility(View.GONE);
//                relativelay_no.setVisibility(View.GONE);
//                String RegistrationID = Utils.getPreferences("RegistrationID", context);
//                String driver_id = Utils.getPreferences("id", context);
//                makeJsonObjReq(driver_id, RegistrationID, status);
//                MapsFragment newFragment = new MapsFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container_body, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        relativelay_busy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                status = "OFFLINE";
//                Utils.savePreferences("driver_online", "false", context);
//                Utils.savePreferences("status_driver", status, context);
//                spinner.setSelection(1);
//                relativelay_yes.setVisibility(View.GONE);
//                relativelay_busy.setVisibility(View.GONE);
//                relativelay_no.setVisibility(View.VISIBLE);
//                String RegistrationID = Utils.getPreferences("RegistrationID", context);
//                String driver_id = Utils.getPreferences("id", context);
//                makeJsonObjReq(driver_id, RegistrationID, status);
//                MapsFragment newFragment = new MapsFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container_body, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        relativelay_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                status = "BUSY";
//                Utils.savePreferences("driver_online", "false", context);
//                Utils.savePreferences("status_driver", status, context);
//                spinner.setSelection(2);
//                relativelay_yes.setVisibility(View.GONE);
//                relativelay_busy.setVisibility(View.VISIBLE);
//                relativelay_no.setVisibility(View.GONE);
//                String RegistrationID = Utils.getPreferences("RegistrationID", context);
//                String driver_id = Utils.getPreferences("id", context);
//                makeJsonObjReq(driver_id, RegistrationID, status);
//                MapsFragment newFragment = new MapsFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container_body, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        spinner = (Spinner) findViewById(R.id.spinner);
// /*       String driverStatus = Utils.getPreferences("driverStatus", context);
//
//        if (driverStatus.equals("ONLINE")) {
//            relativelay_yes.setVisibility(View.VISIBLE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.GONE);
//
//        }else if(driverStatus.equals("OFFLINE")){
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.VISIBLE);
//        }else if(driverStatus.equals("BUSY")) {
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.VISIBLE);
//            relativelay_no.setVisibility(View.GONE);
//
//        }*/
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
////                Toast.makeText(context, status[position], Toast.LENGTH_LONG).show();
//
//                if (position == 0) {
//                    status = "ONLINE";
//                    Utils.savePreferences("driver_online", "true", context);
//                    Utils.savePreferences("status_driver", status, context);
//                    relativelay_yes.setVisibility(View.VISIBLE);
//                    relativelay_busy.setVisibility(View.GONE);
//                    relativelay_no.setVisibility(View.GONE);
//
//                } else if (position == 1) {
//                    status = "OFFLINE";
//                    Utils.savePreferences("driver_online", "false", context);
//                    Utils.savePreferences("status_driver", status, context);
//                    relativelay_yes.setVisibility(View.GONE);
//                    relativelay_busy.setVisibility(View.GONE);
//                    relativelay_no.setVisibility(View.VISIBLE);
//                } else {
//                    status = "BUSY";
//                    Utils.savePreferences("driver_online", "false", context);
//                    Utils.savePreferences("status_driver", status, context);
//                    relativelay_yes.setVisibility(View.GONE);
//                    relativelay_busy.setVisibility(View.VISIBLE);
//                    relativelay_no.setVisibility(View.GONE);
//                }
//                String RegistrationID = Utils.getPreferences("RegistrationID", context);
//                String driver_id = Utils.getPreferences("id", context);
//                makeJsonObjReq(driver_id, RegistrationID, status);
//
//                MapsFragment newFragment = new MapsFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container_body, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        // display the first navigation drawer view on app launch
////        displayView(0);
//
//        if (MapsFragment.iscomingFromTgleButton == false) {
//            MapsFragment newFragment = new MapsFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container_body, newFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        } else {
//
//        }
///*Permissions*/
//        /*if (isReadStorageAllowed()) {
//            //If permission is already having then showing the toast
//            //Existing the method with return
//        }*/
///*Permissions*/
//        //requestStoragePermission();
///*automatically  makes vailable */
//      /*  status = Utils.getPreferences("status_driver", context);
//
//        if (status.equalsIgnoreCase("ONLINE")) {
//            spinner.setSelection(0);
//
//            status = "ONLINE";
//            Utils.savePreferences("status_driver", status, context);
//            Utils.savePreferences("driver_online", "true", context);
//            relativelay_yes.setVisibility(View.VISIBLE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.GONE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
//
////            MapsFragment newFragment2 = new MapsFragment();
////            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
////            transaction2.replace(R.id.container_body, newFragment2);
////            transaction2.addToBackStack(null);
////            transaction2.commit();
//
//
//        } else if (status.equalsIgnoreCase("OFFLINE")) {
//            status = "OFFLINE";
//
//            spinner.setSelection(1);
//            Utils.savePreferences("status_driver", status, context);
//            Utils.savePreferences("driver_online", "false", context);
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.VISIBLE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
//
////            MapsFragment newFragment3 = new MapsFragment();
////            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
////            transaction3.replace(R.id.container_body, newFragment3);
////            transaction3.addToBackStack(null);
////            transaction3.commit();
//
//        } else {
//            status = "BUSY";
//            spinner.setSelection(2);
//            Utils.savePreferences("status_driver", status, context);
//            Utils.savePreferences("driver_online", "false", context);
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.VISIBLE);
//            relativelay_no.setVisibility(View.GONE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
////
////            MapsFragment newFragment3 = new MapsFragment();
////            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
////            transaction3.replace(R.id.container_body, newFragment3);
////            transaction3.addToBackStack(null);
////            transaction3.commit();
//        }
//*/
//
////        status = "";
////        Utils.savePreferences("status_driver",status,context);
//    /*    spinner.setSelection(0);
//        relativelay_yes.setVisibility(View.VISIBLE);
//        relativelay_busy.setVisibility(View.GONE);
//        relativelay_no.setVisibility(View.GONE);
//        String RegistrationID = Utils.getPreferences("RegistrationID", context);
//        String driver_id = Utils.getPreferences("id", context);
//        makeJsonObjReq(driver_id, RegistrationID, status);*/
//
//
//   /*     int delay = 1000 * 15; // delay for 15 sec.
//        int period = 1000 * 15; // interval to send data
//        Timer timer = new Timer();
//
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
////                Intent background = new Intent(context, BackgroundService.class);
////                context.startService(background);
//                String goto_dropoff_location = Utils.getPreferences("goto_dropoff_location", context);
////                driverStatus = Utils.getPreferences("driverStatus", context);
//                String driverStatus = Utils.getPreferences("status_driver", context);
////                if(TextUtils.isEmpty(driverStatus)){
////                    driverStatus="ONLINE";
////
////                }
//                if (driverStatus.equals("ONLINE") ) {
//                    Intent background = new Intent(context, BackgroundService.class);
//                    context.startService(background);
//                    Log.d("clicked", "clicked");
//                }
//                Log.d("clicked","clicked");
//            }
//        }, delay, period);*/
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        try {
//            String picture = Utils.getPreferences("picture_user", context);
//            String url = CONSTANTS.ImageUrl + picture;
//            imageLoader = AppController.getInstance().getImageLoader();
//            driver_profile_pic.setImageUrl(url, imageLoader);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        new AsyncCallWS2().execute();
//
//        String email = Utils.getPreferences("username", context);
//        String first_name = Utils.getPreferences("first_name", context);
//        String last_name = Utils.getPreferences("last_name", context);
//
//        //Toast.makeText(this, ""+first_name, Toast.LENGTH_LONG).show();
//
//        if (!TextUtils.isEmpty(email)) {
//            tv_email.setText(email);
//        }
//        if (!TextUtils.isEmpty(first_name)) {
//            tv_drivername.setText(first_name + " " + last_name);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        status = Utils.getPreferences("status_driver", context);
//
//        if (status.equalsIgnoreCase("ONLINE")) {
//            spinner.setSelection(0);
//
//            status = "ONLINE";
//            Utils.savePreferences("status_driver", status, context);
//            Utils.savePreferences("driver_online", "true", context);
//            relativelay_yes.setVisibility(View.VISIBLE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.GONE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
////
////            MapsFragment newFragment3 = new MapsFragment();
////            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
////            transaction3.replace(R.id.container_body, newFragment3);
////            transaction3.addToBackStack(null);
////            transaction3.commit();
//
//        } else if (status.equalsIgnoreCase("OFFLINE")) {
//            status = "OFFLINE";
//
//            spinner.setSelection(1);
//            Utils.savePreferences("driver_online", "false", context);
//            Utils.savePreferences("status_driver", status, context);
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.GONE);
//            relativelay_no.setVisibility(View.VISIBLE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
//
////            MapsFragment newFragment3 = new MapsFragment();
////            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
////            transaction3.replace(R.id.container_body, newFragment3);
////            transaction3.addToBackStack(null);
////            transaction3.commit();
//        } else {
//            status = "BUSY";
//            spinner.setSelection(2);
//            Utils.savePreferences("status_driver", status, context);
//            Utils.savePreferences("driver_online", "false", context);
//            relativelay_yes.setVisibility(View.GONE);
//            relativelay_busy.setVisibility(View.VISIBLE);
//            relativelay_no.setVisibility(View.GONE);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReq(driver_id, RegistrationID, status);
//
////            MapsFragment newFragment3 = new MapsFragment();
////            FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
////            transaction3.replace(R.id.container_body, newFragment3);
////            transaction3.addToBackStack(null);
////            transaction3.commit();
//        }
//
//
//    }
//
//    public void onBackPressed() {
//
//
//        if (isPressed == false) {
//         /*   MapsFragment newFragment = new MapsFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container_body, newFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();*/
////            displayView(0);
//            isPressed = true;
//        } else {
//            Log.d("CDA", "onBackPressed Called");
//            showDialogBackpress();
//        }
//    }
//
//    @Override
//    public void onDrawerItemSelected(View view, int position) {
//        displayView(position);
//    }
//
//    private void displayView(int position) {
//        Fragment fragment = null;
//        String title = getString(R.string.app_name);
//        switch (position) {
//            case 0:
//                fragment = new RecentTripFragment();
//                title = getString(R.string.nav_item_History);
//                isPressed = false;
//                isSpinnerOpned = false;
//                break;
//            case 1:
//                isSpinnerOpned = true;
//
//                spinner.performClick();
////                fragment = new MapsFragment();
//                title = getString(R.string.nav_item_Status);
//                isPressed = false;
//                break;
////            case 2:
////                fragment = new NotificationFragment();
////                title = getString(R.string.nav_item_Notifications);
////                isPressed = false;
////                isSpinnerOpned=false;
////                break;
////            case 3:
////                fragment = new ContactUsFragment();
////                title = getString(R.string.nav_item_Contact_us);
////                isPressed = false;
////                isSpinnerOpned=false;
////                break;
//            case 2:
//                Intent intent = new Intent(context, UpdateProfile.class);
//                startActivity(intent);
////                fragment = new SettingsFragment();
//                title = getString(R.string.nav_item_Settings);
//                isPressed = false;
//                isSpinnerOpned = false;
//                break;
//            case 3:
//                fragment = new MapsFragment();
//                title = getString(R.string.nav_item_About);
//                isPressed = false;
//                isSpinnerOpned = false;
//                break;
//            case 4:
//                showDialogLogout();
//                title = getString(R.string.nav_item_Help);
//                isPressed = false;
//                isSpinnerOpned = false;
//                break;
//            case 5:
//                showDialogLogout();
//                title = getString(R.string.nav_item_Logout);
//                isPressed = false;
//                isSpinnerOpned = false;
//                break;
//            default:
//                break;
//        }
//
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container_body, fragment);
//            fragmentTransaction.commit();
//
//            // set the toolbar title
////            getSupportActionBar().setTitle(title);
//        }
//    }
//
//    //We are calling this method to check the permission status
//    private boolean isReadStorageAllowed() {
//        //Getting the permission status
//        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//
//        //If permission is granted returning true
//        if (result == PackageManager.PERMISSION_GRANTED)
//            return true;
//
//        //If permission is not granted returning false
//        return false;
//    }
//
//    //Requesting permission
//    private void requestStoragePermission() {
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//            //If the user has denied the permission previously your code will come to this block
//            //Here you can explain why you need this permission
//            //Explain here why you need this permission
//        }
//
//        //And finally ask for the permission
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                STORAGE_PERMISSION_CODE);
//    }
//
//    //This method will be called when the user will tap on allow or deny
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        //Checking the request code of our request
//        if (requestCode == STORAGE_PERMISSION_CODE) {
//
//            //If permission is granted
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                //Displaying a toast
//            } else {
//                //Displaying another toast if permission is not granted
//                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public void showDialogBackpress() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityDrawer.this);
//        alertDialog.setTitle(getString(R.string.app_name));
//        alertDialog.setMessage("App will Quit and you can not receive any ride request");
//        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//
//        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to invoke NO event
//
//                dialog.cancel();
//                isPressed = false;
//
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    public void showDialogLogout() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityDrawer.this);
//        alertDialog.setTitle(getString(R.string.app_name));
//        alertDialog.setMessage("Are You Sure You Want To Log out?");
//        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//
//                String RegistrationID = Utils.getPreferences("RegistrationID", context);
//
//                String id = Utils.getPreferences("id", context);
//
//                logOut(id, RegistrationID);
//
//
//            }
//        });
//
//        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to invoke NO event
//
//                dialog.cancel();
//                isPressed = false;
//
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        try {
//            status = "OFFLINE";
//            Utils.savePreferences("driver_online", "false", context);
//            Utils.savePreferences("status_driver", status, context);
//            String RegistrationID = Utils.getPreferences("RegistrationID", context);
//            String driver_id = Utils.getPreferences("id", context);
//            makeJsonObjReqForLogout(driver_id, RegistrationID, status);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //    @Override
////    protected void onDestroy() {
////        super.onDestroy();
//////        Intent alarm = new Intent(context, AlarmReceiver.class);
//////        boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
//////        if (alarmRunning == false) {
//////            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
//////            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//////            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 60000, pendingIntent);
//////        }
////
////    }
//
//
//    private void logOut(final String userID, final String regID) {
//
//        String url = CONSTANTS.baseUrl + "logout.php";
//
//        Map<String, String> postParam = new HashMap<String, String>();
//        postParam.put("type", CONSTANTS.userType);
//
//        final ProgressDialog progressDialog = ProgressDialog.show(context, "",
//                "Loading...", true);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(postParam),
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("TAG", jsonObject.toString());
//                        try {
//                            JSONObject jsonStatus = jsonObject.getJSONObject("status");
//                            if (jsonStatus.getString("code").equalsIgnoreCase("1000")) {
//
//                                Utils.savePreferences("LOGGED", null, MainActivityDrawer.this);
//
//                                progressDialog.dismiss();
//                                Intent intent = new Intent(context, Login.class);
//                                startActivity(intent);
//                                finish();
//    /*for changong status */
////
//                                /*for changong status */
//
//                         /*       Utils.savePreferences("LOGGED", null, getApplicationContext());
//                                Intent intent = new Intent(context, Login.class);
//                                startActivity(intent);
//                                finish();*/
//                        /*        intent.setAction(Intent.ACTION_MAIN);
//                                intent.addCategory(Intent.CATEGORY_HOME);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
//
////                                Toast.makeText(getApplicationContext(), "" + jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//                            } else {
//                                progressDialog.dismiss();
//                                Toast.makeText(getApplicationContext(), jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//                            }
//                        } catch (JSONException e) {
//                            progressDialog.dismiss();
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             * */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("user_id", userID);
//                headers.put("registration_id", regID);
//                return headers;
//            }
//
//        };
//        MyRequestQueue.getRequestInstance(context).addRequest(jsonObjReq);
////        Volley.newRequestQueue(MainActivityDrawer.this).add(jsonObjReq);
//    }
//
//    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {
//        @Override
//        protected Void doInBackground(String... params) {
//            try {
//
//                String picture = Utils.getPreferences("picture_user", context);
//                URL url = new URL(CONSTANTS.ImageUrl + picture);
//                image_bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            driver_profile_pic.setImageBitmap(image_bitmap);
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }
//
//    private void makeJsonObjReqForLogout(final String userID, final String regID, final String driverStatus) {
//
//        String url = CONSTANTS.baseUrl + "update_driver_status.php";
//
//        Map<String, String> postParam = new HashMap<String, String>();
//        postParam.put("status", driverStatus);
//
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(postParam),
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("TAG", jsonObject.toString());
//                        try {
//                            JSONObject jsonStatus = jsonObject.getJSONObject("status");
//                            if (jsonStatus.getString("code").equalsIgnoreCase("1000")) {
//
//                                Utils.savePreferences("driverStatus", driverStatus, context);
//                                //Utils.savePreferences("LOGGED", null, getApplicationContext());
//              /*                  Intent intent = new Intent(context, Login.class);
//                                intent.setAction(Intent.ACTION_MAIN);
//                                intent.addCategory(Intent.CATEGORY_HOME);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//                                Toast.makeText(getApplicationContext(), "" + jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//
//                                Toast.makeText(context, "" + jsonStatus.getString("message"), Toast.LENGTH_LONG).show();*/
//
//                            } else {
//
////                                Toast.makeText(context, jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             * */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("driver_id", userID);
//                headers.put("registration_id", regID);
//                return headers;
//            }
//
//        };
//        MyRequestQueue.getRequestInstance(context).addRequest(jsonObjReq);
////        Volley.newRequestQueue(context).add(jsonObjReq);
//    }
//
//    private void makeJsonObjReq(final String userID, final String regID, final String driverStatus) {
//
//        String url = CONSTANTS.baseUrl + "update_driver_status.php";
//
//        Map<String, String> postParam = new HashMap<String, String>();
//        postParam.put("status", driverStatus);
//
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(postParam),
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("TAG", jsonObject.toString());
//                        try {
//                            JSONObject jsonStatus = jsonObject.getJSONObject("status");
//                            if (jsonStatus.getString("code").equalsIgnoreCase("1000")) {
//
//                                Utils.savePreferences("driverStatus", driverStatus, context);
//
//                                Toast.makeText(context, "" + jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//                            } else {
//
//                                Toast.makeText(context, jsonStatus.getString("message"), Toast.LENGTH_LONG).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             * */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("driver_id", userID);
//                headers.put("registration_id", regID);
//                return headers;
//            }
//
//        };
//        MyRequestQueue.getRequestInstance(context).addRequest(jsonObjReq);
////        Volley.newRequestQueue(context).add(jsonObjReq);
//    }
//
//    public void powerOnCell() {
//        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
//        wakeLock.acquire();
//
//        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
//        keyguardLock.disableKeyguard();
//    }
//}
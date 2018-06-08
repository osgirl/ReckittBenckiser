package com.cpm.gskgtsupervisor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.DailyEntryScreen;
import com.cpm.dailyentry.StoreEntry;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.fragment.HelpFragment;
import com.cpm.fragment.MainFragment;
import com.cpm.message.AlertMessage;
import com.cpm.upload.CheckoutNUpload;
import com.cpm.upload.UploadDataActivity;
import com.cpm.upload.UploadOptionActivity;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GSKDatabase database;
    ArrayList<JourneyPlanGetterSetter> jcplist;
    private SharedPreferences preferences=null;
    private String date,user_name,user_type;

    TextView tv_username,tv_usertype;

    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        user_name = preferences.getString(CommonString.KEY_USERNAME, null);
        user_type = preferences.getString(CommonString.KEY_USER_TYPE, null);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main_menu2, navigationView, false);
        navigationView.addHeaderView(headerView);

      /*  Menu m = navigationView.getMenu();

        SubMenu topChannelMenu = m.addSubMenu("Top Channels");
        topChannelMenu.add("Foo");
        topChannelMenu.add("Bar");
        topChannelMenu.add("Baz");

        MenuItem mi = m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());*/

        tv_username = (TextView)  headerView.findViewById(R.id.nav_user_name);
        tv_usertype = (TextView) headerView.findViewById(R.id.nav_user_type);

        tv_username.setText(user_name);
        tv_usertype.setText(user_type);

        navigationView.setNavigationItemSelectedListener(this);

        database = new GSKDatabase(this);
        database.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daily) {
            // Handle the camera action

            Intent startDownload = 	new Intent(this,DailyEntryScreen.class);
            startActivity(startDownload);

            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        } else if (id == R.id.nav_download) {

            if(checkNetIsAvailable()){

                if (database.isCoverageDataFilled(date)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Parinaam");
                    builder.setMessage("Please Upload Previous Data First")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent startUpload = 	new Intent(MainMenuActivity.this,CheckoutNUpload.class);
                                    startActivity(startUpload);
                                    finish();

                                }
                            });
	            				/*.setNegativeButton("Cancel",
	            						new DialogInterface.OnClickListener() {
	            							public void onClick(DialogInterface dialog, int id) {

	            								Intent i = new Intent(AutoupdateActivity.this,
	            										MainActivity.class);
	            								startActivity(i);

	            								AutoupdateActivity.this.finish();

	            							}
	            						});*/
                    AlertDialog alert = builder.create();

                    alert.show();

                    // Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();

                } else {

                    Intent startDownload = 	new Intent(getApplicationContext(),CompleteDownloadActivity.class);
                    startActivity(startDownload);
                    finish();
                }

            }
            else{


                Snackbar.make(frameLayout, "No Network Available", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                //Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_upload) {

            if (checkNetIsAvailable()) {

                jcplist=database.getJCPData(date);

                if (jcplist.size() == 0) {

                    Snackbar.make(frameLayout, "Please Download Data First", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                  //  Toast.makeText(getBaseContext(), "Please Download Data First", Toast.LENGTH_LONG).show();
                } else {

                    if(preferences.getString(CommonString.KEY_STOREVISITED_STATUS, "").equals("Yes")){

                        Snackbar.make(frameLayout, "First checkout of store", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                        //Toast.makeText(getApplicationContext(), "First checkout of store", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
                        //ArrayList<GeotaggingBeans> gdata = new ArrayList<GeotaggingBeans>();

                        cdata = database.getCoverageData(date);
                        //gdata = database.getGeotaggingData("Y");

                        if (cdata.size() == 0) {

                            Snackbar.make(frameLayout, AlertMessage.MESSAGE_NO_DATA, Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();

                           // Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();

                        } else {

                          /*  Menu m = navigationView.getMenu();
                            MenuItem mitem = m.getItem(3);

                            MenuItem subMenu = mitem.getSubMenu().getItem(0);
                            subMenu.setVisible(true);

                            MenuItem subMenu2 = mitem.getSubMenu().getItem(1);
                            subMenu.setVisible(true);*/

                           Intent i = new Intent(getBaseContext(),
                                    UploadOptionActivity.class);
                            i.putExtra("UploadAll", false);
                            startActivity(i);

                            finish();

                        }

                    }



					/*
					intent = new Intent(getBaseContext(),
							UploadOptionActivity.class);
					startActivity(intent);

					MainMenuActivity.this.finish();*/

                }

            } else {

                Snackbar.make(frameLayout, "No Network Available", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

              //  Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_exit) {

            Intent startDownload = 	new Intent(this,LoginActivity.class);
            startActivity(startDownload);
            finish();

        }else if (id == R.id.nav_help) {

            FragmentManager fragmentManager = getSupportFragmentManager();


            HelpFragment cartfrag = new HelpFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, cartfrag)
                    .commit();

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkNetIsAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager fragmentManager = getSupportFragmentManager();


        MainFragment cartfrag = new MainFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, cartfrag)
                .commit();
    }


}

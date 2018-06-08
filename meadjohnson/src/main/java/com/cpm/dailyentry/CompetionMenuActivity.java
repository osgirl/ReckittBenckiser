package com.cpm.dailyentry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.GetterSetter.NavMenuItemGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompetionMenuActivity extends AppCompatActivity {

    ValueAdapter adapter;

    RecyclerView recyclerView;

    GSKDatabase db;

    private SharedPreferences preferences;
    String store_cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competion_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

        recyclerView=(RecyclerView) findViewById(R.id.drawer_layout_recycle);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    public List<NavMenuItemGetterSetter> getdata(){

        List<NavMenuItemGetterSetter> data=new ArrayList<>();

        int facing_img,poi_img,promotion_img;

        if(db.getFacingCompetitorData(store_cd).size()>0){
            facing_img = R.drawable.c_facing_done;
        }
        else{
            facing_img = R.drawable.c_facing;
        }

       /* if(db.getCompetitionPOIData(store_cd).size()>0){
            poi_img = R.drawable.c_add_display_done;
        }
        else{
            poi_img = R.drawable.c_add_display;
        }*/

        if(db.getCompetitionPromotionData(store_cd).size()>0){
            promotion_img = R.drawable.c_promotion_done;
        }
        else{
            promotion_img = R.drawable.c_promotion;
        }

       // int img[]={facing_img,poi_img,promotion_img};

        int img[]={facing_img,promotion_img};

        //String text[]={"My Cart", "Profile", "About","Logout"};

        for(int i=0;i<img.length;i++){

            NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
            recData.setIconImg(img[i]);
            //recData.setIconName(text[i]);

            data.add(recData);
        }

        return  data;
    }

    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder>{

        private LayoutInflater inflator;

        List<NavMenuItemGetterSetter> data= Collections.emptyList();

        public ValueAdapter(Context context, List<NavMenuItemGetterSetter> data){

            inflator = LayoutInflater.from(context);
            this.data=data;

        }

        @Override
        public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            View view=inflator.inflate(R.layout.custom_row,parent,false);

            MyViewHolder holder=new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final ValueAdapter.MyViewHolder viewHolder, final int position) {

            final NavMenuItemGetterSetter current=data.get(position);

            //viewHolder.txt.setText(current.txt);
            viewHolder.icon.setImageResource(current.getIconImg());
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(position==0){

                        Intent startPerformance = 	new Intent(getApplicationContext(),CompetitionFaceupActivity.class);
                        startActivity(startPerformance);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }
                   /* if(position==1){

                        Intent startPerformance = 	new Intent(getApplicationContext(),CompetitionAditionalPOIActivity.class);
                        startActivity(startPerformance);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                    }*/
                    if(position==1){

                        Intent startPerformance = 	new Intent(getApplicationContext(),CompetitionPromotionsActivity.class);
                        startActivity(startPerformance);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder{

            //TextView txt;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                //txt=(TextView) itemView.findViewById(R.id.list_txt);
                icon=(ImageView) itemView.findViewById(R.id.list_icon);
            }
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
		/*Intent i = new Intent(this, DailyEntryScreen.class);
		startActivity(i);*/

        finish();


        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter=new ValueAdapter(getApplicationContext(),getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }
}

package com.cpm.dailyentry;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetionCompanyGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;

import java.util.ArrayList;
import java.util.Calendar;

public class CompetitionFaceupActivity extends AppCompatActivity implements View.OnClickListener{

    boolean checkflag=true;

    static int currentapiVersion = 1;

    Button btnsave;
    ListView lv;

    //Spinner spin;
    private ArrayAdapter<CharSequence> dfAdapter;
    ArrayList<FacingCompetionCompanyGetterSetter> facingcomplist=new ArrayList<FacingCompetionCompanyGetterSetter>();
    //ArrayList<FacingCompetionCompanyGetterSetter> companylist=new ArrayList<>();
    //ArrayList<String> dflist=new ArrayList<String>();
    ArrayList<String> statuslist=new ArrayList<String>();

    //EditText etremarkone;
    //LinearLayout lv_layout;



    GSKDatabase db;

    int listsize=0;

    private SharedPreferences preferences;
    String store_cd;

    String visit_date,username,intime;

    CustomKeyboardView mKeyboardView;
    Keyboard mKeyboard;

    MyAdaptor myadapter;

    boolean ischangedflag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_faceup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentapiVersion = android.os.Build.VERSION.SDK_INT;

        lv=(ListView) findViewById(R.id.lvfacing);
        btnsave=(Button) findViewById(R.id.save_btn);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mKeyboard = new Keyboard(this, R.xml.keyboard);

        mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView
                .setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
                        this));

        db=new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username= preferences.getString(CommonString.KEY_USERNAME, null);
        intime=getCurrentTime();

        myadapter=new MyAdaptor();

        setDataToListView();

        btnsave.setOnClickListener(this);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                lv.invalidateViews();
            }

        });


    }

    public void setDataToListView(){
        try{
            facingcomplist = db.getFacingCompetitorData(store_cd);

            if(facingcomplist.size()<=0){
                //facingcomplist = db.getCategoryData();
                facingcomplist = db.getCompetitionCompanyData();
            }
            else{
                btnsave.setText("Update");
            }
        }
        catch(Exception e){
            Log.d("Exception when fetching",
                    e.toString());
        }

        if(facingcomplist.size()>0){
            listsize=facingcomplist.size();
			/*etremarkone.setText(facingcomplist.get(0).getRemark());
			spin.setSelection(statuslist.indexOf(facingcomplist.get(0).getStatus()));*/
            lv.setAdapter(myadapter);
        }

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id==R.id.save_btn){

            lv.clearFocus();

            if(validate(facingcomplist)){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to save")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        db.open();

                                        dialog.cancel();

                                        getMid();

                                        db.insertFscingCompetitorData(facingcomplist,store_cd);
                                        Toast.makeText(getApplicationContext(),
                                                "Data has been saved", Toast.LENGTH_SHORT).show();

						/*Intent DailyEntryMenu = new Intent(
								FacingCompetitor.this,
								StoreEntry.class);
						startActivity(DailyEntryMenu);*/

                                        finish();

                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();

                alert.show();

            }
            else{
                myadapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Please fill all data", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private class MyAdaptor extends BaseAdapter {

        @Override
        public int getCount() {

            return listsize;
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();



                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.faceup_item, null);

                holder.cardView=(CardView) convertView.findViewById(R.id.card_view);

                holder.dfavail= (TextView) convertView
                        .findViewById(R.id.tvdfav);
                holder.etfacing=(EditText) convertView.findViewById(R.id.etfaceup);
                //holder.etstoredf=(EditText) convertView.findViewById(R.id.etstoredf);


                if (currentapiVersion >= 11) {
                    holder.etfacing.setTextIsSelectable(true);
                   // holder.etstoredf.setTextIsSelectable(true);

                    holder.etfacing.setRawInputType(InputType.TYPE_CLASS_TEXT);
                   // holder.etstoredf.setRawInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    holder.etfacing.setInputType(0);
                   // holder.etstoredf.setInputType(0);

                }


                holder.etfacing
                        .setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            public void onFocusChange(View v, boolean hasFocus) {

                                if(hasFocus){

                                    showKeyboardWithAnimation();
                                }

                                if (!hasFocus) {

                                    hide();
                                    final int position = v.getId();
                                    final EditText Caption = (EditText) v;
                                    String value1 = Caption.getText().toString();

                                    if (value1.equals("")) {

                                        facingcomplist.get(position).setFacing("");

                                    } else {

                                        ischangedflag=true;

                                        facingcomplist.get(position).setFacing(value1);

                                    }

                                }
                            }
                        });

             /*   holder.etstoredf
                        .setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            public void onFocusChange(View v, boolean hasFocus) {

                                if(hasFocus){
                                    showKeyboardWithAnimation();
                                }

                                if (!hasFocus) {

                                    hide();
                                    final int position = v.getId();
                                    final EditText Caption = (EditText) v;
                                    String value1 = Caption.getText().toString();

                                    if (value1.equals("")) {

                                        facingcomplist.get(position).setStoredf("");

                                    } else {

                                        ischangedflag=true;

                                        facingcomplist.get(position).setStoredf(value1);

                                    }

                                }
                            }
                        });*/

                convertView.setTag(holder);


            } else {
                holder = (ViewHolder) convertView.getTag();

            }


            holder.dfavail.setId(position);
            holder.etfacing.setId(position);
          //  holder.etstoredf.setId(position);

            holder.dfavail.setText(facingcomplist.get(position).getCompany());
           // holder.etstoredf.setText(facingcomplist.get(position).getStoredf());
            holder.etfacing.setText(facingcomplist.get(position).getFacing());

            if(!checkflag){

                boolean tempflag=false;

             /*   if(holder.etstoredf.getText().toString().equals("")){
                    //holder.dfavail.setTextColor(Color.RED);
                    //convertView.setBackgroundColor(getResources().getColor(R.color.red));
                    holder.etstoredf.setHintTextColor(getResources().getColor(R.color.red));
                    holder.etstoredf.setHint("Empty");
                    tempflag=true;
                }
*/

                if(holder.etfacing.getText().toString().equals("")){
                    //holder.dfavail.setTextColor(Color.RED);

                    holder.etfacing.setHintTextColor(getResources().getColor(R.color.red));
                    holder.etfacing.setHint("Empty");
                    tempflag=true;
                }


                if(tempflag){
                    //holder.dfavail.setTextColor(Color.RED);
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
                }
                else{
                    //holder.dfavail.setTextColor(Color.BLACK);
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
                }

            }



            return convertView;
        }

    }


    private class ViewHolder {
        TextView dfavail;
        EditText etfacing;
        //EditText etstoredf;
        CardView cardView;
    }


    /***
     * Display the screen keyboard with an animated slide from bottom
     */
    private void showKeyboardWithAnimation() {
        if (mKeyboardView.getVisibility() == View.GONE) {
            Animation animation = AnimationUtils
                    .loadAnimation(CompetitionFaceupActivity.this,
                            R.anim.slide_in_bottom);
            mKeyboardView.showWithAnimation(animation);
        }
        else if(mKeyboardView.getVisibility() == View.INVISIBLE){
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

        return intime;

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        if (mKeyboardView.getVisibility() == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
			/*mKeyboardView.requestFocusFromTouch();*/
        } else {

            if(ischangedflag){

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CompetitionFaceupActivity.this);
                builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {

                                        finish();

                                        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

            }
            else{
                finish();

                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }


        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mKeyboardView.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setVisibility(View.INVISIBLE);
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

        if(id==android.R.id.home){

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }

    public void hide() {
        mKeyboardView.setVisibility(View.INVISIBLE);
	/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

    }

    public long checkMid() {
        return db.CheckMid(visit_date, store_cd);
    }

    public long getMid() {

        long mid = 0;

        mid = checkMid();

        if (mid == 0) {
            CoverageBean cdata = new CoverageBean();
            cdata.setStoreId(store_cd);
            cdata.setVisitDate(visit_date);
            cdata.setUserId(username);
            cdata.setInTime(intime);
            cdata.setOutTime(getCurrentTime());
            cdata.setReason("");
            cdata.setReasonid("0");
            cdata.setLatitude("0.0");
            cdata.setLongitude("0.0");
            mid = db.InsertCoverageData(cdata);

        }

        return mid;
    }

    public boolean validate(ArrayList<FacingCompetionCompanyGetterSetter> complist){

        for (int j = 0; j < complist.size(); j++) {
			/*String aspermccain = listDataChild2.get(listDataHeader2.get(i)).get(j).getAs_per_meccain();*/
            String facing= complist.get(j).getFacing();
           // String storefacing= complist.get(j).getStoredf();

            if (facing.equalsIgnoreCase("") ) {

                checkflag=false;

                break;

            } else{
                checkflag=true;
            }
        }

        return checkflag;
    }

}

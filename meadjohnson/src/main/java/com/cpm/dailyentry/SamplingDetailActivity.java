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
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.GetterSetter.SampleDataGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.xmlGetterSetter.POIGetterSetter;

import java.util.ArrayList;

public class SamplingDetailActivity extends AppCompatActivity implements View.OnFocusChangeListener{

    Button btnsave;
    EditText etcustomer,etphone_no,etqsample,etqsale;

    GSKDatabase db;

    private SharedPreferences preferences;
    String store_cd;

    static int currentapiVersion = 1;

    CustomKeyboardView mKeyboardView;

    Keyboard mKeyboard;

    MyAdaptor myAdaptor;

    ArrayList<SampleDataGetterSetter> data_list = new ArrayList<>();

    String customer_str,phone_str,sample_str,sale_str;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampling_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etcustomer = (EditText) findViewById(R.id.et_customer_name);
        etphone_no = (EditText) findViewById(R.id.et_phone_no);
        etqsample = (EditText) findViewById(R.id.et_quantity_sample);
        etqsale = (EditText) findViewById(R.id.et_quantity_sale);

        lv = (ListView) findViewById(R.id.lv_sampling_data);

        btnsave = (Button) findViewById(R.id.btnsave);

        currentapiVersion = android.os.Build.VERSION.SDK_INT;

        mKeyboard = new Keyboard(this, R.xml.keyboard);

        mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView
                .setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
                        this));

        if (currentapiVersion >= 11) {

            etphone_no.setTextIsSelectable(true);
            etqsample.setTextIsSelectable(true);
            etqsale.setTextIsSelectable(true);

            etphone_no.setRawInputType(InputType.TYPE_CLASS_TEXT);
            etqsample.setRawInputType(InputType.TYPE_CLASS_TEXT);
            etqsale.setRawInputType(InputType.TYPE_CLASS_TEXT);

        } else {
            etphone_no.setInputType(0);
            etqsample.setInputType(0);
            etqsale.setInputType(0);

        }

        etphone_no.setOnFocusChangeListener(this);
        etqsample.setOnFocusChangeListener(this);
        etqsale.setOnFocusChangeListener(this);

        //lv=(ListView) findViewById(R.id.lvcalls);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

        db=new GSKDatabase(getApplicationContext());

        db.open();

        setSamplingData();
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                try{

                    customer_str = etcustomer.getText().toString();
                    phone_str = etphone_no.getText().toString();
                    sample_str = etqsample.getText().toString();
                    sale_str = etqsale.getText().toString();

                    if(customer_str.equals("") || phone_str.equals("") || sample_str.equals("") || sale_str.equals("")){
                      //  Toast.makeText(getApplicationContext(),CommonString.PLS_FILL_DATA , Toast.LENGTH_SHORT).show();
                        Snackbar.make(lv,CommonString.PLS_FILL_DATA,Snackbar.LENGTH_SHORT).show();
                    }
                    else{
					/*	et_total_calls.setText("");
						et_productive_calls.setText("");*/

                        if(isValidPhoneNumber(phone_str)){

                            AlertDialog.Builder builder = new AlertDialog.Builder(SamplingDetailActivity.this);
                            builder.setMessage("Are you sure you want to save")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,
                                                                    int id) {

                                                    db.insertSampleData(store_cd, customer_str, phone_str, sample_str, sale_str);
                                                    Toast.makeText(getApplicationContext(),
                                                            "Data has been saved", Toast.LENGTH_SHORT).show();

                                                    etcustomer.setText("");
                                                    etphone_no.setText("");
                                                    etqsample.setText("");
                                                    etqsale.setText("");

                                                    hide();

                                                    data_list.clear();
                                                    setSamplingData();
                                                    lv.invalidate();
                                                }
                                            })
                                    .setNegativeButton("No",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,
                                                                    int id) {

                                                    if(mKeyboardView.getVisibility() == View.INVISIBLE){
                                                        mKeyboardView.setVisibility(View.VISIBLE);
                                                    }

                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alert = builder.create();

                            alert.show();



                        }
                        else {
                            Snackbar.make(lv,"Please enter valid mobile number",Snackbar.LENGTH_SHORT).show();
                        }


						/*listcalls=db.getCallsData(store_cd);

						if(listcalls.size()>0){
							listsize=listcalls.size();

							lv.setAdapter(myAdaptor);
						}*/

                    }

                }
                catch(Exception e){
                    System.out.println(e.toString());
                }



            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        int id = view.getId();

        if(id==R.id.et_phone_no){
            if(hasFocus){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                showKeyboardWithAnimation();
            }
            else{
                hide();
            }
        }

        if(id==R.id.et_quantity_sample){
            if(hasFocus){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                showKeyboardWithAnimation();
            }
            else{
                hide();
            }
        }

        if(id==R.id.et_quantity_sale){
            if(hasFocus){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                showKeyboardWithAnimation();
            }
            else{
                hide();
            }
        }

    }

    /***
     * Display the screen keyboard with an animated slide from bottom
     */
    private void showKeyboardWithAnimation() {
        if (mKeyboardView.getVisibility() == View.GONE) {
            Animation animation = AnimationUtils
                    .loadAnimation(SamplingDetailActivity.this,
                            R.anim.slide_in_bottom);
            mKeyboardView.showWithAnimation(animation);
        }
        else if(mKeyboardView.getVisibility() == View.INVISIBLE){
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hide() {
        mKeyboardView.setVisibility(View.INVISIBLE);
		/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

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

    private class MyAdaptor extends ArrayAdapter<SampleDataGetterSetter> {

        public MyAdaptor(Context context, ArrayList<SampleDataGetterSetter> poidata) {
            super(context,0, poidata);
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getCount() {

            return data_list.size();
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
                convertView = inflater.inflate(R.layout.sample_data_item, null);


                holder.tv_customer= (TextView) convertView
                        .findViewById(R.id.tv_customer);
                holder.tv_phone=(TextView) convertView.findViewById(R.id.tv_phone);
                holder.tv_sample=(TextView) convertView.findViewById(R.id.tv_q_sample);
                holder.tv_sale=(TextView) convertView.findViewById(R.id.tv_q_sale);

              /*  holder.imgdel=(ImageView) convertView.findViewById(R.id.imgdelete);

                holder.imgdel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        int key_id=Integer.parseInt(listcalls.get(position).getKey_id());
                        db.deleteStockRow(key_id, store_cd);

                        listcalls.remove(position);
                        notifyDataSetChanged();
                    }
                });
*/
                convertView.setTag(holder);


            } else {
                holder = (ViewHolder) convertView.getTag();

            }


            holder.tv_customer.setId(position);
            holder.tv_phone.setId(position);
            holder.tv_sample.setId(position);
            holder.tv_sale.setId(position);

            holder.tv_customer.setText(data_list.get(position).getCustomer_name());
            holder.tv_phone.setText(data_list.get(position).getPhone_no());
            holder.tv_sample.setText(data_list.get(position).getQuality_sample());
            holder.tv_sale.setText(data_list.get(position).getQuality_sale());

            return convertView;
        }

    }


    private class ViewHolder {
        TextView tv_customer,tv_phone,tv_sample,tv_sale;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        if(mKeyboardView.getVisibility() == View.VISIBLE){
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
        else{
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }

    }

    public void setSamplingData(){

        data_list = db.getSampleData(store_cd);

        if(data_list.size()>0){

           // heading.setVisibility(View.VISIBLE);

            lv.setAdapter(new MyAdaptor(getApplicationContext(), data_list));
            lv.setVisibility(View.VISIBLE);
        }

    }

    /**
     * Regex to validate the mobile number
     * mobile number should be of 10 digits length
     *
     * @param mobile
     * @return
     */
    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

}

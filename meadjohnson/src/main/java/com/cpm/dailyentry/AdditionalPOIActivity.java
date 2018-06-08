package com.cpm.dailyentry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.POIGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class AdditionalPOIActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner_category,spinner_asset,spinner_brand;
    EditText etremark;
    Button btnsave;
    ImageView camimg;
    ListView lv;
    LinearLayout heading;

    String _pathforcheck,_path,str;

    GSKDatabase db;
    private ArrayAdapter<CharSequence> categoryAdapter,assetAdapter,brandAdapter;
    ArrayList<FacingCompetitorGetterSetter> categorylist=new ArrayList<FacingCompetitorGetterSetter>();
    ArrayList<StockNewGetterSetter> brandlist=new ArrayList<StockNewGetterSetter>();
    ArrayList<AssetInsertdataGetterSetter> assetlist;
    ArrayList<POIGetterSetter> poilist=new ArrayList<POIGetterSetter>();
    //ArrayList<POIGetterSetter> poilist=new ArrayList<POIGetterSetter>();

    String category_cd, asset_cd, category, asset, brand_cd, brand;

    POIGetterSetter poiGetterSetter = new POIGetterSetter();

    private SharedPreferences preferences;
    String store_cd,visit_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_poi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_category = (Spinner) findViewById(R.id.spincategory);
        spinner_asset = (Spinner) findViewById(R.id.spinasset);
        spinner_brand = (Spinner) findViewById(R.id.spinbrand);
        etremark = (EditText) findViewById(R.id.etfaceup);
        btnsave = (Button) findViewById(R.id.btn_poi_save);
        lv = (ListView) findViewById(R.id.lvaddnpoi);
        heading = (LinearLayout) findViewById(R.id.headerpoi);
        camimg = (ImageView) findViewById(R.id.imgcampoi);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);

        str = Environment.getExternalStorageDirectory() + "/Meadjohnson_Images/";

        db=new GSKDatabase(getApplicationContext());
        db.open();

        setPOIData();

        categoryAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);
        assetAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);
        brandAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);

        categoryAdapter.add("Select Category");

        categorylist = db.getCategoryData();


        for (int i=0;i<categorylist.size();i++){

            categoryAdapter.add(categorylist.get(i).getCategory());

        }

        spinner_category.setAdapter(categoryAdapter);

        assetAdapter.add("Select Asset");

        assetlist=db.getAllAssetData();

        for (int i=0;i<assetlist.size();i++){

            assetAdapter.add(assetlist.get(i).getAsset());

        }

        spinner_asset.setAdapter(assetAdapter);

        brandAdapter.add("Select Brand");
        spinner_brand.setAdapter(brandAdapter);

        spinner_category.setOnItemSelectedListener(this);
        // spinner_category.setBackgroundResource(R.drawable.gradient_bg_hover);

        spinner_asset.setOnItemSelectedListener(this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( (category_cd == null) || category_cd.equals("")) {

                    Snackbar.make(v, "Please Select a Category", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if ((brand_cd == null) || brand_cd.equals("")) {
                    Snackbar.make(v, "Please Select an Brand", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if ((asset_cd == null) || asset_cd.equals("")) {
                    Snackbar.make(v, "Please Select an Asset", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if(_pathforcheck==null || _pathforcheck.equals("")){
                    Snackbar.make(v, "Please Click Image", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    poiGetterSetter.setRemark(etremark.getText().toString().replaceAll("[&^<>{}'$]", ""));
                    poiGetterSetter.setCategory_cd(category_cd);
                    poiGetterSetter.setCategory(category);
                    poiGetterSetter.setAsset_cd(asset_cd);
                    poiGetterSetter.setAsset(asset);
                    poiGetterSetter.setBrand_cd(brand_cd);
                    poiGetterSetter.setBrand(brand);
                    poiGetterSetter.setImage(_pathforcheck);

                    db.insertPOIData(poiGetterSetter, store_cd);

                    spinner_category.setSelection(0);
                    spinner_asset.setSelection(0);
                    spinner_brand.setSelection(0);
                    _pathforcheck="";
                    etremark.setText("");

                    poilist.clear();
                    setPOIData();

                    brandAdapter.clear();
                    brandAdapter.add("Select Brand");
                    camimg.setBackgroundResource(R.drawable.camera);

                    lv.invalidate();

                }


            }
        });

        camimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( (category_cd == null) || category_cd.equals("")) {

                    Snackbar.make(v, "Please Select a Category First", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if ((brand_cd == null) || brand_cd.equals("")) {
                    Snackbar.make(v, "Please Select an Brand First", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if ((asset_cd == null) || asset_cd.equals("")) {
                    Snackbar.make(v, "Please Select an Asset First", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else{
                    _pathforcheck = store_cd + brand_cd + "Poi"
                            + "Image"+ visit_date.replace("/","") +getCurrentTime().replace(":","")+".jpg";

                    _path = Environment.getExternalStorageDirectory() +  "/Meadjohnson_Images/"
                            + _pathforcheck;

                    startCameraActivity();
                }

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.spincategory:

                if (position != 0) {

                    category_cd = categorylist.get(position-1).getCategory_cd();
                    category = categorylist.get(position-1).getCategory();

                    brandlist = db.getBrandData(category_cd);
                    brandAdapter.clear();

                    brandAdapter.add("Select Brand");

                    for (int i=0;i<brandlist.size();i++){

                        brandAdapter.add(brandlist.get(i).getBrand());

                    }

                    spinner_brand.setAdapter(brandAdapter);
                    spinner_brand.setOnItemSelectedListener(this);

                }
                else{
                    category_cd="";
                    category = "";
                }

                break;

            case R.id.spinasset:

                if (position != 0) {

                    asset_cd = assetlist.get(position-1).getAsset_cd();
                    asset = assetlist.get(position-1).getAsset();
                }
                else{
                    asset_cd="";
                    asset="";
                }

                break;

            case R.id.spinbrand:

                if (position != 0) {

                    brand_cd = brandlist.get(position-1).getBrand_cd();
                    brand = brandlist.get(position-1).getBrand();
                }
                else{
                    brand_cd="";
                    brand="";
                }

                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class POIAdaptor extends ArrayAdapter<POIGetterSetter>{



        public POIAdaptor(Context context, ArrayList<POIGetterSetter> poidata) {
            super(context,0, poidata);
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getCount() {
            return poilist.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewStockHolder holder = null;

            if (convertView == null) {
                holder = new ViewStockHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.poi_item, null);

                holder.tvcategory = (TextView) convertView
                        .findViewById(R.id.tvcategory);
                holder.tvasset= (TextView) convertView
                        .findViewById(R.id.tvasset);
                holder.tvremark= (TextView) convertView
                        .findViewById(R.id.tvremark);
                holder.tvbrand= (TextView) convertView
                        .findViewById(R.id.tvbrand);
                holder.delete = (Button) convertView
                        .findViewById(R.id.btndelete);
                holder.layout = (RelativeLayout) convertView
                        .findViewById(R.id.poihead);

                holder.delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        RelativeLayout layout=(RelativeLayout) v.getParent();
                        int id = layout.getId();

                        // int id = lv.getId();

                        db.deletePOIRow(id,store_cd);
                        poilist.remove(position);
                        notifyDataSetChanged();
                        Snackbar.make(v, "Data Deleted", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

						/*LinearLayout layout=(LinearLayout) v.getParent();
						TextView tv=(TextView)layout.findViewById(R.id.tvkeyid);*/

                        /*int id = ((LinearLayout)v.getParent()).getId();

                        db.deleteStockRow(id, store_id);

                        skuStocklist.get(position).getId();

                        //	holder1.sales.setText("====");
                        skuStocklist.remove(position);

                        notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Data Deleted --"+idval, Toast.LENGTH_SHORT).show();*/
                    }
                });


                convertView.setTag(holder);

            } else {
                holder = (ViewStockHolder) convertView.getTag();
            }



			/*holder.id.setId(position);
			holder.stock.setId(position);
			holder.sku.setId(position);
			holder.sales.setId(position);*/

            holder.layout.setId(Integer.parseInt(poilist.get(position).getId()));
            // holder.id.setText(poilist.get(position).getId());
            holder.tvcategory.setText(poilist.get(position).getCategory());
            holder.tvasset.setText(poilist.get(position).getAsset());
            holder.tvremark.setText(poilist.get(position).getRemark());
            holder.tvbrand.setText(poilist.get(position).getBrand());

            return convertView;
        }


        public class ViewStockHolder {
            TextView tvcategory;
            TextView tvasset;
            TextView tvremark;
            TextView tvbrand;
            Button delete;
            RelativeLayout layout;
        }

    }

    public void setPOIData(){

        poilist = db.getPOIData(store_cd);


        if(poilist.size()>0){

            heading.setVisibility(View.VISIBLE);

            lv.setAdapter(new POIAdaptor(getApplicationContext(), poilist));
            lv.setVisibility(View.VISIBLE);
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

    protected void startCameraActivity() {

        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);

            Intent intent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(intent, 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                _pathforcheck="";
                break;

            case -1:

                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(str + _pathforcheck).exists()) {

                        camimg.setBackgroundResource(R.drawable.camera_done);

                    }
                }

                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

        return intime;

    }

}

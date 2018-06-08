package com.cpm.dailyentry;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.POIGetterSetter;

import java.util.ArrayList;

public class CompetitionAditionalPOIActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner_category,spinner_asset,spinner_company;
    EditText etremark;
    Button btnsave;
    ListView lv;
    LinearLayout heading;

    GSKDatabase db;
    private ArrayAdapter<CharSequence> categoryAdapter,assetAdapter,companyAdapter;
    ArrayList<FacingCompetitorGetterSetter> categorylist=new ArrayList<FacingCompetitorGetterSetter>();
    ArrayList<AssetInsertdataGetterSetter> assetlist;
    ArrayList<POIGetterSetter> poilist=new ArrayList<POIGetterSetter>();
    ArrayList<CompanyGetterSetter> companylist=new ArrayList<>();
    //ArrayList<POIGetterSetter> poilist=new ArrayList<POIGetterSetter>();

    String category_cd,asset_cd,category,asset,company_cd,company;

    POIGetterSetter poiGetterSetter = new POIGetterSetter();

    private SharedPreferences preferences;
    String store_cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_aditional_poi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_category = (Spinner) findViewById(R.id.spincategory);
        spinner_asset = (Spinner) findViewById(R.id.spinasset);
        spinner_company = (Spinner) findViewById(R.id.spincompetitor);
        etremark = (EditText) findViewById(R.id.etfaceup);
        btnsave = (Button) findViewById(R.id.btn_poi_save);
        lv = (ListView) findViewById(R.id.lvaddnpoi);
        heading = (LinearLayout) findViewById(R.id.headerpoi);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

        db=new GSKDatabase(getApplicationContext());
        db.open();

        setCompetitorPOIData();

        categoryAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);
        assetAdapter = new ArrayAdapter<CharSequence>(this,
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

        spinner_category.setOnItemSelectedListener(this);
       // spinner_category.setBackgroundResource(R.drawable.gradient_bg_hover);

        spinner_asset.setOnItemSelectedListener(this);

        companyAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);

        companyAdapter.add("Select Competitor");

        companylist = db.getCompanyData();

        for (int i=0;i<companylist.size();i++){

            companyAdapter.add(companylist.get(i).getCompany().get(0));

        }

        spinner_company.setOnItemSelectedListener(this);
        spinner_company.setAdapter(companyAdapter);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category_cd.equals("") || (category_cd == null)) {

                    Snackbar.make(v, "Please Select a Category", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (asset_cd.equals("") || (asset_cd == null)) {

                        Snackbar.make(v, "Please Select an Asset", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else if (company_cd.equals("") || (company_cd == null)) {

                    Snackbar.make(v, "Please Select an Competitor", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else{
                        poiGetterSetter.setRemark(etremark.getText().toString().replaceAll("[&^<>{}'$]", ""));
                        poiGetterSetter.setCategory_cd(category_cd);
                        poiGetterSetter.setCategory(category);
                        poiGetterSetter.setAsset_cd(asset_cd);
                        poiGetterSetter.setAsset(asset);
                        poiGetterSetter.setCompany_cd(company_cd);
                        poiGetterSetter.setCompany(company);

                        db.insertCompetitionPOIData(store_cd, poiGetterSetter);

                        spinner_category.setSelection(0);
                        spinner_asset.setSelection(0);
                        spinner_company.setSelection(0);
                        etremark.setText("");

                        poilist.clear();
                        setCompetitorPOIData();
                        lv.invalidate();

                    }
            }
        });


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){

            case R.id.spincategory:

                if (position != 0) {

                    category_cd = categorylist.get(position-1).getCategory_cd();
                    category = categorylist.get(position-1).getCategory();
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


            case R.id.spincompetitor:

                if (position != 0) {

                    company_cd = companylist.get(position-1).getCompany_cd().get(0);
                    company = companylist.get(position-1).getCompany().get(0);
                }
                else{
                    company_cd="";
                    company = "";
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
                convertView = inflater.inflate(R.layout.poi_list_item, null);

                holder.tvcategory = (TextView) convertView
                        .findViewById(R.id.tvcategory);
                holder.tvasset= (TextView) convertView
                        .findViewById(R.id.tvasset);
                holder.tvcompetitor= (TextView) convertView
                        .findViewById(R.id.tvcompany);
                holder.tvremark= (TextView) convertView
                        .findViewById(R.id.tvremark);
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

                        db.deleteCompetitionPOIRow(id);
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
            holder.tvcompetitor.setText(poilist.get(position).getCompany());
            holder.tvremark.setText(poilist.get(position).getRemark());

            return convertView;
        }


        public class ViewStockHolder {
            TextView tvcategory;
            TextView tvasset;
            TextView tvcompetitor;
            TextView tvremark;
            Button delete;
            RelativeLayout layout;
        }

    }

    public void setCompetitorPOIData(){

        poilist = db.getCompetitionPOIData(store_cd);


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


}

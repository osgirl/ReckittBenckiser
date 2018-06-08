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

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.CompetitionPromotionGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.POIGetterSetter;

import java.util.ArrayList;

public class CompetitionPromotionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner_category,spinner_company;
    EditText etremark,etpromotion, etsku;
    Button btnsave;
    ListView lv;
    LinearLayout heading;

    GSKDatabase db;
    String category_cd,category,company_cd,company;

    private SharedPreferences preferences;
    String store_cd;


    private ArrayAdapter<CharSequence> categoryAdapter,companyAdapter;
    ArrayList<FacingCompetitorGetterSetter> categorylist=new ArrayList<FacingCompetitorGetterSetter>();
    ArrayList<CompanyGetterSetter> companylist=new ArrayList<>();
    CompetitionPromotionGetterSetter promotionGetterSetter=new CompetitionPromotionGetterSetter();
    ArrayList<CompetitionPromotionGetterSetter> poilist = new ArrayList<CompetitionPromotionGetterSetter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_promotions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_category = (Spinner) findViewById(R.id.spincategory);
        spinner_company = (Spinner) findViewById(R.id.spincompany);
        etremark = (EditText) findViewById(R.id.etfaceup);
        etpromotion = (EditText) findViewById(R.id.etpromotion);
        etsku = (EditText) findViewById(R.id.etsku);
        btnsave = (Button) findViewById(R.id.btn_poi_save);
        lv = (ListView) findViewById(R.id.lvaddnpoi);
        heading = (LinearLayout) findViewById(R.id.headerpoi);

        db=new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);


        categoryAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);

        categoryAdapter.add("Select Category");

        companyAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.spinner_custom_item);

        companyAdapter.add("Select Competitor");

        categorylist = db.getCategoryData();

        for (int i=0;i<categorylist.size();i++){

            categoryAdapter.add(categorylist.get(i).getCategory());

        }

        companylist = db.getCompanyData();

        for (int i=0;i<companylist.size();i++){

            companyAdapter.add(companylist.get(i).getCompany().get(0));

        }

        spinner_category.setAdapter(categoryAdapter);
        spinner_category.setOnItemSelectedListener(this);

        spinner_company.setOnItemSelectedListener(this);
        spinner_company.setAdapter(companyAdapter);

        setCompetitorPOIData();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category_cd.equals("") || (category_cd == null)) {

                    Snackbar.make(v, "Please Select a Category", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }else  if (company_cd.equals("") || (company_cd == null)) {

                    Snackbar.make(v, "Please Select a Competitor", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }else if(etsku.getText().toString().equals("")) {

                    Snackbar.make(v, "Please Enter SKU", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                }else if(!etpromotion.getText().toString().equals("")){

                    promotionGetterSetter.setRemark(etremark.getText().toString().replaceAll("[&^<>{}'$]", ""));
                    promotionGetterSetter.setPromotion(etpromotion.getText().toString().replaceAll("[&^<>{}'$]", ""));
                    promotionGetterSetter.setSku(etsku.getText().toString().replaceAll("[&^<>{}'$]", ""));
                    promotionGetterSetter.setCategory_cd(category_cd);
                    promotionGetterSetter.setCategory(category);
                    promotionGetterSetter.setCompany_cd(company_cd);
                    promotionGetterSetter.setCompany(company);

                    db.insertCompetitionPromotionData(promotionGetterSetter, store_cd);

                    spinner_category.setSelection(0);
                    spinner_company.setSelection(0);
                    etremark.setText("");
                    etpromotion.setText("");
                    etsku.setText("");

                    poilist.clear();
                    setCompetitorPOIData();
                    lv.invalidate();

                }
                else {
                    Snackbar.make(v, "Please Enter Promotion", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
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

        if(parent.getId()==  R.id.spincategory){

            if (position != 0) {

                category_cd = categorylist.get(position-1).getCategory_cd();
                category = categorylist.get(position-1).getCategory();
            }
            else{
                category_cd="";
                category = "";
            }

        }else  if(parent.getId()==  R.id.spincompany){

            if (position != 0) {

                company_cd = companylist.get(position-1).getCompany_cd().get(0);
                company = companylist.get(position-1).getCompany().get(0);
            }
            else{
                company_cd="";
                company = "";
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setCompetitorPOIData(){



        poilist = db.getCompetitionPromotionData(store_cd);


        if(poilist.size()>0){

            /*skuStocklist.clear();

            for (int j = 0; j < nonpromoterData.size(); j++) {
                skuData skudata=new skuData();
                String skuname="";

                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getSKU_CD().get(0).equals(nonpromoterData.get(j).getSku_CD())){
                        skuname=list.get(i).getSKU().get(0);
                        break;
                    }
                }

                skudata.setId(nonpromoterData.get(j).getKey_ID());
                skudata.setSku(skuname);
                skudata.setStock(nonpromoterData.get(j).getStock());
                skudata.setSales( nonpromoterData.get(j).getSales());

                skuStocklist.add(skudata);
            }
*/


            heading.setVisibility(View.VISIBLE);

            lv.setAdapter(new POIAdaptor(getApplicationContext(), poilist));
            lv.setVisibility(View.VISIBLE);
        }

    }


    private class POIAdaptor extends ArrayAdapter<CompetitionPromotionGetterSetter>{



        public POIAdaptor(Context context, ArrayList<CompetitionPromotionGetterSetter> poidata) {
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
                convertView = inflater.inflate(R.layout.competitionpromotion_list, null);

                holder.tvcategory = (TextView) convertView
                        .findViewById(R.id.tvcategory);
                holder.tvpromotion= (TextView) convertView
                        .findViewById(R.id.tvpromotion);
                holder.tvsku= (TextView) convertView
                        .findViewById(R.id.tvsku);
                holder.tvcompetitor = (TextView) convertView.findViewById(R.id.tvcompany);
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

                        db.deleteCompetitionPromotionRow(id);
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
            holder.tvcategory.setText(poilist.get(position).getCategory());
           holder.tvpromotion.setText(poilist.get(position).getPromotion());
            holder.tvcompetitor.setText(poilist.get(position).getCompany());
            holder.tvsku.setText(poilist.get(position).getSku());

            return convertView;
        }


        public class ViewStockHolder {
            TextView tvcategory;
            TextView tvpromotion;
            TextView tvsku;
            TextView tvcompetitor;
            Button delete;
            RelativeLayout layout;
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

package com.ptg.inventory.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptg.inventory.activity.R;
import com.ptg.inventory.model.InwardProductListObject;
import com.ptg.inventory.utils.Constants;
import com.ptg.inventory.utils.DatabaseHandler;

import java.util.ArrayList;

public class DeliveryStockFragment extends Fragment {

    private LinearLayout llStock = null;

int arrPos=0;
    int discAmt=0;
    TextView tv_prodName;
    TextView tv_prodcode;
    TextView tv_os_CB;
    TextView tv_os_LOOSE;
    EditText et_cs_CB;
    EditText et_cs_LB;
    Button tv_Exp;
    static final int DATE_PICKER_ID = 1111;
    TextView tv_netAmt;
    Button btn_Damages;
    CheckBox cb_var;
    boolean varChecked=false;

    Button btn_Submit,btn_discounts;
    int totalamt=0;
    int pos=0;
    TextView tv_damages;
    TextView tv_prodAmt,tv_totalAmt;
    private LinearLayout llExpenses = null;
    DatabaseHandler dbHandler=null;
    int totalAMt=0;
   private final   ArrayList<InwardProductListObject> arr_itemsList=new ArrayList<InwardProductListObject>();
    private final   ArrayList<InwardProductListObject> arr_Discounts_itemsList=new ArrayList<InwardProductListObject>();

    TextView tvTotalExpenses,tv_varAct;

    int var_D=0;
    int var_P=0;
    int var_Q=0;
    int var_N=0;
    int var_L=0;
    int var_S=0;
    int var_U=0;
    int var_G=0;

    int expTotal=0;
    int damgeTotal=0;

 EditText et_Date;
    Button btn_submit;
    TextView tv_ViewStock;
    String isVarChecked;

    int a=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View deliveryStockView = inflater.inflate(R.layout.fragment_del_stock, container, false);
      //  arr_itemsList.clear();
        dbHandler=new DatabaseHandler(getActivity());
        arr_itemsList.clear();
        initializeUIComponents(deliveryStockView);




        Cursor cvar = dbHandler.retriveData("Select * from " + DatabaseHandler.TABLE_VARIABLE);//+"' where PRODUCT_DATE like '"+Constants.getTodayDate()+"%'" );

        if (cvar != null) {

            try {
                if (cvar.getCount() > 0) {
                    var_D=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_D)));
                    var_P=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_P)));
                    var_Q=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_Q)));
                    var_N=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_N)));
                    var_L=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_L)));
                    var_S=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_S)));
                    var_U=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_U)));
                    var_G=Integer.parseInt(cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_G)));
                    isVarChecked=cvar.getString(cvar.getColumnIndex(DatabaseHandler.VAR_Checked));

                }
            } catch (Exception e) {

            }
        }

        if(isVarChecked.equalsIgnoreCase("YES")){
            tv_varAct.setText("Var Amount Activated");
            tv_varAct.setTextColor(Color.BLUE);
        }else{
            tv_varAct.setText("Var Amount Not Activated");
            tv_varAct.setTextColor(Color.BLACK);
        }

        tv_varAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new VariableFragment()).addToBackStack("frag").commit();

            }
        });


        Cursor c = dbHandler.retriveData("Select * from " + DatabaseHandler.TABLE_MYSTOCK+" where PRODUCT_APPEAR='"+"Y"+"'" );


                     //   Cursor c = dbHandler.retriveData("Select * from " + DatabaseHandler.TABLE_MYSTOCK);//+"' where PRODUCT_DATE like '"+Constants.getTodayDate()+"%'" );

                if (c != null) {

                    try {
                        c.moveToFirst();
                        Log.v("PRODUCT COUNT", "PRODUCT COUNT.." + c.getCount());
                        if (c.getCount() > 0) {
                            do {
                                String data = c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_ID));
                                InwardProductListObject iplb = new InwardProductListObject();
                                iplb.setProd_Name(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_NAME)));
                                iplb.setProd_Code(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)));
                                iplb.setProd_Stock(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_OPENING_CB)));
                                iplb.setProd_OS_CB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_OPENING_CB))));
                                iplb.setProd_OS_LB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_OPENING_LB))));
                                int varAmt=0;
                                if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("D")){

                                    varAmt=var_D;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("P")){

                                    varAmt=var_P;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("Q")){

                                    varAmt=var_Q;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("N")){

                                    varAmt=var_N;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("L")){

                                    varAmt=var_L;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("S")){

                                    varAmt=var_S;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("U")){

                                    varAmt=var_U;
                                }else if(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CODE)).contains("G")){

                                    varAmt=var_G;
                                }



                                int varAount=(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_MRP_PRICE)))+varAmt);


                                Log.v("PRODAMOUNT...","PRODUCT AMOUNT..."+varAount);
                                iplb.setProd_Amt(""+varAount);//c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_MRP_PRICE)));



                                iplb.setProdId(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_ID)));
                                iplb.setProd_CS_CB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB))));
                                iplb.setProd_CS_LB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_LB))));
                                iplb.setProd_DAMAGES(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_DAMAGES))));
                                iplb.setProd_TotalAmt(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_AMOUNT))));

                                iplb.setProd_TotalSale_CB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_CB))));
                                iplb.setProd_TotalSale_LB(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_LB))));

                                iplb.setProd_cs_amt(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_AMOUNT))));
                                //   btn_discounts.setText(""+Constants._DISCAMT);
                                arr_itemsList.add(iplb);
                                addItem(llStock);
                                totalamt += Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_AMOUNT)));
                                String dataIS=c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_AMOUNT));
                                Log.v("TotalAMOUNT..","TOTAL AMOUNT..."+totalamt+"DATAIS..."+c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB)));
                                tv_totalAmt.setText(""+totalamt);
                                tv_prodAmt.setText(""+dataIS);
                                tv_os_CB.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_OPENING_CB)));
                                tv_os_LOOSE.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_OPENING_LB)));
                                if(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB)))==0){
                                    et_cs_CB.setText("");//c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB)));
                                }else{
                                    et_cs_CB.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB)));
                                }

                                if(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_LB)))==0){

                                    et_cs_LB.setText("");
                                }else{
                                    et_cs_LB.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_CLOSING_LB)));
                                }
                                Constants.strDeliveryStockDate=c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_DATE_STAMP));

                                tv_damages.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_CB)) +" | "+c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_LB)));

                                tv_prodAmt.setText(c.getString(c.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_AMOUNT)));


                                arrPos++;
                                pos++;

                            } while (c.moveToNext());


                        }


                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }

        //et_Date.setText(Constants.strDeliveryStockDate);
                Cursor cdisc=dbHandler.retriveData("Select * from TBL_PROD_DISCOUNTS where Disc_createdDate like '"+Constants.strDeliveryStockDate+"%'" );

                if(cdisc!=null){
                    try{
                        cdisc.moveToFirst();
                        Log.v("PRODUCT COUNT","PRODUCT COUNT.DISCOUNRS."+cdisc.getCount());
                        if(cdisc.getCount()>0) {
                            do {

                                discAmt+=Integer.parseInt(cdisc.getString(cdisc.getColumnIndex(DatabaseHandler.DISC_Amt)));
                                Log.v("PRODUCT COUNT","PRODUCT COUNT.DISCOUNRS.1212112...."+cdisc.getString(cdisc.getColumnIndex(DatabaseHandler.DISC_Amt)));
                            } while (cdisc.moveToNext());
                        }
                    }catch(Exception e){

                        Log.v("ERRORR...","ERROR DATA...."+e.getMessage());
                    }
                    cdisc.close();
                }



                Cursor cexp=dbHandler.retriveData("Select * from TBL_EXPANCES where EXP_PRODUCT_DATE like '"+Constants.strDeliveryStockDate+"%'" );

                if(cexp!=null){
                    try{
                        cexp.moveToFirst();
                        Log.v("PRODUCT COUNT","PRODUCT COUNT.DISCOUNRS."+cexp.getCount());
                        if(cexp.getCount()>0) {
                            do {

                                expTotal+=Integer.parseInt(cexp.getString(cexp.getColumnIndex(DatabaseHandler.EXP_AMOUNT)));

                            } while (cexp.moveToNext());


                        }


                    }catch(Exception e){

                    }
                    cexp.close();
                }


        Cursor cdamages=dbHandler.retriveData("Select * from TBL_MYSTOCK");// where EXP_PRODUCT_DATE like '"+Constants.strDeliveryStockDate+"%'" );

        if(cdamages!=null){
            try{
                cdamages.moveToFirst();
                Log.v("PRODUCT COUNT","PRODUCT COUNT.DISCOUNRS."+cdamages.getCount());
                if(cdamages.getCount()>0) {
                    do {

                        damgeTotal += Integer.parseInt(cdamages.getString(cdamages.getColumnIndex(DatabaseHandler.MYSTOCK_PROD_DAMAGES_AMT)));

                    } while (cdamages.moveToNext());
                }

            }catch(Exception e){

            }
        }



        tv_Exp.setText(""+expTotal);
                btn_discounts.setText(""+discAmt);
        btn_Damages.setText(""+damgeTotal);

                int a=Integer.parseInt(tv_totalAmt.getText().toString());

                int b=Integer.parseInt(tv_Exp.getText().toString());
                int z=Integer.parseInt(btn_discounts.getText().toString());

                Log.v("*******...","****DATA....."+a+"...exp..."+b+"....disc..."+z);

                int k=(totalamt-(expTotal+discAmt+damgeTotal));
                tv_netAmt.setText(""+k);






        return deliveryStockView;
    }


    @Override
    public void onPause() {
        super.onPause();
        discAmt=0;
        totalamt=0;
        expTotal=0;
    }



    public void initializeUIComponents(View v){
        //arr_BottleCount = new ArrayList<BottleDataDo>();
        llStock = (LinearLayout)v.findViewById(R.id.ll_stock);
        llExpenses= (LinearLayout)v.findViewById(R.id.llExpenses);
        tv_totalAmt=(TextView)v.findViewById(R.id.tv_totalAmt);
        btn_discounts=(Button)v.findViewById(R.id.btn_discounts);
        tvTotalExpenses=(TextView)v.findViewById(R.id.tvTotalExpenses);
        btn_Submit=(Button)v.findViewById(R.id.btn_submit);
        tv_Exp=(Button)v.findViewById(R.id.tv_exp);
        tv_netAmt=(TextView)v.findViewById(R.id.tvNetAmt);
        btn_Submit=(Button)v.findViewById(R.id.btn_submit);
        tv_ViewStock=(TextView)v.findViewById(R.id.id_stock);
        et_Date=(EditText)v.findViewById(R.id.et_date);
        btn_Damages=(Button)v.findViewById(R.id.btn_damages);
        tv_varAct=(TextView)v.findViewById(R.id.tv_varActivate);
        et_Date.setFocusable(false);

        et_Date.setText(Constants.strDeliveryStockDate);

      /*  et_Date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

              //  Constants.dateID=200;
                MainActivity._CONTEXT.showDialog(DATE_PICKER_ID);
                return false;
            }
        });*/


        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Constants.totalDamages=Integer.parseInt(btn_Damages.getText().toString());
                    Constants.totalDisc=Integer.parseInt(btn_discounts.getText().toString());
                    Constants.totalExp=Integer.parseInt(tv_Exp.getText().toString());
                    Constants.totalAmount=Integer.parseInt(tv_totalAmt.getText().toString());

                    Constants.netAmount=Integer.parseInt(tv_netAmt.getText().toString());

                    ContentValues cv = new ContentValues();

                    cv.put(DatabaseHandler.MYSTOCK_PROD_DATE_STAMP, "" + Constants.strDeliveryStockDate);

                    dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");





                    getFragmentManager().beginTransaction().replace(R.id.frame_container, new InvoiceFragment()).addToBackStack("frag").commit();


            }
        });
        tv_Exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             //   if(!et_Date.getText().toString().equalsIgnoreCase("Select Date")) {


                    getFragmentManager().beginTransaction().replace(R.id.frame_container, new ExpancesFragment()).addToBackStack("frag").commit();
              /*  }else{
                    Toast.makeText(getActivity(),"Please enter the valid date!",Toast.LENGTH_SHORT).show();
                }*/
            }
        });


        btn_discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  if(!et_Date.getText().toString().equalsIgnoreCase("Select Date")) {
                    getFragmentManager().beginTransaction().replace(R.id.frame_container, new DiscountsAddFragment()).addToBackStack("frag").commit();
              /*  }else{
                    Toast.makeText(getActivity(),"Please enter the valid date!",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    public void addItem(LinearLayout ll) {
         View v=ll;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        v = inflater.inflate(R.layout.closingstock_prodlist_inflater, null);
        tv_prodName=(TextView)v.findViewById(R.id.tv_prodName);
        tv_prodcode=(TextView)v.findViewById(R.id.tv_prodCode);
        tv_os_CB=(TextView)v.findViewById(R.id.tv_os_CB);
        tv_os_LOOSE=(TextView)v.findViewById(R.id.tv_os_LOOSE);
        et_cs_CB=(EditText)v.findViewById(R.id.et_cs_CB);
        et_cs_LB=(EditText)v.findViewById(R.id.et_cs_LB);
       //   tv_disounts=(TextView)v.findViewById(R.id.tv_discounts);
        tv_damages=(TextView) v.findViewById(R.id.et_damages);
        tv_prodAmt=(TextView)v.findViewById(R.id.tv_amount);

        et_cs_CB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    pos=v.getId();
                tv_prodAmt.getTag();
                tv_os_LOOSE.getTag();

                Log.v("TOUCHED...","TOUCHED..."+pos);
                tv_os_CB.getTag();
                tv_prodAmt= (TextView) ((View)v.getTag()).findViewById(R.id.tv_amount);
                tv_os_CB= (TextView) ((View)v.getTag()).findViewById(R.id.tv_os_CB);
                tv_os_LOOSE= (TextView) ((View)v.getTag()).findViewById(R.id.tv_os_LOOSE);
                et_cs_LB= (EditText) ((View)v.getTag()).findViewById(R.id.et_cs_LB);
                et_cs_CB= (EditText) ((View)v.getTag()).findViewById(R.id.et_cs_CB);
                tv_damages= (TextView) ((View)v.getTag()).findViewById(R.id.et_damages);
                eachPeaceCost=Integer.parseInt(arr_itemsList.get(pos).getProd_Amt());

                Log.v("EACH COSR","EACH PIECE COST..."+eachPeaceCost);
                return false;
            }
        });

      et_cs_LB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pos=v.getId();
                tv_prodAmt.getTag();
                tv_os_LOOSE.getTag();
                tv_os_CB.getTag();
                tv_damages.getTag();
                tv_prodAmt= (TextView) ((View)v.getTag()).findViewById(R.id.tv_amount);
                tv_os_CB= (TextView) ((View)v.getTag()).findViewById(R.id.tv_os_CB);
                tv_os_LOOSE= (TextView) ((View)v.getTag()).findViewById(R.id.tv_os_LOOSE);
                et_cs_CB= (EditText) ((View)v.getTag()).findViewById(R.id.et_cs_CB);
                tv_damages= (TextView) ((View)v.getTag()).findViewById(R.id.et_damages);
                et_cs_LB= (EditText) ((View)v.getTag()).findViewById(R.id.et_cs_LB);
                eachPeaceCost=Integer.parseInt(arr_itemsList.get(pos).getProd_Amt());

                return false;
            }
        });





        et_cs_CB.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                String str=String.valueOf(arg0);
                eachPeaceCost=Integer.parseInt(arr_itemsList.get(pos).getProd_Amt());
                if(str.equalsIgnoreCase("")){
                          int j = 0;
                            int os_Bottle_Coount = ((Integer.parseInt(tv_os_CB.getText().toString()) * bottleCount(pos)) + Integer.parseInt(tv_os_LOOSE.getText().toString()));
                            arr_itemsList.get(pos).setProd_CS_CB(j);
                            int cs_Bottle_Count = ((j * bottleCount(pos))+(arr_itemsList.get(pos).getProd_CS_LB()));//+arr_itemsList.get(pos).getProd_DAMAGES()));

                    arr_itemsList.get(pos).setProdId(arr_itemsList.get(pos).getProdId());

                    if(cs_Bottle_Count!=0){
                        int totalSaleBottles=(os_Bottle_Coount-cs_Bottle_Count);



                        float v=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                        int p=(int)v;

                        int q=(totalSaleBottles-((int)p*bottleCount(pos)));



                            tv_damages.setText(p + " | " + q);
                            tv_prodAmt.setText("" + ((totalSaleBottles * eachPeaceCost)));
                            arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                            arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                            arr_itemsList.get(pos).setProd_cs_amt(totalSaleBottles * eachPeaceCost);
                            arr_itemsList.get(pos).setProd_TotalAmt(totalSaleBottles * eachPeaceCost);


                    }else{
                     int   p=0;
                     int   q=0;
                        tv_damages.setText(p + " | " + q);
                        tv_prodAmt.setText("0");// + ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                        arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                        arr_itemsList.get(pos).setProd_cs_amt(0);//((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalAmt(0);// ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));

                    }


                            int a=0;
                            for(int i=0;i<arr_itemsList.size();i++){

                                a+=arr_itemsList.get(i).getProd_TotalAmt();
                                Log.v("POSITION..","POS DATA"+arr_itemsList.get(i).getProd_TotalAmt());
                                tv_totalAmt.setText(""+a);
                            }
                            int k=(a-(expTotal+discAmt));
                            tv_netAmt.setText(""+k);

                            ContentValues cv = new ContentValues();
                            cv.put(DatabaseHandler.MYSTOCK_PROD_CLOSING_STOCK, "" + cs_Bottle_Count);
                            cv.put(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB, "" + arr_itemsList.get(pos).getProd_CS_CB());
                            cv.put(DatabaseHandler.MYSTOCK_PROD_CLOSING_LB, "" + arr_itemsList.get(pos).getProd_CS_LB());
                            cv.put(DatabaseHandler.MYSTOCK_PROD_AMOUNT, "" + arr_itemsList.get(pos).getProd_cs_amt());
                            cv.put(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_LB, "" + arr_itemsList.get(pos).getProd_TotalSale_LB());
                            cv.put(DatabaseHandler.MYSTOCK_PROD_TOTALSALE_CB, "" + arr_itemsList.get(pos).getProd_TotalSale_CB());
                            cv.put(DatabaseHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + arr_itemsList.get(pos).getProd_TotalAmt());

                            dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");
                    dbHandler.UpdateTable(DatabaseHandler.TABLE_H_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"' where PRODUCT_DATE like '"+Constants.strDeliveryStockDate+"%'" );

                            Log.v("UPDATED..","UPDATED...."+pos+"...cb.."+arr_itemsList.get(pos).getProd_cs_amt()+"..PRODUCT ID.."+arr_itemsList.get(pos).getProdId());

                  //  Log.v("UPDATED..","UPDATED...."+arr_itemsList.get(pos).getProd_CS_CB()+"..PRODUCT ID.."+arr_itemsList.get(pos).getProdId());
                }else {
                            int j=Integer.parseInt(str);

                    int os_Bottle_Coount = ((Integer.parseInt(tv_os_CB.getText().toString()) * bottleCount(pos)) + Integer.parseInt(tv_os_LOOSE.getText().toString()));

                    arr_itemsList.get(pos).setProd_CS_CB(j);
                            int cs_Bottle_Count = ((j * bottleCount(pos))+(arr_itemsList.get(pos).getProd_CS_LB()));//+arr_itemsList.get(pos).getProd_DAMAGES()));


                    Log.v("TOTALSALE....","TOTALSALE..."+(os_Bottle_Coount-cs_Bottle_Count));
                    Log.v("TOTALSALE....","TOTALSALE...bottles float"+(os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                    float hh=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));

                    Log.v("TOTALSALE....","TOTALSALE...bottles"+ (int)hh);

                    arr_itemsList.get(pos).setProdId(arr_itemsList.get(pos).getProdId());


                    if(cs_Bottle_Count!=0){
                        int totalSaleBottles=(os_Bottle_Coount-cs_Bottle_Count);


                        float v=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                        int p=(int)v;

                        int q=(totalSaleBottles-((int)p*bottleCount(pos)));





                            tv_damages.setText(p + " | " + q);
                            tv_prodAmt.setText("" + ((totalSaleBottles * eachPeaceCost)));
                            arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                            arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                            arr_itemsList.get(pos).setProd_cs_amt(totalSaleBottles * eachPeaceCost);
                            arr_itemsList.get(pos).setProd_TotalAmt(totalSaleBottles * eachPeaceCost);


                    }else{
                        int   p=0;
                        int   q=0;
                        tv_damages.setText(p + " | " + q);
                        tv_prodAmt.setText("0");// + ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                        arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                        arr_itemsList.get(pos).setProd_cs_amt(0);//((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalAmt(0);// ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));

                    }





                    int a=0;
                            for(int i=0;i<arr_itemsList.size();i++){
                                a+=arr_itemsList.get(i).getProd_TotalAmt();
                                Log.v("POSITION..","POS DATA"+arr_itemsList.get(i).getProd_TotalAmt());
                                tv_totalAmt.setText(""+a);
                            }

                            int k=(a-(expTotal+discAmt));
                            tv_netAmt.setText(""+k);




                            ContentValues cv = new ContentValues();
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_STOCK, "" + cs_Bottle_Count);


                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_CB, "" + arr_itemsList.get(pos).getProd_CS_CB());
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_LB, "" + arr_itemsList.get(pos).getProd_CS_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_AMOUNT, "" + arr_itemsList.get(pos).getProd_cs_amt());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_LB, "" + arr_itemsList.get(pos).getProd_TotalSale_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_CB, "" + arr_itemsList.get(pos).getProd_TotalSale_CB());


                            cv.put(dbHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + arr_itemsList.get(pos).getProd_TotalAmt());
                            dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");
                    dbHandler.UpdateTable(DatabaseHandler.TABLE_H_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"' where PRODUCT_DATE like '"+Constants.strDeliveryStockDate+"%'" );

                         //   dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_NAME='" + arr_itemsList.get(pos).getProd_Name() + "' AND PRODUCT_CODE='"+arr_itemsList.get(pos).getProd_Code()+"'");

                         //   dbHandler.UpdateTable(dbHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId() + "'");
                            Log.v("UPDATED..","UPDATED...."+pos+"...cb.."+arr_itemsList.get(pos).getProd_cs_amt()+"..PRODUCT ID.."+arr_itemsList.get(pos).getProdId());



                }
            }
            @Override
            public void afterTextChanged(Editable s) {


                Log.v("AFTER TEXT CHANGED..","TEXT CHANGED..."+s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

        });




      et_cs_LB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str=String.valueOf(s);
                eachPeaceCost=Integer.parseInt(arr_itemsList.get(pos).getProd_Amt());
                if(str.equalsIgnoreCase("")){

                            int j = 0;
                            int os_Bottle_Coount = ((Integer.parseInt(tv_os_CB.getText().toString()) * bottleCount(pos)) + Integer.parseInt(tv_os_LOOSE.getText().toString()));
                            arr_itemsList.get(pos).setProd_CS_LB(j);
                            int   cs_Bottle_Count = (((bottleCount(pos)*arr_itemsList.get(pos).getProd_CS_CB())));//+(arr_itemsList.get(pos).getProd_DAMAGES()));
                    arr_itemsList.get(pos).setProdId(arr_itemsList.get(pos).getProdId());

                    if(cs_Bottle_Count!=0){
                        int totalSaleBottles=(os_Bottle_Coount-cs_Bottle_Count);


                        float v=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                        int p=(int)v;
                        int q=(totalSaleBottles-((int)p*bottleCount(pos)));




                            tv_damages.setText(p + " | " + q);
                            tv_prodAmt.setText("" + ((totalSaleBottles * eachPeaceCost)));
                            arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                            arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                            arr_itemsList.get(pos).setProd_cs_amt(totalSaleBottles * eachPeaceCost);
                            arr_itemsList.get(pos).setProd_TotalAmt(totalSaleBottles * eachPeaceCost);


                    }else{
                        int   p=0;
                        int   q=0;
                        tv_damages.setText(p + " | " + q);
                        tv_prodAmt.setText("0");// + ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                        arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                        arr_itemsList.get(pos).setProd_cs_amt(0);//((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalAmt(0);// ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));

                    }




                    int a=0;
                            for(int i=0;i<arr_itemsList.size();i++){

                                a+=arr_itemsList.get(i).getProd_TotalAmt();
                                Log.v("POSITION..","POS DATA"+arr_itemsList.get(i).getProd_TotalAmt());
                                tv_totalAmt.setText(""+a);
                            }

                            int k=(a-(expTotal+discAmt));
                            tv_netAmt.setText(""+k);



                            ContentValues cv = new ContentValues();
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_STOCK, "" + cs_Bottle_Count);


                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_CB, "" + arr_itemsList.get(pos).getProd_CS_CB());
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_LB, "" + arr_itemsList.get(pos).getProd_CS_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_AMOUNT, "" + arr_itemsList.get(pos).getProd_cs_amt());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_LB, "" + arr_itemsList.get(pos).getProd_TotalSale_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_CB, "" + arr_itemsList.get(pos).getProd_TotalSale_CB());


                            cv.put(dbHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + arr_itemsList.get(pos).getProd_TotalAmt());
                            dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");
                    dbHandler.UpdateTable(DatabaseHandler.TABLE_H_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");

                      //      dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_NAME='" + arr_itemsList.get(pos).getProd_Name() + "' AND PRODUCT_CODE='"+arr_itemsList.get(pos).getProd_Code()+"'");

                            Log.v("UPDATED..","UPDATED...."+pos+"...cb.."+arr_itemsList.get(pos).getProd_cs_amt()+"..PRODUCT ID.."+arr_itemsList.get(pos).getProdId());

                }else {
                            int j=Integer.parseInt(str);
                           int os_Bottle_Coount = (Integer.parseInt(tv_os_CB.getText().toString()) * bottleCount(pos)) + Integer.parseInt(tv_os_LOOSE.getText().toString());
                            arr_itemsList.get(pos).setProd_CS_LB(j);
                            int  cs_Bottle_Count = ((arr_itemsList.get(pos).getProd_CS_CB()*bottleCount(pos))+ j);//(j+arr_itemsList.get(pos).getProd_DAMAGES()));
                    Log.v("TOTALSALE....","TOTALSALE..."+(os_Bottle_Coount-cs_Bottle_Count));
                    Log.v("TOTALSALE....","TOTALSALE...bottles float"+(os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                    float hh=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));

                    Log.v("TOTALSALE....","TOTALSALE...bottles"+ (int)hh);


                    if(cs_Bottle_Count!=0){
                        int totalSaleBottles=(os_Bottle_Coount-cs_Bottle_Count);

                        float v=((os_Bottle_Coount-cs_Bottle_Count)/bottleCount(pos));
                        int p=(int)v;

                        int q=(totalSaleBottles-((int)p*bottleCount(pos)));




                            tv_damages.setText(p + " | " + q);
                            tv_prodAmt.setText("" + ((totalSaleBottles * eachPeaceCost)));
                            arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                            arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                            arr_itemsList.get(pos).setProd_cs_amt(totalSaleBottles * eachPeaceCost);
                            arr_itemsList.get(pos).setProd_TotalAmt(totalSaleBottles * eachPeaceCost);




                    }else{
                        int   p=0;
                        int   q=0;
                        tv_damages.setText(p + " | " + q);
                        tv_prodAmt.setText("0");// + ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalSale_CB(p);
                        arr_itemsList.get(pos).setProd_TotalSale_LB(q);
                        arr_itemsList.get(pos).setProd_cs_amt(0);//((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));
                        arr_itemsList.get(pos).setProd_TotalAmt(0);// ((totalSaleBottles * eachPeaceCost) + (totalSaleBottles * varAmt)));

                    }

                    int a=0;
                            for(int i=0;i<arr_itemsList.size();i++){

                                a+=arr_itemsList.get(i).getProd_TotalAmt();
                                Log.v("POSITION..","POS DATA"+arr_itemsList.get(i).getProd_TotalAmt());
                                tv_totalAmt.setText(""+a);
                            }

                            int k=(a-(expTotal+discAmt));
                            tv_netAmt.setText(""+k);

                            ContentValues cv = new ContentValues();
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_STOCK, "" + cs_Bottle_Count);


                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_CB, "" + arr_itemsList.get(pos).getProd_CS_CB());
                            cv.put(dbHandler.MYSTOCK_PROD_CLOSING_LB, "" + arr_itemsList.get(pos).getProd_CS_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_AMOUNT, "" + arr_itemsList.get(pos).getProd_cs_amt());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_LB, "" + arr_itemsList.get(pos).getProd_TotalSale_LB());
                            cv.put(dbHandler.MYSTOCK_PROD_TOTALSALE_CB, "" + arr_itemsList.get(pos).getProd_TotalSale_CB());


                            cv.put(dbHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + arr_itemsList.get(pos).getProd_TotalAmt());
                            dbHandler.UpdateTable(DatabaseHandler.TABLE_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");
                            dbHandler.UpdateTable(DatabaseHandler.TABLE_H_MYSTOCK, cv, "PRODUCT_ID='" + arr_itemsList.get(pos).getProdId()+"'");


                    Log.v("UPDATED..","UPDATED...."+pos+"...cb.."+arr_itemsList.get(pos).getProd_cs_amt()+"..PRODUCT ID.."+arr_itemsList.get(pos).getProdId());

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        et_cs_CB.setTag(v);
        et_cs_LB.setTag(v);
        tv_os_CB.setTag(v);
        tv_os_LOOSE.setTag(v);
        tv_prodAmt.setTag(v);
        tv_damages.setTag(v);
        totalAMt=0;






        if(arr_itemsList.get(arrPos).getProd_Code().contains("D")){
            tv_prodcode.setText("D");
            Log.v("PRODUCT","PRODUCT CODE..."+arr_itemsList.get(arrPos).getProd_Code()+"..OS CB..."+arr_itemsList.get(arrPos).getProd_OS_CB()+"...OS LB.."+arr_itemsList.get(arrPos).getProd_OS_LB());
            Log.v("PRODUCT","PRODUCT ****...POS"+arrPos+"..CSCB.."+arr_itemsList.get(arrPos).getProd_CS_CB()+"..OS LB..."+arr_itemsList.get(arrPos).getProd_CS_LB());

        }else if(arr_itemsList.get(arrPos).getProd_Code().contains("P")){
            tv_prodcode.setText("P");
            Log.v("PRODUCT","PRODUCT CODE..."+arr_itemsList.get(arrPos).getProd_Code()+"..OS CB..."+arr_itemsList.get(arrPos).getProd_OS_CB()+"...OS LB.."+arr_itemsList.get(arrPos).getProd_OS_LB());
            Log.v("PRODUCT","PRODUCT ****...POS"+arrPos+"..CSCB.."+arr_itemsList.get(arrPos).getProd_CS_CB()+"..OS LB..."+arr_itemsList.get(arrPos).getProd_CS_LB());


        }else if(arr_itemsList.get(arrPos).getProd_Code().contains("Q")){
            tv_prodcode.setText("Q");
            Log.v("PRODUCT","PRODUCT CODE..."+arr_itemsList.get(arrPos).getProd_Code()+"..OS CB..."+arr_itemsList.get(arrPos).getProd_OS_CB()+"...OS LB.."+arr_itemsList.get(arrPos).getProd_OS_LB());
            Log.v("PRODUCT","PRODUCT ****...POS"+arrPos+"..CSCB.."+arrPos+"..CSCB.."+arr_itemsList.get(arrPos).getProd_CS_CB()+"..OS LB..."+arr_itemsList.get(arrPos).getProd_CS_LB());


        }else if(arr_itemsList.get(arrPos).getProd_Code().contains("N")){
            tv_prodcode.setText("N");
            Log.v("PRODUCT","PRODUCT CODE..."+arr_itemsList.get(arrPos).getProd_Code()+"..OS CB..."+arr_itemsList.get(arrPos).getProd_OS_CB()+"...OS LB.."+arr_itemsList.get(arrPos).getProd_OS_LB());
            Log.v("PRODUCT","PRODUCT ****...POS"+arrPos+"..CSCB.."+arr_itemsList.get(arrPos).getProd_CS_CB()+"..OS LB..."+arr_itemsList.get(arrPos).getProd_CS_LB());


        }else if(arr_itemsList.get(arrPos).getProd_Code().contains("L")){
            tv_prodcode.setText("L");
            Log.v("PRODUCT","PRODUCT CODE..."+arr_itemsList.get(arrPos).getProd_Code()+"..OS CB..."+arr_itemsList.get(arrPos).getProd_OS_CB()+"...OS LB.."+arr_itemsList.get(arrPos).getProd_OS_LB());
            Log.v("PRODUCT","PRODUCT ****...POS"+arrPos+"..CSCB.."+arr_itemsList.get(arrPos).getProd_CS_CB()+"..OS LB..."+arr_itemsList.get(arrPos).getProd_CS_LB());

        }


        et_cs_CB.setId(arrPos);
        et_cs_LB.setId(arrPos);



        Log.v("CB POS..","CB POS.."+et_cs_CB.getId());

        for(int i=0;i<arr_itemsList.size();i++){

            totalAMt+=arr_itemsList.get(i).getProd_cs_amt();

            if(i==0){
                tv_prodName.setVisibility(View.VISIBLE);
                tv_prodName.setText(arr_itemsList.get(i).getProd_Name());

            }else if(arr_itemsList.get(i).getProd_Name().equalsIgnoreCase(arr_itemsList.get(i-1).getProd_Name())){
                tv_prodName.setVisibility(View.GONE);//Text("");//arr_itemsList.get(i).getProd_Name());
            }else if(!arr_itemsList.get(i).getProd_Name().equalsIgnoreCase(arr_itemsList.get(i-1).getProd_Name())){
                tv_prodName.setVisibility(View.VISIBLE);
                tv_prodName.setText(arr_itemsList.get(i).getProd_Name());

            }

        }


        ll.addView(v);
        int position = ll.indexOfChild(v);
        ll.setTag(position);
    }
        int prod_Stock=0;
    int eachPeaceCost=0;

    public int bottleCount(int position){

        int bottleCount=0;



        if(arr_itemsList.get(position).getProd_Code().contains("D")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._D;

            bottleCount= Constants._D;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("N")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._N;
            bottleCount=Constants._N;


        }else  if(arr_itemsList.get(position).getProd_Code().contains("P")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._P;
            bottleCount=Constants._P;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("Q")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._Q;
            bottleCount=Constants._Q;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("L")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._L;
            bottleCount=Constants._L;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("S")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._S;
            bottleCount=Constants._S;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("U")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._U;
            bottleCount=Constants._U;

        }else  if(arr_itemsList.get(position).getProd_Code().contains("G")){
            prod_Stock=Integer.parseInt(arr_itemsList.get(position).getProd_Stock())/ Constants._G;
            bottleCount=Constants._G;

        }
        return bottleCount;

    }






}
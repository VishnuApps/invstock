package com.ptg.inventory.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ptg.inventory.activity.MainActivity;
import com.ptg.inventory.activity.R;
import com.ptg.inventory.model.InwardProductListObject;
import com.ptg.inventory.utils.Constants;
import com.ptg.inventory.utils.DatabaseHandler;

import java.util.ArrayList;


public class InwordFragment extends Fragment {

    EditText et_stock,et_invoiceno,et_prodId;
    Button btn_prodList;
    TextView tv_prodCode,tv_prodName,tv_prodID,tv_amount;
    Button btn_scan,btn_Save;
    TextView tvStatus;
    ImageView iv_search;
	public InwordFragment(){}
    DatabaseHandler dbHandler=null;
    int ProductBoxCost;
    String ProductBoxCostAmount,ProductPieceCost,product_Qty;
    int total_Amount;
    String productName,productCode,productID;
    ListView lv_prodList;
    InwardProductListObject adb;
    TextView tv_total;
  //  int toal_Amt=0;

    public  ArrayList<InwardProductListObject> arr_itemsList=new ArrayList<InwardProductListObject>();

    public  void InwordFragment(){

    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_add_inward, container, false);
        dbHandler=new DatabaseHandler(getActivity());
        tv_prodCode=(TextView)rootView.findViewById(R.id.tv_pcode);
        et_prodId=(EditText)rootView.findViewById(R.id.et_productid);
        tv_prodName=(TextView)rootView.findViewById(R.id.tv_pname);
        tv_prodID=(TextView)rootView.findViewById(R.id.tv_pid);
        tv_amount=(TextView)rootView.findViewById(R.id.tv_amount);
        et_stock=(EditText)rootView.findViewById(R.id.et_pdel);
        et_invoiceno=(EditText)rootView.findViewById(R.id.et_invoiceNo);
      //  btn_prodList=(Button)rootView.findViewById(R.id.btn_prodList);
        iv_search=(ImageView)rootView.findViewById(R.id.iv_pid_search);
        et_stock.addTextChangedListener(watch);
        btn_scan=(Button)rootView.findViewById(R.id.btn_scanProd);
        lv_prodList=(ListView)rootView.findViewById(R.id.lv_prodList);
       tv_total=(TextView)rootView.findViewById(R.id.tv_totalAmount);

        btn_Save=(Button)rootView.findViewById(R.id.btn_Submit);
        arr_itemsList.clear();

        Cursor c=dbHandler.retriveData("Select * from "+dbHandler.TABLE_MYSTOCK);
        c.moveToFirst();
        if(c!=null){
            try{
                Log.v("PRODUCT COUNT","PRODUCT COUNT.."+c.getCount());
                if(c.getCount()>0) {
                    do {


                        String data = c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_ID));


                        InwardProductListObject iplb = new InwardProductListObject();

                        iplb.setProd_Name(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_NAME)));
                        iplb.setProd_Code(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_CODE)));
                        iplb.setProd_Stock(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_OPENING_STOCK)));
                        iplb.setProd_Amt(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_WHOLESALE_PRICE)));
                        iplb.setProd_OS_CB(Integer.parseInt(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_OPENING_CB))));

                        arr_itemsList.add(iplb);
                        Log.v("DATA..", "RESULTS MYSTOCK..." + data+"size"+arr_itemsList.size());

                    } while (c.moveToNext());


                }


            }catch(Exception e){

            }

        }

        int prod_Stock=0;
        lv_prodList.setAdapter(new EfficientAdapter());

        for(int i=0;i<arr_itemsList.size();i++){
            String P_name=arr_itemsList.get(i).getProd_Name();
            Log.v("PRODUCT NAMME","PRODUCT***"+P_name);

            if(arr_itemsList.get(i).getProd_Code().contains("D")){
                prod_Stock=Integer.parseInt(arr_itemsList.get(i).getProd_Stock())/ Constants._D;
            }else  if(arr_itemsList.get(i).getProd_Code().contains("N")){
                prod_Stock=Integer.parseInt(arr_itemsList.get(i).getProd_Stock())/ Constants._N;
            }else  if(arr_itemsList.get(i).getProd_Code().contains("P")){
                prod_Stock=Integer.parseInt(arr_itemsList.get(i).getProd_Stock())/ Constants._P;
            }else  if(arr_itemsList.get(i).getProd_Code().contains("Q")){
                prod_Stock=Integer.parseInt(arr_itemsList.get(i).getProd_Stock())/ Constants._Q;
            }else  if(arr_itemsList.get(i).getProd_Code().contains("L")){
                prod_Stock=Integer.parseInt(arr_itemsList.get(i).getProd_Stock())/ Constants._L;
            }

            rest+=(prod_Stock*(Integer.parseInt(arr_itemsList.get(i).getProd_Amt())));

            Log.v("TOTAL AMOUNT...","TOTALAMOUNT DATA..."+rest);
        }



        tv_total.setText(""+rest);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int prod_Stock=0;
                if(tv_prodID.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please enter/scan the product",Toast.LENGTH_SHORT).show();
                }else  if(et_stock.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please enter stock",Toast.LENGTH_SHORT).show();
                }else {

                    int int_strock=Integer.parseInt(et_stock.getText().toString());

                    if(productCode.contains("D")){
                        prod_Stock=int_strock* Constants._D;

                    }else  if(productCode.contains("N")){
                        prod_Stock=int_strock* Constants._N;
                    }else  if(productCode.contains("P")){
                        prod_Stock=int_strock* Constants._P;
                    }else  if(productCode.contains("Q")){
                        prod_Stock=int_strock* Constants._Q;
                    }else  if(productCode.contains("L")){
                        prod_Stock=int_strock* Constants._L;
                    }

                    ContentValues cv = new ContentValues();
                    cv.put(dbHandler.MYSTOCK_INVOICE_NO, et_invoiceno.getText().toString());
                    cv.put(dbHandler.MYSTOCK_PROD_ID, productID);
                    cv.put(dbHandler.MYSTOCK_PROD_NAME, productName);
                    cv.put(dbHandler.MYSTOCK_PROD_CODE, productCode);
                    cv.put(dbHandler.MYSTOCK_PROD_OPENING_STOCK, ""+prod_Stock);
                    cv.put(dbHandler.MYSTOCK_PROD_CLOSING_STOCK, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_CLOSING_CB, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_CLOSING_LB, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_OPENING_CB, et_stock.getText().toString());
                    cv.put(dbHandler.MYSTOCK_PROD_OPENING_LB, "0");

                    cv.put(dbHandler.MYSTOCK_PROD_DISCOUNT, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_AMOUNT, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_SERVICE_CHARGE, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_DAMAGES, "0");
                    cv.put(dbHandler.MYSTOCK_PROD_QTY, product_Qty);
                    cv.put(dbHandler.MYSTOCK_MRP_PRICE, ProductPieceCost);
                    cv.put(dbHandler.MYSTOCK_WHOLESALE_PRICE, ProductBoxCostAmount);
                    cv.put(dbHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + total_Amount);
                    cv.put(dbHandler.MYSTOCK_PROD_DATE_STAMP,""+ Constants.getTodayDate());



                   Cursor c=dbHandler.retriveData("Select * from TBL_MYSTOCK where PRODUCT_ID='"+productID+"'");
                   // Toast.makeText(getActivity(),"Product Updated succesfull"+c.getCount(),Toast.LENGTH_SHORT).show();
                    if(c.getCount() > 0) {

                       dbHandler.UpdateTable(dbHandler.TABLE_MYSTOCK,cv,"PRODUCT_ID='"+productID+"'");
                        Toast.makeText(getActivity(),"Product Updated succesfull",Toast.LENGTH_SHORT).show();
                   }else {


                        dbHandler.insert(dbHandler.TABLE_MYSTOCK, cv);
                        Toast.makeText(getActivity(),"Product added succesfull",Toast.LENGTH_SHORT).show();
                    }


                    ContentValues cv_h = new ContentValues();
                    cv_h.put(dbHandler.MYSTOCK_INVOICE_NO, et_invoiceno.getText().toString());
                    cv_h.put(dbHandler.MYSTOCK_PROD_ID, productID);
                    cv_h.put(dbHandler.MYSTOCK_PROD_NAME, productName);
                    cv_h.put(dbHandler.MYSTOCK_PROD_CODE, productCode);
                    cv_h.put(dbHandler.MYSTOCK_PROD_OPENING_STOCK, ""+prod_Stock);
                    cv_h.put(dbHandler.MYSTOCK_PROD_CLOSING_STOCK, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_CLOSING_CB, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_CLOSING_LB, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_OPENING_CB, et_stock.getText().toString());
                    cv_h.put(dbHandler.MYSTOCK_PROD_OPENING_LB, "0");

                    cv_h.put(dbHandler.MYSTOCK_PROD_DISCOUNT, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_AMOUNT, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_SERVICE_CHARGE, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_DAMAGES, "0");
                    cv_h.put(dbHandler.MYSTOCK_PROD_QTY, product_Qty);
                    cv_h.put(dbHandler.MYSTOCK_MRP_PRICE, ProductPieceCost);
                    cv_h.put(dbHandler.MYSTOCK_WHOLESALE_PRICE, ProductBoxCostAmount);
                    cv_h.put(dbHandler.MYSTOCK_PROD_TOTAL_AMOUNT, "" + total_Amount);
                    cv_h.put(dbHandler.MYSTOCK_PROD_DATE_STAMP,""+ Constants.getTodayDate());



                    Cursor ch=dbHandler.retriveData("Select * from TBL_INWORD_HISTORY where PRODUCT_ID='"+productID+"'");
                    // Toast.makeText(getActivity(),"Product Updated succesfull"+c.getCount(),Toast.LENGTH_SHORT).show();
                    if(ch.getCount() > 0) {

                        dbHandler.UpdateTable(dbHandler.TABLE_H_INWORD,cv_h,"PRODUCT_ID='"+productID+"'");
                        Toast.makeText(getActivity(),"Product Updated succesfull",Toast.LENGTH_SHORT).show();
                    }else {


                        dbHandler.insert(dbHandler.TABLE_H_INWORD, cv_h);
                        Toast.makeText(getActivity(),"Product added succesfull",Toast.LENGTH_SHORT).show();
                    }






                }

//// Show the list ofproducts


                arr_itemsList.clear();
              //  toal_Amt=0;
                Cursor c=dbHandler.retriveData("Select * from "+dbHandler.TABLE_MYSTOCK);
                c.moveToFirst();
                if(c!=null){
                    try{
                        Log.v("PRODUCT COUNT","PRODUCT COUNT.."+c.getCount());
                        if(c.getCount()>0) {
                            do {


                                String data = c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_ID));


                                InwardProductListObject iplb = new InwardProductListObject();

                                iplb.setProd_Name(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_NAME)));
                                iplb.setProd_Code(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_CODE)));
                                iplb.setProd_Stock(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_OPENING_STOCK)));
                                iplb.setProd_Amt(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_WHOLESALE_PRICE)));

                                int a = Integer.parseInt(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_WHOLESALE_PRICE)));
                                int b = Integer.parseInt(c.getString(c.getColumnIndex(dbHandler.MYSTOCK_PROD_OPENING_STOCK)));
                                //toal_Amt += (a * b);
                                arr_itemsList.add(iplb);
                                Log.v("DATA..", "RESULTS MYSTOCK..." + data+"size"+arr_itemsList.size());

                            } while (c.moveToNext());


                        }


                    }catch(Exception e){

                    }

                }
              //  tv_total.setText(""+toal_Amt);

                lv_prodList.setAdapter(new EfficientAdapter());

            }
        });
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                startActivityForResult(intent, 0);
            }
        });




        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=dbHandler.retriveData("Select * from TBL_PRODUCTLIST where PRODUCT_ID='"+et_prodId.getText().toString()+"'");

                et_stock.setText("");
                et_stock.addTextChangedListener(watch);

                if(c!=null)
                {
                    if(c.getCount()>0)
                    {

                            productName=c.getString(c.getColumnIndex(dbHandler.PRODUCT_NAME));
                        productID=c.getString(c.getColumnIndex(dbHandler.PRODUCT_ID));
                        productCode=c.getString(c.getColumnIndex(dbHandler.PRODUCT_CODE));
                         ProductBoxCostAmount=c.getString(c.getColumnIndex(dbHandler.PRODUCT_COST_BOX)).replace(".0","");
                         ProductPieceCost=c.getString(c.getColumnIndex(dbHandler.PRODUCT_MRP_EACH_PIECE)).replace(".0","");
                        product_Qty=c.getString(c.getColumnIndex(dbHandler.PRODUCT_QTY_BOX)).replace(".0","");

                        int i=Integer.parseInt(product_Qty);
                        int j=Integer.parseInt(ProductPieceCost);
                        total_Amount=(i*j);


                     //   Log.v("Prod Name","PRODNAME:"+Productname+"BOx Cost:"+ProductBoxCostAmount+"PieceCost"+ProductPieceCost);


                        ProductBoxCost=Integer.parseInt(ProductBoxCostAmount);


                        tv_prodName.setText(productName);
                        tv_prodID.setText(productID);
                        tv_prodCode.setText(productCode);

                      //  Log.v("Prod Name","PRODNAME:"+Productname+"BOx Cost:"+ProductBoxCostAmount+"PieceCost"+ProductBoxCost);


                          //  Log.v("Prod Name","PRODNAME:"+Productname+"BOx Cost:"+ProductBoxCost+"PieceCost"+ProductPieceCost);


                    }else{
                        Toast.makeText(getActivity(),"Product not found",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Product not found",Toast.LENGTH_SHORT).show();
                }

            }
        });



         
        return rootView;
    }





    TextWatcher watch = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
Log.v("AFTER TEXT CHANGED","AFTER TEXT CHANGED"+arg0.length());
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

            Log.v("beforeTextChanged","beforeTextChanged TEXT CHANGED"+arg1);
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int a, int b, int c) {
            // TODO Auto-generated method stub


            String str=String.valueOf(s);
            if(str.equalsIgnoreCase("")){
                int j = 0;

                Log.v("onTextChanged", "onTextChanged TEXT CHANGED" + a);
                tv_amount.setText("" + (ProductBoxCost * j));
            }else {
                int j = Integer.parseInt(str);

                Log.v("onTextChanged", "onTextChanged TEXT CHANGED" + a);
                tv_amount.setText("" + (ProductBoxCost * j));
            }

        }};



    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            if (resultCode == MainActivity._CONTEXT.RESULT_OK) {
              //  tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tv_prodID.setText(intent.getStringExtra("SCAN_RESULT"));


                Cursor c=dbHandler.retriveData("Select * from TBL_PRODUCTLIST where PRODUCT_ID='"+tv_prodID.getText().toString()+"'");

                if(c!=null)
                {
                    if(c.getCount()>0)
                    {

                        String Productname=c.getString(c.getColumnIndex(dbHandler.PRODUCT_NAME));
                        String ProductID=c.getString(c.getColumnIndex(dbHandler.PRODUCT_ID));
                        String ProductCode=c.getString(c.getColumnIndex(dbHandler.PRODUCT_CODE));
                        String ProductBoxCost=c.getString(c.getColumnIndex(dbHandler.PRODUCT_COST_BOX));
                        String ProductPieceCost=c.getString(c.getColumnIndex(dbHandler.PRODUCT_MRP_EACH_PIECE));

                        tv_prodName.setText(Productname);
                        tv_prodID.setText(ProductID);
                        tv_prodCode.setText(ProductCode);




                        Log.v("Prod Name","PRODNAME:"+Productname+"BOx Cost:"+ProductBoxCost+"PieceCost"+ProductPieceCost);


                    }
                }




            } else if (resultCode == MainActivity._CONTEXT.RESULT_CANCELED) {
              //  tvStatus.setText("Press a button to start a scan.");
                tv_prodID.setText("Scan cancelled.");
            }
        }
    }




    public class EfficientAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arr_itemsList.size();
        }

        @Override
        public Object getItem(int position) {
            return arr_itemsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent) {

                adb = (InwardProductListObject) getItem(position);

            LayoutInflater li = getActivity().getLayoutInflater();
            convertView = li.inflate(R.layout.inward_prodlist_inflater, null);

            TextView tv_prodName=(TextView)convertView.findViewById(R.id.tv_prodNaame);
            TextView tv_prodcode=(TextView)convertView.findViewById(R.id.tv_prodCode);
            TextView tv_prodStock=(TextView)convertView.findViewById(R.id.tv_prodStcok);
            TextView tv_prodAmt=(TextView)convertView.findViewById(R.id.tv_prodAmount);

            int prod_Stock=0;
            int int_strock=Integer.parseInt(adb.getProd_Stock());



            if(adb.getProd_Code().contains("D")){
                prod_Stock=int_strock/ Constants._D;
            }else  if(adb.getProd_Code().contains("N")){
                prod_Stock=int_strock/ Constants._N;
            }else  if(adb.getProd_Code().contains("P")){
                prod_Stock=int_strock/ Constants._P;
            }else  if(adb.getProd_Code().contains("Q")){
                prod_Stock=int_strock/ Constants._Q;
            }else  if(adb.getProd_Code().contains("L")){
                prod_Stock=int_strock/ Constants._L;
            }
            tv_prodName.setText(adb.getProd_Name());
            tv_prodcode.setText(adb.getProd_Code());
            tv_prodStock.setText(""+adb.getProd_OS_CB());


            int a=prod_Stock;
            int b=Integer.parseInt(adb.getProd_Amt());

            tv_prodAmt.setText(""+(a*b));



            return convertView;

        }
    }


int rest=0;

}

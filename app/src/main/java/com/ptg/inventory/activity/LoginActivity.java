package com.ptg.inventory.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ptg.inventory.utils.Constants;
import com.ptg.inventory.utils.DatabaseHandler;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 23-07-2016.
 */
public class LoginActivity extends Activity {

    Button btn_login;
    static DatabaseHandler dbHandler=null;
    static AssetManager assetManager;
   public static JSONObject jsonObj=new JSONObject();
    private SharedPreferences sp;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    EditText et_username,et_Password;
    String result="";

    CheckBox cb_rememberme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btn_login=(Button)findViewById(R.id.button6);
        et_username=(EditText)findViewById(R.id.editText1);
        et_Password=(EditText)findViewById(R.id.editText2);
        cb_rememberme=(CheckBox)findViewById(R.id.checkBox1);

        assetManager = getAssets();
        dbHandler=new DatabaseHandler(LoginActivity.this);

        loginPreferences = getSharedPreferences("loginPrefs",
                Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            et_username.setText(loginPreferences.getString("username", ""));
            et_Password.setText(loginPreferences.getString("password", ""));
            cb_rememberme.setChecked(true);
        }

        cb_rememberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new GetPublicationsTask(LoginActivity.this).execute();

            /*    if(et_username.getText().toString().equalsIgnoreCase("Store001") && et_Password.getText().toString().equalsIgnoreCase("StarOne")){


                    if (cb_rememberme.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", et_username.getText().toString());
                        loginPrefsEditor.putString("password", et_Password.getText().toString());
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                    ContentValues cv=new ContentValues();
                    cv.put(DatabaseHandler.VAR_D,"0");
                    cv.put(DatabaseHandler.VAR_P,"0");
                    cv.put(DatabaseHandler.VAR_Q,"0");
                    cv.put(DatabaseHandler.VAR_N,"0");
                    cv.put(DatabaseHandler.VAR_L,"0");
                    cv.put(DatabaseHandler.VAR_S,"0");
                    cv.put(DatabaseHandler.VAR_U,"0");
                    cv.put(DatabaseHandler.VAR_G,"0");
                    cv.put(DatabaseHandler.VAR_DATE,"100");
                    cv.put(DatabaseHandler.VAR_Checked,"NO");

                    Cursor c = dbHandler.retriveData("Select * from TBL_VARIABLE");
                    if (c==null || c.getCount() == 0) {
                        dbHandler.insert(DatabaseHandler.TABLE_VARIABLE, cv);
                    }else{
                        dbHandler.UpdateTable(DatabaseHandler.TABLE_VARIABLE, cv, "Date='" + "100" + "'");
                    }

                    ContentValues cvLogin=new ContentValues();
                    cvLogin.put(DatabaseHandler.LOGIN_STOREID,et_username.getText().toString());
                    cvLogin.put(DatabaseHandler.LOGIN_PASSWORD,et_Password.getText().toString());

                    Cursor cLogin = dbHandler.retriveData("Select * from TBL_Login");
                    if (cLogin==null || cLogin.getCount() == 0) {
                        dbHandler.insert(DatabaseHandler.TABLE_Login, cvLogin);
                    }else{
                        dbHandler.UpdateTable(DatabaseHandler.TABLE_Login, cvLogin, "StoreId='" + et_username.getText().toString() + "'");
                    }

                    new PremiumTask(LoginActivity.this).execute();
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter valid credentials",Toast.LENGTH_SHORT).show();
                }
*/




            }
        });

    }
    class PremiumTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public PremiumTask(Activity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
            // itemsList.clear();
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;




            Cursor c=dbHandler.retriveData("select * from "+DatabaseHandler.TABLE_PRODUCTLIST);

            Log.v("Cursor Count...","TotalCursor COunt.."+c.getCount());



            if(c!=null)
            {

                if(c.getCount()==0)
                {
                    c.moveToFirst();
                    do {

                        try {

                            JSONArray jData = new JSONArray(Constants.dataJSON);

                            ContentValues cv = new ContentValues();
                            for (int i = 0; i < jData.length(); i++) {
                                JSONObject jsonObjData = jData.getJSONObject(i);//.getString("");


                                double d=Double.parseDouble(jsonObjData.getString("cost_Box"));


                                cv.put(DatabaseHandler.PRODUCT_ID, jsonObjData.getString("Product_ID").replace("c", ""));
                                cv.put(DatabaseHandler.PRODUCT_NAME, jsonObjData.getString("Product_Name"));
                                cv.put(DatabaseHandler.PRODUCT_CODE, jsonObjData.getString("ITEMCODE"));
                                cv.put(DatabaseHandler.PRODUCT_QTY_BOX, jsonObjData.getString("Quantity_Box"));
                                cv.put(DatabaseHandler.PRODUCT_COST_BOX,String.valueOf(Math.round(d)));
                                cv.put(DatabaseHandler.PRODUCT_MRP_EACH_PIECE, jsonObjData.getString("MRP_of_each_Bottle"));
                               // cv.put(DatabaseHandler.PRODUCT_TAX_BOX, jsonObjData.getString("Tax_Box"));
                                dbHandler.insert(DatabaseHandler.TABLE_PRODUCTLIST, cv);
                            }


                        } catch (JSONException e) {

                            Log.e("Json Message", e.getMessage());
                        }
                    }while(c.moveToNext());



                }
            }

            Cursor cr=dbHandler.retriveData("Select * from TBL_MYSTOCK");

            if(cr!=null) {
                if (cr.getCount()>0) {
                    cr.moveToFirst();
                    do{

                        Log.v("DATA LIST ..","PRODUCT ID.."+cr.getString(cr.getColumnIndex(dbHandler.MYSTOCK_INVOICE_NO)));

                    }while(cr.moveToNext());

                }

            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
                dialog.setCancelable(false);

                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);


            }


        }
    }





/*



    public static void readXLSXFile( String filename) {// throws IOException
        //{
        //    InputStream ExcelFileToRead = new FileInputStream("C:/Test.xlsx");




        try {

          InputStream myInput;

            //  Don't forget to Change to your assets folder excel sheet
        myInput = assetManager.open(filename);
         *//* File file = new File(Environment.getExternalStorageDirectory(), filename);
            FileInputStream myInput = new FileInputStream(file);
*//*

            XSSFWorkbook workbook = new XSSFWorkbook(myInput);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 0; r<rowsCount; r++) {
                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                ContentValues cv=new ContentValues();

                for (int c = 0; c<cellsCount; c++) {
                    String value = getCellAsString(row, c, formulaEvaluator);
                    String cellInfo = "r:"+r+"; c:"+c+"; v:"+value;


                    if(c==0){
                    jsonObj.put(DatabaseHandler.PRODUCT_ID,value.replace("c",""));
                    }else if(c==1){
                    jsonObj.put(DatabaseHandler.PRODUCT_NAME,value);
                    }else if(c==2) {
                        jsonObj.put(DatabaseHandler.PRODUCT_CODE, value);
                    }else if(c==3){
                    jsonObj.put(DatabaseHandler.PRODUCT_QTY_BOX,value);

                    }else if(c==4){
                    jsonObj.put(DatabaseHandler.PRODUCT_COST_BOX,value);
                    }else if(c==5){
                    jsonObj.put(DatabaseHandler.PRODUCT_MRP_EACH_PIECE,value);
                    }else if(c==6) {
                        jsonObj.put(DatabaseHandler.PRODUCT_TAX_BOX, value);
                    }




                    if(c==0){
                        cv.put(DatabaseHandler.PRODUCT_ID,value.replace("c",""));
                    }else if(c==1){
                        cv.put(DatabaseHandler.PRODUCT_NAME,value);
                    }else if(c==2){
                        cv.put(DatabaseHandler.PRODUCT_CODE,value);
                    }else if(c==3){
                        cv.put(DatabaseHandler.PRODUCT_QTY_BOX,value);
                    }else if(c==4){
                        cv.put(DatabaseHandler.PRODUCT_COST_BOX,value);
                    }else if(c==5){
                        cv.put(DatabaseHandler.PRODUCT_MRP_EACH_PIECE,value);
                    }else if(c==6){
                        cv.put(DatabaseHandler.PRODUCT_TAX_BOX,value);
                    }

                    Log.v("CELL INFO..","CELL INFO.."+cellInfo);
                }
                dbHandler.insert(DatabaseHandler.TABLE_PRODUCTLIST,cv);


            }

        Log.v("DATA..","DATA..."+jsonObj.toString());

          *//*  File root=null;
            try {
                // check for SDcard
                root = Environment.getExternalStorageDirectory();
                Log.i("TAG PATH..","path.." +root.getAbsolutePath());

                //check sdcard permission

                    File fileDir = new File(root.getAbsolutePath());
                    fileDir.mkdirs();

                    File file= new File(fileDir, "samplefile.txt");
                file.createNewFile();
                    FileWriter filewriter = new FileWriter(file);
                    BufferedWriter out = new BufferedWriter(filewriter);
                    out.write(jsonObj.toString());
                    out.close();

            } catch (IOException e) {
                Log.e("ERROR:---", "Could not write file to SDCard" + e.getMessage());

            }*//*
        } catch (Exception e) {
            /*//* proper exception handling to be here *//**//*
            // printlnToUser(e.toString());
            Log.v("error....","error..."+e.getMessage());
        }



    }*/

/*


    public  static String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {

                    //    int i=(int)numericValue;
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            /*/
/* proper error handling should be here *//*
*/
/*
            //  printlnToUser(e.toString());
        }
        return value;
    }


*/

    class GetPublicationsTask extends AsyncTask<String, String, String> {
        private ProgressDialog dialog;

        public GetPublicationsTask(Activity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://auditorgroup.in/inventery/API/api1.php");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("INVOICE_NO", "001155555"));
                nameValuePairs.add(new BasicNameValuePair("PRODUCT_ID", "005522244"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpclient.execute(httppost);
                //   msgTextField.setText(""); //reset the message text field
                // Toast.makeText(getBaseContext(),"Sent",Toast.LENGTH_SHORT).show();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;


        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
                dialog.setCancelable(false);
            }


        }

    }


}

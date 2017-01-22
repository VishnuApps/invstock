package com.ptg.inventory.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {


	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "Inventory";

	// questionsData table name

	public static final String TABLE_MYSTOCK = "TBL_MYSTOCK";
	public static final String TABLE_EXP = "TBL_EXPANCES";
	public static final String TABLE_PRODUCTLIST = "TBL_PRODUCTLIST";
	public static final String TABLE_DISCOUNTS = "TBL_PROD_DISCOUNTS";
	public static final String TABLE_H_DISCOUNTS = "TBL_PROD_H_DISCOUNTS";
	public static final String TABLE_CUSTOMERS = "TBL_CUSTOMERS";
	public static final String TABLE_H_MYSTOCK = "TBL_MYSTOCK_HISTORY";
	public static final String TABLE_H_INWORD = "TBL_INWORD_HISTORY";
	public static final String TABLE_DAMAGES="TBL_DAMAGES";
	public static final String TABLE_ISHOLYDAY="TBL_ISHOLYDAY";
	public static final String TABLE_Login="TBL_Login";



	public static final String TABLE_VARIABLE = "TBL_VARIABLE";
	public static final String TABLE_CLOSING_STOCK_HISTORY = "TBL_CS_HISTORY";



	public static final String VAR_SERIAL_NO="S_NO";
	public static final String VAR_D="V_D";
	public static final String VAR_P="V_P";
	public static final String VAR_Q="V_Q";
	public static final String VAR_N="V_N";
	public static final String VAR_L="V_L";
	public static final String VAR_S="V_S";
	public static final String VAR_U="V_U";
	public static final String VAR_G="V_G";
	public static final String VAR_Checked="V_chk";
	public static final String VAR_DATE="Date";



	public static final String HOLYDAY_SERIAL_NO="S_NO";
	public static final String HOLYDAY_STOREID="StoreId";
	public static final String HOLYDAY_IS="HolydayIs";
	public static final String HOLYDAY_DATE="Date";

	public static final String LOGIN_SERIAL_NO="S_NO";
	public static final String LOGIN_STOREID="StoreId";
	public static final String LOGIN_PASSWORD="Password";

	public static final String LOGIN_DATE="Date";

	public static final String MYSTOCK_SERIAL_NO="S_NO";
	public static final String MYSTOCK_INVOICE_NO="INVOICE_NO";
	public static final String MYSTOCK_PROD_ID="PRODUCT_ID";
	public static final String MYSTOCK_PROD_NAME="PRODUCT_NAME";
	public static final String MYSTOCK_PROD_QTY="PRODUCT_QTY";
	public static final String MYSTOCK_PROD_CODE="PRODUCT_CODE";
	public static final String MYSTOCK_PROD_OPENING_STOCK="PRODUCT_OPENING_STOCK";
	public static final String MYSTOCK_PROD_CLOSING_STOCK="PRODUCT_CLOSING_STOCK";
	public static final String MYSTOCK_PROD_CLOSING_CB="PRODUCT_CLOSING_CB";
	public static final String MYSTOCK_PROD_CLOSING_LB="PRODUCT_CLOSING_LB";

	public static final String MYSTOCK_PROD_TOTALSALE_CB="PRODUCT_TOTALSALE_CB";
	public static final String MYSTOCK_PROD_TOTALSALE_LB="PRODUCT_TOTALSALE_LB";

	public static final String MYSTOCK_PROD_OPENING_CB="PRODUCT_OPENING_CB";
	public static final String MYSTOCK_PROD_OPENING_LB="PRODUCT_OPENING_LB";
	public static final String MYSTOCK_PROD_DISCOUNT="PRODUCT_DISCOUNT_AMOUNT";
	public static final String MYSTOCK_MRP_PRICE="PRODUCT_MRP_PRICE";
	public static final String MYSTOCK_WHOLESALE_PRICE="PRODUCT_WHOLESALE_PRICE";
	public static final String MYSTOCK_PROD_SERVICE_CHARGE="PRODUCT_SERVICE_CHARGE";
	public static final String MYSTOCK_PROD_TOTAL_AMOUNT="PRODUCT_TOTAL_AMOUNT";
	public static final String MYSTOCK_PROD_AMOUNT="PRODUCT_AMOUNT";
	public static final String MYSTOCK_PROD_DAMAGES="PRODUCT_DAMAGES";
	public static final String MYSTOCK_PROD_DAMAGES_CB="PRODUCT_DAMAGES_CB";
	public static final String MYSTOCK_PROD_DAMAGES_LB="PRODUCT_DAMAGES_LB";
	public static final String MYSTOCK_PROD_APPEAR="PRODUCT_APPEAR";
	public static final String MYSTOCK_SYNC_STATUS="PRODUCT_SYNC_STATUS";
	public static final String MYSTOCK_PROD_DATE_STAMP="PRODUCT_DATE";

	public static final String MYSTOCK_PROD_DAMAGES_TOTAL="PRODUCT_DAMAGES_TOTAL";
	public static final String MYSTOCK_PROD_DAMAGES_AMT="PRODUCT_DAMAGES_AMT";
	//public static final String MYSTOCK_PROD_HOLYDAY="PRODUCT_HOLYDAY";
	public static final String MYSTOCK_PROD_STOREID="PRODUCT_STOREID";



	// MySTock History Coulmns




	public static final String MYSTOCK_H_SERIAL_NO="S_NO";
	public static final String MYSTOCK_H_INVOICE_NO="INVOICE_NO";
	public static final String MYSTOCK_H_PROD_ID="PRODUCT_ID";
	public static final String MYSTOCK_H_PROD_NAME="PRODUCT_NAME";
	public static final String MYSTOCK_H_PROD_QTY="PRODUCT_QTY";
	public static final String MYSTOCK_H_PROD_CODE="PRODUCT_CODE";
	public static final String MYSTOCK_H_PROD_OPENING_STOCK="PRODUCT_OPENING_STOCK";
	public static final String MYSTOCK_H_PROD_CLOSING_STOCK="PRODUCT_CLOSING_STOCK";
	public static final String MYSTOCK_H_PROD_CLOSING_CB="PRODUCT_CLOSING_CB";
	public static final String MYSTOCK_H_PROD_CLOSING_LB="PRODUCT_CLOSING_LB";
	public static final String MYSTOCK_H_PROD_OPENING_CB="PRODUCT_OPENING_CB";
	public static final String MYSTOCK_H_PROD_OPENING_LB="PRODUCT_OPENING_LB";
	public static final String MYSTOCK_H_PROD_DISCOUNT="PRODUCT_DISCOUNT_AMOUNT";
	public static final String MYSTOCK_H_MRP_PRICE="PRODUCT_MRP_PRICE";
	public static final String MYSTOCK_H_WHOLESALE_PRICE="PRODUCT_WHOLESALE_PRICE";
	public static final String MYSTOCK_H_PROD_SERVICE_CHARGE="PRODUCT_SERVICE_CHARGE";
	public static final String MYSTOCK_H_PROD_TOTAL_AMOUNT="PRODUCT_TOTAL_AMOUNT";
	public static final String MYSTOCK_H_PROD_AMOUNT="PRODUCT_AMOUNT";
	public static final String MYSTOCK_H_PROD_DAMAGES="PRODUCT_DAMAGES";
	public static final String MYSTOCK_H_PROD_DATE_STAMP="PRODUCT_DATE";

// TABLE EXPENCES
	public static final String EXP_SERIAL_NO="EXP_S_NO";
	public static final String EXP_NAME="EXP_PRODUCT_NAME";
	public static final String EXP_TYPE="EXP_PRODUCT_TYPE";
	public static final String EXP_AMOUNT="EXP_PRODUCT_AMOUNT";
	public static final String EXP_SYNC="EXP_SYNC";
	public static final String EXP_DATE="EXP_PRODUCT_DATE";
	public static final String EXP_STIREID="EXP_PRODUCT_STOREID";

	// TABLE PRODUCT_LIST
	public static final String PRODUCT_SERIAL_NO="PRODUCT_S_NO";
	public static final String PRODUCT_ID="PRODUCT_ID";
	public static final String PRODUCT_NAME="PRODUCT_NAME";
	public static final String PRODUCT_CODE="PRODUCT_CODE";
	public static final String PRODUCT_QTY_BOX="PRODUCT_QTY_BOX";
	public static final String PRODUCT_COST_BOX="PRODUCT_COST_BOX";
	public static final String PRODUCT_MRP_EACH_PIECE="PRODUCT_MRP_EACH_PIECE";
	public static final String PRODUCT_TAX_BOX="PRODUCT_TAX_BOX";

	public static final String PRODUCT_DATE="PRODUCT_DATE";

	/*// Table DISCOUNTS
	public static final String DISCOUNTS_SERIAL_NO="PRODUCT_S_NO";
	public static final String DISCOUNTS_PRODUCT_ID="PRODUCT_ID";
	public static final String DISCOUNTS_PRODUCT_NAME="PRODUCT_NAME";
	public static final String DISCOUNTS_PRODUCT_TYPE="PRODUCT_TYPE";
	public static final String DISCOUNTS_PRODUCT_QTY="PRODUCT_QTY";
	public static final String DISCOUNTS_AMOUNT="PRODUCT_AMOUNT";
	public static final String DISCOUNTS_DATE="PRODUCT_DATE";
*/
	// Table Customers
	public static final String CUSTOMER_SERIAL_NO="Customer_S_NO";
	public static final String CUSTOMER_NAME="Customer_NAME";
	public static final String CUSTOMER_MOBILE="Customer_MOBILE";
	public static final String CUSTOMER_EMAIL="Customer_EMAIl";
	public static final String CUSTOMER_Address="Customer_Address";
	public static final String CUSTOMER_CREATED_DATE="Created_DATE";
	public static final String CUSTOMER_STOREID="StoreId";

	// Table Customers Discounts

	public static final String DISC_CUSTOMER_SERIAL_NO="Discounts_S_NO";
	public static final String DISC_CUSTOMER_NAME="Disc_Customer_NAME";
	public static final String DISC_CUSTOMER_MOBILE="Disc_Customer_MOBILE";
	public static final String DISC_ProductID="Disc_ProductId";
	public static final String DISC_ProductName="Disc_ProductName";
	public static final String DISC_ProductCode="Disc_ProductCode";
	public static final String DISC_Total_CB="Disc_Total_CB";
	public static final String DISC_Total_LB="Disc_Total_LB";
	public static final String DISC_MRP_PRICE="Disc_Mrp_Price";
	public static final String DISC_SYNC="Disc_Sync";
	public static final String DISC_Taken_Bottles="Disc_Taken_Bottles";
	public static final String DISC_Actual_Amt="Disc_Actual_Amt";
	public static final String DISC_Taken_Amt="Disc_Taken_Amt";
	public static final String DISC_Amt="Disc_Amt";
	public static final String DISC_Total_Amt="Disc_Total_Amt";
	public static final String DISC_createdDate="Disc_createdDate";
	public static final String DISC_StoreId="Disc_StoreId";


	// Table Damges

	public static final String DAMGES_SERIAL_NO="Damages_S_NO";
	public static final String DAMGES_PRODUCT_ID="Damages_productID";
	public static final String DAMGES_PRODUCT_NAME="Damages_ProductName";
	public static final String DAMGES_PRODUCT_QTY="Damages_ProductQty";
	public static final String DAMGES_PRODUCT_CB="Damages_ProductCB";
	public static final String DAMGES_PRODUCT_LB="Damages_ProductLB";
	public static final String DAMGES_PRODUCT_AMT="Damages_ProductAMT";
	public static final String DAMGES_PRODUCT_date="Damages_createdDate";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {


		String CREATE_VARIABLE_TABLE = "CREATE TABLE "
				+ TABLE_VARIABLE
				+ " ("
				+ VAR_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"

				+ VAR_D	+ " TEXT,"
				+ VAR_P 	+ " TEXT,"
				+ VAR_Q 	+ " TEXT,"
				+ VAR_N 	+ " TEXT,"
				+ VAR_L	+ " TEXT,"
				+ VAR_S	+ " TEXT,"
				+ VAR_U	+ " TEXT,"
				+ VAR_G	+ " TEXT,"


				+ VAR_Checked + " TEXT,"
				+ VAR_DATE + " DATE"
				+ ")";





		String CREATE_PRODUCT_LIST_TABLE = "CREATE TABLE "
				+ TABLE_PRODUCTLIST
				+ " (" 
				+ PRODUCT_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"

				+ PRODUCT_ID	+ " TEXT,"
				+ PRODUCT_NAME 	+ " TEXT,"
				+ PRODUCT_CODE 	+ " TEXT,"
				+ PRODUCT_QTY_BOX 	+ " TEXT,"
				+ PRODUCT_COST_BOX	+ " TEXT,"
				+ PRODUCT_MRP_EACH_PIECE	+ " TEXT,"
				+ PRODUCT_TAX_BOX	+ " TEXT,"

				+ PRODUCT_DATE + " DATE"
				+ ")";


		/*String CREATE_DAMAGES_TABLE = "CREATE TABLE "
				+ TABLE_DAMAGES
				+ " ("
				+ DAMGES_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"

				+ DAMGES_PRODUCT_NAME	+ " TEXT,"
				+ DAMGES_PRODUCT_QTY 	+ " TEXT,"
				+ DAMGES_PRODUCT_ID 	+ " TEXT,"
				+ DAMGES_PRODUCT_CB 	+ " TEXT,"
				+ DAMGES_PRODUCT_LB	+ " TEXT,"
				+ DAMGES_PRODUCT_date	+ " TEXT,"
				+ DAMGES_PRODUCT_AMT	+ " TEXT"
					+ ")";*/

		String CREATE_DISCOUNTS_TABLE = "CREATE TABLE "

				+ TABLE_DISCOUNTS
				+ " ("
				+ DISC_CUSTOMER_SERIAL_NO		+ " INTEGER PRIMARY KEY,"

				+ DISC_CUSTOMER_NAME	+ " TEXT,"
				+ DISC_CUSTOMER_MOBILE 	+ " TEXT,"
				+ DISC_ProductID 	+ " TEXT,"
				+ DISC_ProductName 	+ " TEXT,"
				+ DISC_ProductCode	+ " TEXT,"
				+ DISC_Total_CB	+ " TEXT,"
				+ DISC_Total_LB	+ " TEXT,"
				+ DISC_Actual_Amt	+ " TEXT,"
				+ DISC_Taken_Amt	+ " TEXT,"
				+ DISC_Amt	+ " TEXT,"
				+ DISC_MRP_PRICE + " TEXT,"
				+ DISC_Total_Amt + " TEXT,"
				+ DISC_Taken_Bottles + " TEXT,"
				+ DISC_SYNC + " TEXT,"
				+ DISC_createdDate + " DATE"
				+ ")";



		String CREATE_DISCOUNTS_H_TABLE = "CREATE TABLE "

				+ TABLE_H_DISCOUNTS
				+ " ("
				+ DISC_CUSTOMER_SERIAL_NO		+ " INTEGER PRIMARY KEY,"

				+ DISC_CUSTOMER_NAME	+ " TEXT,"
				+ DISC_CUSTOMER_MOBILE 	+ " TEXT,"
				+ DISC_ProductID 	+ " TEXT,"
				+ DISC_ProductName 	+ " TEXT,"
				+ DISC_ProductCode	+ " TEXT,"
				+ DISC_Total_CB	+ " TEXT,"
				+ DISC_Total_LB	+ " TEXT,"
				+ DISC_Actual_Amt	+ " TEXT,"
				+ DISC_Taken_Amt	+ " TEXT,"
				+ DISC_Amt	+ " TEXT,"
				+ DISC_MRP_PRICE + " TEXT,"
				+ DISC_Total_Amt + " TEXT,"
				+ DISC_SYNC + " TEXT,"
				+ DISC_Taken_Bottles + " TEXT,"
				+ DISC_createdDate + " DATE"
				+ ")";


		String CREATE_HOLYDAY_TABLE = "CREATE TABLE "

				+ TABLE_ISHOLYDAY
				+ " ("
				+ HOLYDAY_SERIAL_NO		+ " INTEGER PRIMARY KEY,"

				+ HOLYDAY_STOREID	+ " TEXT,"
				+ HOLYDAY_IS 	+ " TEXT,"
				+ HOLYDAY_DATE 	+ " TEXT"

				+ ")";

		String CREATE_LOGIN_TABLE = "CREATE TABLE "

				+ TABLE_Login
				+ " ("
				+ LOGIN_SERIAL_NO		+ " INTEGER PRIMARY KEY,"

				+ LOGIN_STOREID	+ " TEXT,"
				+ LOGIN_PASSWORD 	+ " TEXT"


				+ ")";


		String CREATE_Customer_TABLE = "CREATE TABLE "
				+ TABLE_CUSTOMERS
				+ " ("
				+ CUSTOMER_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"

				+ CUSTOMER_NAME	+ " TEXT,"
				+ CUSTOMER_MOBILE 	+ " TEXT,"
				+ CUSTOMER_Address 	+ " TEXT,"
				+ CUSTOMER_EMAIL 	+ " TEXT,"
				+ CUSTOMER_CREATED_DATE	+ " DATE"

				+ ")";


		String CREATE_MYSTOCK_TABLE = "CREATE TABLE "
				+ TABLE_MYSTOCK
				+ " ("
				+ MYSTOCK_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ MYSTOCK_PROD_ID 	+ " TEXT,"
				+ MYSTOCK_PROD_NAME 	+ " TEXT,"
				+ MYSTOCK_PROD_CODE	+ " TEXT,"
				+ MYSTOCK_PROD_QTY 	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_LB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_LB	+ " TEXT,"

				+ MYSTOCK_PROD_DAMAGES_CB	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_LB	+ " TEXT,"

				+ MYSTOCK_PROD_TOTALSALE_CB	+ " TEXT,"
				+ MYSTOCK_PROD_TOTALSALE_LB	+ " TEXT,"

				+ MYSTOCK_PROD_CLOSING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES	+ " TEXT,"
				+ MYSTOCK_PROD_SERVICE_CHARGE	+ " TEXT,"
				+ MYSTOCK_PROD_DISCOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_TOTAL_AMOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_AMOUNT	+ " TEXT,"
				+ MYSTOCK_MRP_PRICE	+ " TEXT,"
				+ MYSTOCK_WHOLESALE_PRICE	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_AMT	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_TOTAL+ " TEXT,"
				+ MYSTOCK_PROD_APPEAR+" TEXT,"
				+ MYSTOCK_SYNC_STATUS+" TEXT,"

				+ MYSTOCK_PROD_STOREID+" TEXT,"
				+ MYSTOCK_PROD_DATE_STAMP	+ " DATE"

			//	+ MYSTOCK_PROD_DATE_STAMP + " DATE"
				+ ")";


		// TABLE CLOSINGSTOCK HISTORY





		String CREATE_CS_TABLE = "CREATE TABLE "
				+ TABLE_CLOSING_STOCK_HISTORY
				+ " ("
				+ MYSTOCK_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ MYSTOCK_PROD_ID 	+ " TEXT,"
				+ MYSTOCK_PROD_NAME 	+ " TEXT,"
				+ MYSTOCK_PROD_CODE	+ " TEXT,"
				+ MYSTOCK_PROD_QTY 	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_LB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_LB	+ " TEXT,"

				+ MYSTOCK_PROD_DAMAGES_CB	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_LB	+ " TEXT,"

				+ MYSTOCK_PROD_TOTALSALE_CB	+ " TEXT,"
				+ MYSTOCK_PROD_TOTALSALE_LB	+ " TEXT,"

				+ MYSTOCK_PROD_CLOSING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES	+ " TEXT,"
				+ MYSTOCK_PROD_SERVICE_CHARGE	+ " TEXT,"
				+ MYSTOCK_PROD_DISCOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_TOTAL_AMOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_AMOUNT	+ " TEXT,"
				+ MYSTOCK_MRP_PRICE	+ " TEXT,"
				+ MYSTOCK_WHOLESALE_PRICE	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_AMT	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_TOTAL+ " TEXT,"
				+ MYSTOCK_PROD_APPEAR+" TEXT,"
				+ MYSTOCK_SYNC_STATUS+" TEXT,"
				+ MYSTOCK_PROD_STOREID+" TEXT,"
				+ MYSTOCK_PROD_DATE_STAMP	+ " DATE"

				//	+ MYSTOCK_PROD_DATE_STAMP + " DATE"
				+ ")";



		String CREATE_MYSTOCK_HISTORY_TABLE = "CREATE TABLE "
				+ TABLE_H_MYSTOCK
				+ " ("
				+ MYSTOCK_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ MYSTOCK_PROD_ID 	+ " TEXT,"
				+ MYSTOCK_PROD_NAME 	+ " TEXT,"
				+ MYSTOCK_PROD_CODE	+ " TEXT,"
				+ MYSTOCK_PROD_QTY 	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_LB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_LB	+ " TEXT,"

				+ MYSTOCK_PROD_DAMAGES_CB	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_LB	+ " TEXT,"

				+ MYSTOCK_PROD_TOTALSALE_CB	+ " TEXT,"
				+ MYSTOCK_PROD_TOTALSALE_LB	+ " TEXT,"

				+ MYSTOCK_PROD_CLOSING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_AMT	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_TOTAL+ " TEXT,"
				+ MYSTOCK_PROD_SERVICE_CHARGE	+ " TEXT,"
				+ MYSTOCK_PROD_DISCOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_TOTAL_AMOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_AMOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_APPEAR+" TEXT,"
				+ MYSTOCK_SYNC_STATUS+" TEXT,"
				+ MYSTOCK_MRP_PRICE	+ " TEXT,"
				+ MYSTOCK_PROD_STOREID+" TEXT,"
				+ MYSTOCK_WHOLESALE_PRICE	+ " TEXT,"
				+ MYSTOCK_PROD_DATE_STAMP	+ " DATE"

				//	+ MYSTOCK_PROD_DATE_STAMP + " DATE"
				+ ")";



	/*	String CREATE_INWORD_HISTORY_TABLE = "CREATE TABLE "
				+ TABLE_H_INWORD
				+ " ("
				+ MYSTOCK_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ MYSTOCK_PROD_ID 	+ " TEXT,"
				+ MYSTOCK_PROD_NAME 	+ " TEXT,"
				+ MYSTOCK_PROD_CODE	+ " TEXT,"
				+ MYSTOCK_PROD_QTY 	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_LB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_CB	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_LB	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_CB	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES_LB	+ " TEXT,"

				+ MYSTOCK_PROD_TOTALSALE_CB	+ " TEXT,"
				+ MYSTOCK_PROD_TOTALSALE_LB	+ " TEXT,"

				+ MYSTOCK_PROD_APPEAR+" TEXT,"
				+ MYSTOCK_SYNC_STATUS+" TEXT,"

				+ MYSTOCK_PROD_CLOSING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_DAMAGES	+ " TEXT,"
				+ MYSTOCK_PROD_SERVICE_CHARGE	+ " TEXT,"
				+ MYSTOCK_PROD_DISCOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_TOTAL_AMOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_AMOUNT	+ " TEXT,"
				+ MYSTOCK_MRP_PRICE	+ " TEXT,"
				+ MYSTOCK_WHOLESALE_PRICE	+ " TEXT,"
				+ MYSTOCK_PROD_DATE_STAMP	+ " DATE"

				//	+ MYSTOCK_PROD_DATE_STAMP + " DATE"
				+ ")";

*/



		String CREATE_EXPENCES_TABLE = "CREATE TABLE "
				+ TABLE_EXP
				+ " ("
				+ EXP_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ EXP_NAME	+ " TEXT,"
				+ EXP_TYPE 	+ " TEXT,"
				+ EXP_AMOUNT 	+ " TEXT,"
				+ EXP_SYNC + " TEXT,"
				+ EXP_DATE + " DATE"
				+ ")";


		db.execSQL(CREATE_DISCOUNTS_TABLE);

		db.execSQL(CREATE_EXPENCES_TABLE);
		db.execSQL(CREATE_PRODUCT_LIST_TABLE);
		db.execSQL(CREATE_MYSTOCK_TABLE);
		db.execSQL(CREATE_Customer_TABLE);

		//db.execSQL(CREATE_INWORD_HISTORY_TABLE);
		db.execSQL(CREATE_MYSTOCK_HISTORY_TABLE);
		//db.execSQL(CREATE_DAMAGES_TABLE);
		db.execSQL(CREATE_DISCOUNTS_H_TABLE);
		db.execSQL(CREATE_VARIABLE_TABLE);
		db.execSQL(CREATE_CS_TABLE);
		db.execSQL(CREATE_LOGIN_TABLE);
		db.execSQL(CREATE_HOLYDAY_TABLE);

	}

	

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTLIST);

		// Create tables again
		onCreate(db);
	}

	public void UpdateTable(String TableName,ContentValues cvUpdate,String Where_Condition)
	{
		/*SQLiteDatabase db = this.getWritableDatabase();
			 db.update(TableName, cvUpdate, Where_Condition, null);
			db.close();*/


		Log.e("TEST","FROM ::"+getClass().getName()+ "INS :"+Where_Condition+""+cvUpdate.get(DatabaseHandler.MYSTOCK_PROD_CLOSING_CB));

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			if (Where_Condition.equals("")) {
				db.update(TableName,cvUpdate, null, null);
			} else {
				db.update(TableName,cvUpdate, Where_Condition, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void insert(String tableName,ContentValues values) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		db.insert(tableName, null, values);
		db.close(); 
	//	return seq;
	}

	
	
	public Cursor retriveData(String query)
	{
		try
		{
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor curRetriveData = db.rawQuery(query,null);
			if(curRetriveData!=null)
				curRetriveData.moveToFirst();
			return curRetriveData;
		}
		catch (Exception e) 
		{  
			e.printStackTrace();
			return null;
		}
	}


	public void DeleteTable(String tablename,String whereClause) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			if (whereClause.equals("")) {
				db.delete(tablename, null, null);
			} else {
				db.delete(tablename, whereClause, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void DeleteTable(String TableName,String Where_Condition)
	{
		SQLiteDatabase db = this.getWritableDatabase();



		db.delete(TableName,  Where_Condition,null);



		db.close();

	}*/


}

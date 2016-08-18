package com.ptg.inventory.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ptg.inventory.activity.R;
import com.ptg.inventory.components.DiscountDialog;
import com.ptg.inventory.model.BottleDataDo;

import java.util.ArrayList;

public class DeliveryStockFragment extends Fragment {

    private LinearLayout llStock = null;
    private ListView lvBottles = null;
    private TextView bottleName = null;
    private EditText etOpeningCB = null;
    private EditText etOpeningLoose = null;
    private EditText etClosingCB = null;
    private EditText etClosingLoose = null;
    private TextView tvDiscount = null;
    private TextView tvDamage = null;
    private TextView tvAmt = null;
    private  LinearLayout llDiscount = null;
    private LinearLayout llDamage = null;
    private LinearLayout llExpenses = null;
    private ArrayList<BottleDataDo> alBottleDetails = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View deliveryStockView = inflater.inflate(R.layout.fragment_del_stock, container, false);
        initializeUIComponents(deliveryStockView);
        for(int i=0;i<5;i++){
            BottleDataDo objBottleDo = new BottleDataDo("Kinley",1000,10,2,8,1,10,3,2000);
            alBottleDetails.add(objBottleDo);
            addItem(llStock);
        }
        llExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DiscountDialog().show(getFragmentManager(),"Expenses Dialog");
            }
        });
        return deliveryStockView;
    }

    public void initializeUIComponents(View v){
        alBottleDetails = new ArrayList<BottleDataDo>();
        llStock = (LinearLayout)v.findViewById(R.id.ll_stock);
        llExpenses= (LinearLayout)v.findViewById(R.id.llExpenses);
    }
    public void addItem(LinearLayout ll) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.bottles_list_item, null);
        bottleName = (TextView)v.findViewById(R.id.tv_bottles);
        etOpeningCB = (EditText)v.findViewById(R.id.et_opening_cottonBox);
        etOpeningLoose = (EditText)v.findViewById(R.id.et_opening_looseBottle);
        etClosingCB =(EditText)v.findViewById(R.id.et_closing_cottonBox);
        etClosingLoose = (EditText)v.findViewById(R.id.et_closing_looseBottle);
        tvDiscount = (TextView)v.findViewById(R.id.tv_discount);
        llDiscount = (LinearLayout)v.findViewById(R.id.llDiscount);
        llDamage = (LinearLayout)v.findViewById(R.id.llDamagedItems);
        tvDamage = (TextView)v.findViewById(R.id.tv_damage);
        tvAmt = (TextView)v.findViewById(R.id.tv_amt);
        ll.addView(v);
        llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Hello", "Hello");
                // Show DialogFragment
                new DiscountDialog().show(getFragmentManager(),"Discounts Dialog");
            }
        });


        llDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("hi", "Hi");
               // Toast.makeText(getActivity(), "Hii", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
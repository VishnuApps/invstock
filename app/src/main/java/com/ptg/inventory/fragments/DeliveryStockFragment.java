package com.ptg.inventory.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ptg.inventory.Object.BottleDataDo;
import com.ptg.inventory.activity.R;
import com.ptg.inventory.adapter.BottleDetailsAdapter;
import com.ptg.inventory.model.ItemBean;

import java.util.ArrayList;
import java.util.List;


public class DeliveryStockFragment extends android.app.Fragment {

    private LinearLayout llStock = null;
    private ListView lvBottles = null;
    private EditText etOpeningCottonBox = null;
    private EditText etOpeningLooseBottle = null;
    private EditText etClosingCottonBox = null;
    private EditText etClosingLooseBottle = null;
    private TextView tvDiscount = null;
    private TextView tvDamagedItems = null;
    private TextView tvTotalAmt = null;
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
        return deliveryStockView;
    }

    public void initializeUIComponents(View v){
        alBottleDetails = new ArrayList<BottleDataDo>();
        llStock = (LinearLayout)v.findViewById(R.id.ll_stock);
    }
    public void addItem(LinearLayout ll) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.bottles_list_item, null);
        EditText etOpenCB = (EditText)v.findViewById(R.id.et_opening_cottonBox);
        EditText etOpenLB = (EditText)v.findViewById(R.id.et_opening_looseBottle);
        EditText etCloseCB = (EditText)v.findViewById(R.id.et_closing_cottonBox);
        EditText etCloseLB = (EditText)v.findViewById(R.id.et_closing_looseBottle);
        ll.addView(v);
    }
}
package com.ptg.inventory.components;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ptg.inventory.activity.R;
import com.ptg.inventory.model.BottleDataDo;
import com.ptg.inventory.model.BottleDataObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viddy on 8/15/2016.
 */
public class DiscountDialog extends DialogFragment {

    private TextView tvItem = null;
    private EditText etCB =null;
    private EditText etLB = null;
    private EditText etAmt = null;
    private ImageView imgIncrease = null;
    private ImageView imgDecrease = null;
    private LinearLayout llAddItem = null;
    private int discount_itemCount = 0;
    private TextView tvTotalAmount = null;
    private ArrayList<BottleDataObject> al_DummyBottlesDo = null;
    private ArrayList<BottleDataDo> al_discItemInfo = null;
    private ArrayAdapter<String> dataAdapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View discountView = inflater.inflate(R.layout.discount_dialog, container,false);
     //   getDialog().setTitle("Discount Dialog");
        initializeUIComponents(discountView);
        addItem(llAddItem);

        if(discount_itemCount>1){
            Log.v("111","item count >1");
             imgDecrease.setClickable(false);
        }else{
            Log.v("111","item count 1");
            imgDecrease.setClickable(true);
        }
            //Increase Image clicked
        imgIncrease.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                increaseItemCount();
            }
        });

        //Decrease Image clicked
        imgDecrease.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                reduceItemCount();
            }
        });
        return discountView;
    }

    //increase items count
    public void increaseItemCount(){

        if(al_DummyBottlesDo != null && al_DummyBottlesDo.size()>0){
            boolean isEmpty= false;
            al_discItemInfo = new ArrayList<BottleDataDo>();
            for(int i=0;i<al_DummyBottlesDo.size();i++){
                      BottleDataObject dao = al_DummyBottlesDo.get(i);
                      String itemIn_ml = dao.tvItem.getText().toString().trim();
                      String cb = dao.etCB.getText().toString().trim();
                      String lb = dao.etLB.getText().toString().trim();
                      String amt = dao.etAmt.getText().toString().trim();
                      Boolean isAdded = dao.isAdded;
                     if((!cb.equalsIgnoreCase("") || !lb.equalsIgnoreCase("")) && !amt.equalsIgnoreCase("")){
                         BottleDataDo bDataDo = new BottleDataDo();

                         if(!cb.equalsIgnoreCase(""))
                         bDataDo.openingStockCottonBox = Integer.parseInt(cb);

                         if(!lb.equalsIgnoreCase(""))
                         bDataDo.openingStockLooseBottle = Integer.parseInt(lb);

                         if(!amt.equalsIgnoreCase(""))
                         bDataDo.amount = Integer.parseInt(amt);

                         bDataDo.qty = Integer.parseInt(itemIn_ml);
                         al_discItemInfo.add(bDataDo);
                         isEmpty = false;
                    }else{
                         isEmpty = true;
                         break;
                     }
            }
            if(isEmpty == true){
                Toast.makeText(getActivity(),"Please Enter All fields",Toast.LENGTH_LONG).show();
            }else{
                 if(al_discItemInfo != null && al_discItemInfo.size()>0){
                     int totalDiscountCount = 0;
                     for(int i=0;i<al_discItemInfo.size();i++){
                         BottleDataDo bDO = al_discItemInfo.get(i);
                         totalDiscountCount = totalDiscountCount + bDO.amount;
                     }
                     tvTotalAmount.setText(""+totalDiscountCount);
                     addItem(llAddItem);
                 }
            }
        }
    }

    //decrease items count
    public void reduceItemCount(){

    if(discount_itemCount>1){
                imgDecrease.setClickable(true);
                if(al_DummyBottlesDo != null && al_DummyBottlesDo.size() >1){
                    al_DummyBottlesDo.remove(al_DummyBottlesDo.size() - 1);
                    llAddItem.removeViewAt(discount_itemCount - 1);
                    llAddItem.refreshDrawableState();
                Log.v("remove", "removed Item size===" + al_DummyBottlesDo.size());
                int totalDiscountCountAfterMinus = 0;
                for(int i=0;i<al_DummyBottlesDo.size();i++){
                    BottleDataObject dao = al_DummyBottlesDo.get(i);
                    String amt = dao.etAmt.getText().toString().trim();
                    totalDiscountCountAfterMinus = totalDiscountCountAfterMinus+Integer.parseInt(amt);
                }
                tvTotalAmount.setText("" + totalDiscountCountAfterMinus);

            }
    }else{
                imgDecrease.setClickable(false);
        }
    }

    public void initializeUIComponents(View v){
        al_DummyBottlesDo =new ArrayList<BottleDataObject>();
        llAddItem = (LinearLayout)v.findViewById(R.id.ll_discountDialogItem);
        imgIncrease = (ImageView)v.findViewById(R.id.img_add);
        imgDecrease = (ImageView)v.findViewById(R.id.img_delete);
        tvTotalAmount = (TextView)v.findViewById(R.id.tv_dialog_total);
    }
    //add items dynamically
    public void addItem(LinearLayout ll){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.discount_dialog_listitem, null);
        tvItem = (TextView)v.findViewById(R.id.tvItem);
        etCB = (EditText)v.findViewById(R.id.et_dialog_cb);
        etLB = (EditText)v.findViewById(R.id.et_dialog_lb);
        etAmt = (EditText)v.findViewById(R.id.et_dialog_amt);
        BottleDataObject bottlesDAO = new BottleDataObject(tvItem,etCB,etLB,etAmt,true);
        al_DummyBottlesDo.add(bottlesDAO);
        if(discount_itemCount>1){
            imgDecrease.setClickable(true);
        }else{
            imgDecrease.setClickable(false);
        }
        Log.v("disc","disc item count=="+discount_itemCount);
        discount_itemCount++;
        ll.addView(v);
    }
}

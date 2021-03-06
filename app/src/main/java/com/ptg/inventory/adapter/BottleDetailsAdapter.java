package com.ptg.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptg.inventory.activity.R;
import com.ptg.inventory.model.BottleDataDo;

import java.util.ArrayList;

/**
 * Created by viddy on 8/13/2016.
 */
public class BottleDetailsAdapter extends BaseAdapter {

    private ArrayList<BottleDataDo> _al = null;
    private Context context;
    public BottleDetailsAdapter(Context context, ArrayList<BottleDataDo> alBottles){
        this.context = context;
        this._al = alBottles;
    }
    @Override
    public int getCount() {
        if(_al.size()>0){
            return _al.size();
        }else
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(_al.size()>0){
            return _al.get(position);
        }else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        if(_al.size()>0){
            return _al.size();
        }else
            return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.bottles_list_item, null);
        TextView bottleName = (TextView)v.findViewById(R.id.tv_bottles);
    /*    EditText etOpeningCB = (EditText)v.findViewById(R.id.et_opening_cottonBox);
        EditText etOpeningLoose = (EditText)v.findViewById(R.id.et_opening_looseBottle);
        EditText etClosingCB =(EditText)v.findViewById(R.id.et_closing_cottonBox);
        EditText etClosingLoose = (EditText)v.findViewById(R.id.et_closing_looseBottle);
        TextView tvDiscount = (TextView)v.findViewById(R.id.tv_discount);
        LinearLayout llDiscount = (LinearLayout)v.findViewById(R.id.llDiscount);
        LinearLayout llDamage = (LinearLayout)v.findViewById(R.id.llDamagedItems);
        TextView tvDamage = (TextView)v.findViewById(R.id.tv_damage);
        TextView tvAmt = (TextView)v.findViewById(R.id.tv_amt);*/
      /*  llDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Hello", "Hello");
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();

            }
        });*/


      /*  llDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("hi", "Hi");
                Toast.makeText(context, "Hii", Toast.LENGTH_SHORT).show();

            }
        });*/
        return v;
    }
}

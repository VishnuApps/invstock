package com.ptg.inventory.model;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by viddy on 8/17/2016.
 */
public class BottleDataObject implements Serializable {

    public TextView tvItem = null;
    public EditText etCB = null;
    public EditText etLB = null;
    public EditText etAmt = null;
    public boolean isAdded= false;

    public BottleDataObject(TextView tvItem,EditText etCB, EditText etLB, EditText etAmt,boolean isAdded){
        this.tvItem = tvItem;
        this.etCB = etCB;
        this.etLB = etLB;
        this.etAmt = etAmt;
        this.isAdded = isAdded;
    }
}

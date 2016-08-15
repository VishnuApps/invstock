package com.ptg.inventory.components;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.ptg.inventory.activity.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by viddy on 8/15/2016.
 */
public class DiscountDialog extends DialogFragment {

    private Spinner spinner = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.discount_dialog, container,false);
        getDialog().setTitle("Discount Dialog");
        List<String> list = new ArrayList<String>();
        spinner = (Spinner)rootView.findViewById(R.id.sd_spinner);
        list.add("1000");
        list.add("750");
        list.add("500");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        return rootView;
    }
}

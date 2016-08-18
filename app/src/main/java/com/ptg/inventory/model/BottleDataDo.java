package com.ptg.inventory.model;

import java.io.Serializable;

/**
 * Created by viddy on 8/13/2016.
 */
public class BottleDataDo implements Serializable {

    public String item = "";
    public int qty = -1;
    public int openingStockCottonBox = -1;
    public int openingStockLooseBottle = -1;
    public int closingStockCottonBox = -1;
    public int closingStockLooseBottle = -1;
    public int discount = -1;
    public int damage = -1;
    public int amount = -1;

    public BottleDataDo(){

    }
    public BottleDataDo(String item, int qty, int openingStockCottonBox, int openingStockLooseBottle, int closingStockCottonBox, int closingStockLooseBottle, int discount, int damage, int amount){
        this.item = item;
        this.qty = qty;
        this.openingStockCottonBox = openingStockCottonBox;
        this.openingStockLooseBottle = openingStockLooseBottle;
        this.closingStockCottonBox = closingStockCottonBox;
        this.closingStockLooseBottle= closingStockLooseBottle;
        this.discount = discount;
        this.damage = damage;
        this.amount= amount;

    }
}

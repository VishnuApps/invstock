package com.ptg.inventory.Object;

import java.io.Serializable;

/**
 * Created by viddy on 8/13/2016.
 */
public class BottleDataDo implements Serializable {

    public int openingStockCottonBox = -1;
    public int openingStockLooseBottle = -1;
    public int closingStockCottonBox = -1;
    public int closingStockLooseBottle = -1;
    public int discount = -1;
    public int damage = -1;
    public int amount = -1;

    public BottleDataDo(int openingStockCottonBox, int openingStockLooseBottle, int closingStockCottonBox, int closingStockLooseBottle, int discount, int damage, int amount){
        this.openingStockCottonBox = openingStockCottonBox;
        this.openingStockLooseBottle = openingStockLooseBottle;
        this.closingStockCottonBox = closingStockCottonBox;
        this.closingStockLooseBottle= closingStockLooseBottle;
        this.discount = discount;
        this.damage = damage;
        this.amount= amount;

    }
}

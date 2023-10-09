package com.neosoft.matching.comparator;

import com.neosoft.matching.model.SellOrder;

import java.util.Comparator;

public class SellPriceComparator implements Comparator<SellOrder> {
    @Override
    public int compare(SellOrder s1, SellOrder s2) {
        if (s1.getPrice()< s2.getPrice())
            return -1;
        else if (s1.getPrice() > s2.getPrice())
            return 1;
        else
            return 0;
    }
}

package com.neosoft.matching.comparator;

import com.neosoft.matching.model.BuyOrder;

import java.util.Comparator;

public class BuyPriceComparator implements Comparator<BuyOrder> {
    @Override
    public int compare(BuyOrder b1, BuyOrder b2) {
        if (b1.getPrice()>b2.getPrice())
            return -1;
        else if (b1.getPrice()<b2.getPrice())
            return 1;
        else
            return 0;
    }
}

package com.telia.cope.gateway.proxies;

import java.util.ArrayList;
import java.util.List;

public class OrderInfo 
{
    private List<BtcOrder> btcOrderList;
    
    public List<BtcOrder> getBtcOrderList() {
        if(btcOrderList == null) {
        	btcOrderList = new ArrayList<>();
        }
        return btcOrderList;
    }
 
    public void setBtcOrderList(List<BtcOrder> btcOrderList) {
        this.btcOrderList = btcOrderList;
    }
}

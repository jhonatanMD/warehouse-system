package com.ws.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Util {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static Map<Long , Long> saleMoth = new HashMap<>();

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(ONE_HUNDRED);
    }


    public static Map<Long,Long> getSaleMoth(){

        saleMoth.put(1L,0L);
        saleMoth.put(2L,0L);
        saleMoth.put(3L,0L);
        saleMoth.put(4L,0L);
        saleMoth.put(5L,0L);
        saleMoth.put(6L,0L);
        saleMoth.put(7L,0L);
        saleMoth.put(8L,0L);
        saleMoth.put(9L,0L);
        saleMoth.put(10L,0L);
        saleMoth.put(11L,0L);
        saleMoth.put(12L,0L);

        return saleMoth;
    }
}

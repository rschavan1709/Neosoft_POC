package com.neosoft.matching.consumer;

import com.neosoft.matching.comparator.BuyPriceComparator;
import com.neosoft.matching.comparator.SellPriceComparator;
import com.neosoft.matching.model.BuyOrder;
import com.neosoft.matching.model.SellOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class MatchingConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(MatchingConsumer.class);

    private List<BuyOrder> buyOrders=new LinkedList<>();

    private List<SellOrder> sellOrders=new LinkedList<>();

    @RabbitListener(queues = {"${rabbitmq.buy.queue.name}"})
    public void consumeBuyOrder(BuyOrder buyOrder){
        LOGGER.info(String.format("Received Buy Order -> %s",buyOrder.toString()));
        buyOrders.add(buyOrder);
        Collections.sort(buyOrders,new BuyPriceComparator());
        LOGGER.info(buyOrders.toString());
    }

    @RabbitListener(queues = {"${rabbitmq.sell.queue.name}"})
    public void consumeSellOrder(SellOrder sellOrder){
        LOGGER.info(String.format("Received Sell Order -> %s",sellOrder.toString()));
        sellOrders.add(sellOrder);
        Collections.sort(sellOrders,new SellPriceComparator());
        LOGGER.info(sellOrders.toString());
    }
}

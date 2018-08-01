package ru.tsystem.ordinaalena.stand.jms;

import org.apache.log4j.Logger;
import ru.tsystem.ordinaalena.stand.bean.ejb.TopProducts;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageListenerImpl implements MessageListener {
    private static final Logger logger=Logger.getLogger(MessageListenerImpl.class);

    private TopProducts topProducts;
    @Override
    public void onMessage(Message message) {
        logger.info("Message has received from JMS server.");
        topProducts.updateTopProducts();

    }

    public void setTopProducts(TopProducts topProducts) {
        this.topProducts = topProducts;
    }
}

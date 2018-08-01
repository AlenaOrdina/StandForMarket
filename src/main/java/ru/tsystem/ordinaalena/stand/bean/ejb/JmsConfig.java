package ru.tsystem.ordinaalena.stand.bean.ejb;

import org.apache.log4j.Logger;
import ru.tsystem.ordinaalena.stand.jms.MessageListenerImpl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Singleton
@Startup
public class JmsConfig {
    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;

    @EJB
    private TopProducts topProducts;
    private static final Logger logger = Logger.getLogger(JmsConfig.class);


    @PostConstruct
    private void startConnection() throws NamingException,JMSException{
        Properties  properties=new Properties();
        properties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url", "tcp://localhost:61616");
        properties.put("queue.js-queue", "advertising.stand");
        properties.put("connectionFactoryNames", "queueCF");

        Context context=new InitialContext(properties);
        QueueConnectionFactory connectionFactory=(QueueConnectionFactory) context.lookup("queueCF");
        Queue queue=(Queue)context.lookup("js-queue");
        connection=connectionFactory.createQueueConnection();
        connection.start();

        session=connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);

        receiver=session.createReceiver(queue);
        MessageListenerImpl listener=new MessageListenerImpl();
        listener.setTopProducts(topProducts);
        receiver.setMessageListener(listener);
        logger.info("Connection with JMS server has set");

    }

    @PreDestroy
    private void closeConnection(){
        try{
            receiver.close();
            session.close();
            connection.close();

            logger.info("All connections with JMS server was successfully closed.");
        }
        catch(JMSException e){
            logger.error("Something was wrong during closing connection with JMS server.",e);
        }
    }
}

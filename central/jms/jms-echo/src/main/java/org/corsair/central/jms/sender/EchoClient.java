package org.corsair.central.jms.sender;

import javax.jms.*;

/**
 * Created by kvi on 27-01-2016.
 */
public class EchoClient implements MessageListener {

    private ConnectionFactory pooledConnectionFactory = null;

    private String topic = null;

    private String id = null;

    private Connection connection = null;

    private Session session = null;

    public void init() throws JMSException {
        connection = getPooledConnectionFactory().createConnection();
        connection.setClientID(getId());
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(getTopic());
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(this);
        connection.start();
    }

    public void destroy() throws JMSException {
        session.close();
        connection.stop();
    }

    //    http://stackoverflow.com/questions/9291279/sample-jms-example-using-active-mq
    public void onMessage(Message message) {
        System.out.println(message.toString());
//        if (message instanceof TextMessage) {
//            try {
//                System.out.println("========>>>>JMS Message: " + ((TextMessage)message).getText());
//                System.out.println("========>>>>JMS Message Type: " + message.getJMSType());
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public ConnectionFactory getPooledConnectionFactory() {
        return pooledConnectionFactory;
    }

    public void setPooledConnectionFactory(ConnectionFactory pooledConnectionFactory) {
        this.pooledConnectionFactory = pooledConnectionFactory;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

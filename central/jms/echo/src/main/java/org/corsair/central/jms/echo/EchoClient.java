package org.corsair.central.jms.echo;

import javax.jms.*;

/**
 * Created by kvi on 27-01-2016.
 */
public class EchoClient implements MessageListener{

    private ConnectionFactory pooledConnectionFactory = null;

    private Connection connection = null;

    public void init() throws JMSException {
        connection = pooledConnectionFactory.createConnection();
        connection.setClientID("KVI");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("TestTopic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(this);
        connection.start();
    }

    public void destroy() throws JMSException {
        connection.stop();
    }

    //    http://stackoverflow.com/questions/9291279/sample-jms-example-using-active-mq
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("========>>>>JMS Message: " + ((TextMessage)message).getText());
                System.out.println("========>>>>JMS Message Type: " + message.getJMSType());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionFactory getPooledConnectionFactory() {
        return pooledConnectionFactory;
    }

    public void setPooledConnectionFactory(ConnectionFactory pooledConnectionFactory) {
        this.pooledConnectionFactory = pooledConnectionFactory;
    }
}

package org.corsair.central.jms.sender;

import javax.jms.*;

/**
 * Created by kvi on 27-01-2016.
 */
public class EchoClient {

    private ConnectionFactory pooledConnectionFactory = null;

    private String topic = null;

    private String id = null;

    private Connection connection = null;

    private Session session = null;

    public void init() throws JMSException {
        System.out.println("Creating connection");
        connection = getPooledConnectionFactory().createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(getTopic());
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                System.out.println(message.toString());
            }
        });
        connection.start();
    }

    public void destroy() throws JMSException {
        System.out.println("Closing connection");
        connection.close();
    }

    public ConnectionFactory getPooledConnectionFactory() {
        return pooledConnectionFactory;
    }

    public void setPooledConnectionFactory(ConnectionFactory pooledConnectionFactory) {
        try {
            this.pooledConnectionFactory = pooledConnectionFactory;
            connection = pooledConnectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

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

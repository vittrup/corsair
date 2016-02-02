package org.corsair.central.jms.sender;

import javax.jms.*;

/**
 * Created by kvi on 27-01-2016.
 */
public class SenderClient {

    private ConnectionFactory pooledConnectionFactory = null;

    private String topic = null;

    private String id = null;

    private Connection connection = null;

    private Session session = null;

    public void init() throws JMSException {
//        connection = getPooledConnectionFactory().createConnection();
//        connection.setClientID(getId());
//        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Topic topic = session.createTopic(getTopic());
//        MessageProducer producer = session.createProducer(topic);
//        TextMessage msg = session.createTextMessage();
//        msg.setText("Hello JMS World");
//        producer.send(msg);

    }

    public void destroy() throws JMSException {
        session.close();
        connection.stop();
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

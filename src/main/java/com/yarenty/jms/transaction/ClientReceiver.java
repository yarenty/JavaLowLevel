package com.yarenty.jms.transaction;

import javax.jms.*;

public class ClientReceiver extends Receiver {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println("Starting...");
        new ClientReceiver().doAll();
        System.out.println("Ending...");
    }

    protected QueueSession createQueueSession(final QueueConnection aQC)
            throws JMSException {
        return aQC.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
    }

    protected void processMessage(final Message aMessage, final QueueSession aQS)
            throws JMSException {
        if (aMessage instanceof ObjectMessage) {
            final ObjectMessage aOM = (ObjectMessage) aMessage;
            System.out.print(aOM.getObject() + " ");
            final Integer i = (Integer) aOM.getObject();
            final int ii = i.intValue();
            if (ii == 5) {
                aOM.acknowledge();
            }
        }
    }

}

package com.yarenty.jms.transaction;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;


public class AutoReceiver extends Receiver {
    public static void main(final String[] args) {
        System.out.println("Starting...");
        new AutoReceiver().doAll();
        System.out.println("Ending...");
    }

    protected QueueSession createQueueSession(
            final QueueConnection aQC
    ) throws JMSException {
        return aQC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}
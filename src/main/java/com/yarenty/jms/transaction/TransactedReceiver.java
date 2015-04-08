package com.yarenty.jms.transaction;

import javax.jms.*;

public class TransactedReceiver extends Receiver {
    public static void main(final String[] args) {
        System.out.println("Starting...");
        new TransactedReceiver().doAll();
        System.out.println("Ending...");
    }

    protected QueueSession createQueueSession(
            final QueueConnection aQC
    ) throws JMSException {
        return aQC.createQueueSession(true, -1);
    }

    protected void processMessage(final Message aMessage, final QueueSession aQS)
            throws JMSException {
        if (aMessage instanceof ObjectMessage) {
            final ObjectMessage aOM = (ObjectMessage) aMessage;
            System.out.print(aOM.getObject() + " ");
            final Integer i = (Integer) aOM.getObject();
            final int ii = i.intValue();
            if (ii == 5) {
                aQS.commit();
            } else if (ii == 9) {
                aQS.rollback();
            }
        }
    }
}
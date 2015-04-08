package com.yarenty.jms.transaction;

import javax.jms.*;
import javax.naming.InitialContext;


public class Sender {
    public static void main(final String[] args) {
        System.out.println("Starting...");
        QueueConnectionFactory aQCF = null;
        QueueConnection aQC = null;
        QueueSession aQS = null;
        QueueSender aSender = null;
        try {
            final InitialContext aIC = new InitialContext(Resource.getResources());
            aQCF = (QueueConnectionFactory) aIC.lookup(
                    iConstants.FACTORY_NAME
            );
            aQC = aQCF.createQueueConnection();
            aQS = aQC.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            final Queue aQueue = (Queue) aIC.lookup(iConstants.QUEUE_NAME);
            aSender = aQS.createSender(aQueue);
            aQC.start();
            for (int i = 0; i < 10; i++) {
                aSender.send(aQS.createObjectMessage(new Integer(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (aSender != null) {
                    aSender.close();
                }
                if (aQS != null) {
                    aQS.close();
                }
                if (aQC != null) {
                    aQC.stop();
                    aQC.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ending...");
    }
}
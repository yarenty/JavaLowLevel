package com.yarenty.jms.transaction;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.InputStreamReader;


public abstract class Receiver {
    protected void doAll() {
        QueueConnectionFactory aQCF = null;
        QueueConnection aQC = null;
        QueueSession aQS = null;
        QueueReceiver aQR = null;
        try {
            final InitialContext aIC = new InitialContext(Resource.getResources());
            aQCF = (QueueConnectionFactory) aIC.lookup(
                    iConstants.FACTORY_NAME
            );
            aQC = aQCF.createQueueConnection();
            aQS = createQueueSession(aQC);
            final QueueSession aQS1 = aQS;

            listAllQueues(aIC);

            final Queue aQueue = (Queue) aIC.lookup(iConstants.QUEUE_NAME);
            aQR = aQS.createReceiver(aQueue);
            final MessageListener aML = new MessageListener() {
                public void onMessage(Message aMessage) {
                    try {
                        processMessage(aMessage, aQS1);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };
            aQR.setMessageListener(aML);
            aQC.start();
            final InputStreamReader aISR = new InputStreamReader(System.in);
            char aAnswer = ' ';
            do {
                aAnswer = (char) aISR.read();
                if ((aAnswer == 'r') || (aAnswer == 'R')) {
                    aQS.recover();
                }
            } while ((aAnswer != 'q') && (aAnswer != 'Q'));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (aQR != null) {
                    aQR.close();
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
    }

    private void listAllQueues(final InitialContext aIC) throws NamingException {
//		while( aIC.list("").hasMoreElements()) {
//			Object o = aIC.lookup("").nextElement();	 
//			System.out.println(o.toString());
//		}
    }


    protected void processMessage(final Message aMessage, final QueueSession aQS) throws JMSException {
        if (aMessage instanceof ObjectMessage) {
            final ObjectMessage aOM = (ObjectMessage) aMessage;
            System.out.print(aOM.getObject() + " ");
        }
    }

    protected abstract QueueSession createQueueSession(
            final QueueConnection aQC
    ) throws JMSException;
}
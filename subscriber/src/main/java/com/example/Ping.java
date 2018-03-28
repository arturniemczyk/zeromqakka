package com.example;

import akka.actor.UntypedActor;
import akka.zeromq.*;

public class Ping extends UntypedActor {
    @Override
    public void onReceive(Object message) {

        if (message instanceof ZMQMessage) {

            ZMQMessage m = (ZMQMessage) message;

            System.out.println(m.frame(0).utf8String());

        } else {
            unhandled(message);
        }
    }
}
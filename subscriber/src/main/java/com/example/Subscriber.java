package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.ByteString;
import akka.zeromq.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zeromq.ZMQ;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class Subscriber implements CommandLineRunner
{

    public static void main(String[] args) {
        SpringApplication.run(Subscriber.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        ActorSystem system = ActorSystem.create();

        ActorRef listener = system.actorOf(Props.create(Ping.class));

        ZeroMQExtension.get(system).newSubSocket(
                new Connect("tcp://publisher:1233"),
                new Listener(listener), Subscribe.all());

    }
}

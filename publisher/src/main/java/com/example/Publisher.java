package com.example;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.ByteString;
import akka.zeromq.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Publisher implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(Publisher.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {

        ActorSystem system = ActorSystem.create();

        ActorRef pubSocket = ZeroMQExtension.get(system).newPubSocket(
                new Bind("tcp://*:1233"));


        while (!Thread.currentThread ().isInterrupted ()) {

            pubSocket.tell(ZMQMessage.withFrames(ByteString.fromString("----- Ping " + LocalDateTime.now())), ActorRef.noSender());

            Thread.sleep(5000);

        }


    }
}
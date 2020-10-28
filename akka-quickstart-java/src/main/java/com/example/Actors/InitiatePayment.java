package com.example.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.messages.Payment;

import java.util.ArrayList;
import java.util.List;

public class InitiatePayment extends AbstractBehavior<String> {

    private final ActorRef<Payment> initiatePayment;

    public static Behavior<String> create() {
        return Behaviors.setup(InitiatePayment::new);
    }

    private InitiatePayment(ActorContext<String> context) {
        super(context);
        initiatePayment = context.spawn(TransactionIdGeneration.create(), "initiatePayment");
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder().onMessage(String.class, this::handle).build();
    }

    private Behavior<String> handle(String messgae) {
        List<Payment> payments = new ArrayList<>();
        getContext().getLog().info("Initializing.......");

        payments.add(new Payment(2000.0, "V1", "V2"));
        payments.add(new Payment(20.0, "V2", "V3"));
        payments.add(new Payment(200.0, "V3", "V4"));
        payments.add(new Payment(250.0, "V4", "V5"));
        payments.add(new Payment(20000.0, "V5", "V6"));

        payments.forEach(initiatePayment::tell);
        return this;
    }
}

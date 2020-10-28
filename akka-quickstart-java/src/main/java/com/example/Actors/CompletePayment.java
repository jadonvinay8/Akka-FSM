package com.example.Actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.messages.TransformedPayment;


public class CompletePayment extends AbstractBehavior<TransformedPayment> {

    public static Behavior<TransformedPayment> create() {
        return Behaviors.setup(CompletePayment::new);
    }

    private CompletePayment(ActorContext<TransformedPayment> context) {
        super(context);
    }

    @Override
    public Receive<TransformedPayment> createReceive() {
        return newReceiveBuilder().onMessage(TransformedPayment.class, this::handle).build();
    }

    private Behavior<TransformedPayment> handle(TransformedPayment message) {
        getContext().getLog().info("Payment Completed, persisting data in the DB: " + message );
        return this;
    }

}

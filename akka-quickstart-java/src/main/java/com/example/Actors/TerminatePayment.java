package com.example.Actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.messages.ErrorMessages;


public class TerminatePayment extends AbstractBehavior<ErrorMessages> {

    public static Behavior<ErrorMessages> create() {
        return Behaviors.setup(TerminatePayment::new);
    }

    private TerminatePayment(ActorContext<ErrorMessages> context) {
        super(context);
    }

    @Override
    public Receive<ErrorMessages> createReceive() {
        return newReceiveBuilder().onMessage(ErrorMessages.class, this::handlePayment).build();
    }

    public Behavior<ErrorMessages> handlePayment(ErrorMessages message) {
        getContext().getLog().info("Sending negative acknowledgement for failed payment " + message);
        return this;
    }

}

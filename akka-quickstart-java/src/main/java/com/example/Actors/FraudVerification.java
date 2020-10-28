package com.example.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import com.example.messages.ErrorMessages;
import com.example.messages.TransformedPayment;


public class FraudVerification extends AbstractBehavior<TransformedPayment> {

    public static Behavior<TransformedPayment> create() {
        return Behaviors.setup(FraudVerification::new);
    }

    private final ActorRef<TransformedPayment> processPayment;
    private final ActorRef<ErrorMessages> terminatePayment;

    private FraudVerification(ActorContext<TransformedPayment> context) {
        super(context);
        processPayment = context.spawn(ProcessPayment.create(), "processPayment");
        terminatePayment = context.spawn(TerminatePayment.create(), "terminate");
    }

    @Override
    public Receive<TransformedPayment> createReceive() {
        return newReceiveBuilder().onMessage(TransformedPayment.class, this::handle).build();
    }

    private Behavior<TransformedPayment> handle(TransformedPayment message) {
        double random = Math.random();
        if (random < 0.9) {
            getContext().getLog().info("Verification done...proceeding to process request: " + message);
            processPayment.tell(message);
        } else {
            getContext().getLog().error("Fraud verification failed...terminating payment: " + message);
            terminatePayment.tell(new ErrorMessages(message, "Fraud Verification Failed"));
        }
        return this;
    }

}

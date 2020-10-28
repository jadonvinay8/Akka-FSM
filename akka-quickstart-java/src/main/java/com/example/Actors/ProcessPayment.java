package com.example.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.messages.ErrorMessages;
import com.example.messages.TransformedPayment;


public class ProcessPayment extends AbstractBehavior<TransformedPayment> {

    private final ActorRef<TransformedPayment> completePayment;
    private final ActorRef<ErrorMessages> failedPayment;

    public static Behavior<TransformedPayment> create() {
        return Behaviors.setup(ProcessPayment::new);
    }

    private ProcessPayment(ActorContext<TransformedPayment> context) {
        super(context);
        completePayment = context.spawn(CompletePayment.create(), "completePayment");
        failedPayment = context.spawn(TerminatePayment.create(), "terminatePayment");
    }

    @Override
    public Receive<TransformedPayment> createReceive() {
        return newReceiveBuilder().onMessage(TransformedPayment.class, this::handle).build();
    }

    private Behavior<TransformedPayment> handle(TransformedPayment message) {
        getContext().getLog().info("processing payment...." + message.from + " -> " + message.to + "  of " + message.amount);
        double random = Math.random();
        if (random < 0.9) {
            getContext().getLog().info("Transaction Done for: " + message + " proceeding ahead..");
            completePayment.tell(message);
        } else {
            getContext().getLog().error("Transaction failed...terminating payment: " + message);
            failedPayment.tell(new ErrorMessages(message, "Transaction failed"));
        }
        return this;
    }

}

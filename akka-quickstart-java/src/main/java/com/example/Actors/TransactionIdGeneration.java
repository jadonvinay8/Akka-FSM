package com.example.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.messages.Payment;
import com.example.messages.TransformedPayment;

import java.util.UUID;

public class TransactionIdGeneration extends AbstractBehavior<Payment> {

    public static Behavior<Payment> create() {
        return Behaviors.setup(TransactionIdGeneration::new);
    }

    private final ActorRef<TransformedPayment> verification;

    private TransactionIdGeneration(ActorContext<Payment> context) {
        super(context);
        verification = context.spawn(FraudVerification.create(), "FraudVerification");
    }

    @Override
    public Receive<Payment> createReceive() {
        return newReceiveBuilder().onMessage(Payment.class, this::handlePayment).build();
    }

    private Behavior<Payment> handlePayment(Payment message) {
        UUID transactionId = UUID.randomUUID();
        getContext().getLog().info("TransactionId generated" + transactionId);
        verification.tell(new TransformedPayment(transactionId, message.from, message.to, message.amount));
        return this;
    }
}

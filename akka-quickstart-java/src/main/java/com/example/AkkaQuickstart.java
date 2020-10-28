package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.fsm.Events.PaymentInitiated;
import com.example.fsm.PaymentFSM;
import com.example.fsm.PaymentFSM.Payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AkkaQuickstart {

  public static void main(String[] args) {

//    final ActorSystem<String> paymentActorSystem = ActorSystem.create(InitiatePayment.create(), "main");
//    paymentActorSystem.tell("Start Payment");

      ActorSystem system = ActorSystem.create("main");
      ActorRef ref = system.actorOf(Props.create(PaymentFSM.class));

      List<PaymentInitiated> messages = new ArrayList<>();

//      messages.add(new PaymentInitiated(new Payment("V1", "V2", 20.0, ref)));
//      messages.add(new PaymentInitiated(new Payment("V2", "V3", 200.0, ref)));
//      messages.add(new PaymentInitiated(new Payment("V3", "V4", 2000.0, ref)));
//      messages.add(new PaymentInitiated(new Payment("V4", "V5", 20000.0, ref)));

      ref.tell(new PaymentInitiated(new Payment("V1", "V2", 20.0, ref)), ref);
      ref.tell(new PaymentInitiated(new Payment("V2", "V3", 20.0, ref)), ref);

//      messages.forEach(message -> {
//          System.out.println("hello");
//          ref.tell(message, ref);
//      });

    try {
      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ignored) {

    } finally {
      system.terminate();
    }
  }

}

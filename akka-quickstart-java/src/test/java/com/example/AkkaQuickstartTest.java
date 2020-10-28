package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.testkit.javadsl.TestKit;
import com.example.fsm.Events;
import com.example.fsm.PaymentFSM;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class AkkaQuickstartTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();

    @Test
    public void testGreeterActorSendingOfGreeting() {

    }
}


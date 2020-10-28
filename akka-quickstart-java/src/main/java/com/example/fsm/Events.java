package com.example.fsm;


import com.example.fsm.PaymentFSM.Payment;
import com.example.fsm.PaymentFSM.TransformedPayment;

public class Events {

    public static final class PaymentInitiated {
        public final Payment payment;

        public PaymentInitiated(Payment payment) {
            this.payment = payment;
        }
    }

    static final class PaymentValidationFailed {
        public final Payment payment;

        public PaymentValidationFailed(Payment payment) {
            this.payment = payment;
        }
    }

    public static final class TransactionIDGenerated {
        public final TransformedPayment payment;

        public TransactionIDGenerated(TransformedPayment payment) {
            this.payment = payment;
        }
    }

    static final class FraudVerificationSucceeded {
        public final TransformedPayment payment;

        public FraudVerificationSucceeded(TransformedPayment payment) {
            this.payment = payment;
        }
    }

    static final class FraudVerificationFailed {   }

    static final class TransactionSucceeded {
        public final TransformedPayment payment;

        public TransactionSucceeded(TransformedPayment payment) {
            this.payment = payment;
        }
    }

    static final class TransactionFailed {
        public final TransformedPayment payment;

        public TransactionFailed(TransformedPayment payment) {
            this.payment = payment;
        }
    }

    static final class ConfirmationSent {   }

    static final class NegativeAcknowledgementSent {   }
}

package com.example.messages;

import java.util.Objects;

public class ErrorMessages {

    public final TransformedPayment payment;
    public final String causeOfError;

    public ErrorMessages(TransformedPayment payment, String causeOfError) {
        this.payment = payment;
        this.causeOfError = causeOfError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessages that = (ErrorMessages) o;
        return Objects.equals(payment, that.payment) &&
                Objects.equals(causeOfError, that.causeOfError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, causeOfError);
    }

    @Override
    public String toString() {
        return "ErrorMessages{" +
                "payment=" + payment +
                ", causeOfError='" + causeOfError + '\'' +
                '}';
    }
}

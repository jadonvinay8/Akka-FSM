package com.example.messages;

import java.util.Objects;
import java.util.UUID;

public class TransformedPayment {

    public final UUID transactionId;
    public final String from;
    public final String to;
    public final Double amount;

    public TransformedPayment(UUID transactionId, String from, String to, Double amount) {
        this.transactionId = transactionId;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransformedPayment that = (TransformedPayment) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, from, to, amount);
    }

    @Override
    public String toString() {
        return "TransformedPayment{" +
                "transactionId=" + transactionId +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}

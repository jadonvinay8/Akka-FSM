package com.example.messages;

import java.util.Objects;

public class Payment {

    public final Double amount;
    public final String from;
    public final String to;

    public Payment(Double amount, String from, String to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(amount, payment.amount) &&
                Objects.equals(from, payment.from) &&
                Objects.equals(to, payment.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, from, to);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
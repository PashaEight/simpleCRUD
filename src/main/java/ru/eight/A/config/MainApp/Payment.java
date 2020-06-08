package ru.eight.A.config.MainApp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    int id;
    int amount;

    public Payment(String pay_id, String amount) {
        this.id = Integer.parseInt(pay_id);
        this.amount = Integer.parseInt(amount);
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return ("id: " + this.getId() + ", amount: " + this.getAmount());
    }
}

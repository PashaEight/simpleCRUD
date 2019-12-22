public class Payment {
    int pay_id;
    int amount;

    public Payment(int pay_id, int amount) {
        this.pay_id = pay_id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "pay_id=" + pay_id +
                ", amount=" + amount +
                '}';
    }
}

package edu.haramaya.cosc3053.day08;

public class PaymentGatewayDemo {
    public interface Payable {
        boolean processPayment(double amount);
        String getReceipt();
        default void logTrace(double amount) {
            System.out.println("[AUDIT] Processing payment: $" + amount);
        }
    }

    public static abstract class AbstractGateway implements Payable {
        private final String merchantId;
        public AbstractGateway(String merchantId) { this.merchantId = merchantId; }
        public String getMerchantId() { return merchantId; }
        public abstract double calculateFee(double amount);
    }

    // TODO 8.1: Removed 'abstract' so CreditCardGateway can be instantiated
    public static class CreditCardGateway extends AbstractGateway {
        private double lastAmount;
        private double lastFee;
        private boolean lastStatus;

        public CreditCardGateway(String merchantId) {
            super(merchantId);
        }

        // TODO 8.2: 2.5% fee calculation
        @Override
        public double calculateFee(double amount) {
            return amount * 0.025;
        }

        // TODO 8.3: Process payment and store state for the receipt
        @Override
        public boolean processPayment(double amount) {
            logTrace(amount);
            if (amount <= 0) {
                this.lastStatus = false;
                return false;
            }
            this.lastAmount = amount;
            this.lastFee = calculateFee(amount);
            this.lastStatus = true;
            return true;
        }

        @Override
        public String getReceipt() {
            if (!lastStatus) {
                return "Receipt [Merchant: " + getMerchantId() + "] - Payment Failed or Not Processed.";
            }
            double total = lastAmount + lastFee;
            return String.format(
                    "Receipt [Merchant: %s] | Amount: $%.2f | Fee (2.5%%): $%.2f | Total Charged: $%.2f",
                    getMerchantId(), lastAmount, lastFee, total
            );
        }
    }

    public static void main(String[] args) {
        System.out.println("Day 08 Interfaces & Abstract Classes Challenge");

        Payable gateway = new CreditCardGateway("MERCH-HRU-08");

        if (gateway.processPayment(200.00)) {
            System.out.println(gateway.getReceipt());
        }
    }
}

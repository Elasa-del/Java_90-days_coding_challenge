public class Day_05_BankAccount {
    // TODO 5.1: Declare private encapsulated fields
    private final String accountNumber; // Read-only via final keyword
    private String accountHolder;
    private double balance;
    static final double MINIMUM_BALANCE = 25.00;

    // TODO 5.2: Implement 3-arg constructor with defensive validation
    public Day_05_BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be null or blank.");
        }
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(String.format("Initial deposit ($%.2f) is below the minimum required balance ($%.2f).",
                    initialDeposit, MINIMUM_BALANCE));
        }

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    // TODO 5.3: Implement deposit(double amount) -> return boolean success
    public boolean deposit(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Deposit failed for %s: Amount ($%.2f) must be positive.%n",
                    accountNumber, amount);
            return false;
        }

        this.balance += amount;
        System.out.printf("✅ [SUCCESS] Deposited $%.2f into %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    // TODO 5.4: Implement withdraw(double amount) -> return boolean success
    public boolean withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Withdrawal failed for %s: Amount ($%.2f) must be positive.%n",
                    accountNumber, amount);
            return false;
        }
        if ((this.balance - amount) < MINIMUM_BALANCE) {
            System.out.printf("❌ [REJECTED] Withdrawal of $%.2f from %s denied. Remaining balance ($%.2f) would violate minimum required balance ($%.2f).%n",
                    amount, accountNumber, (this.balance - amount), MINIMUM_BALANCE);
            return false;
        }

        this.balance -= amount;
        System.out.printf("✅ [SUCCESS] Withdrew $%.2f from %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    public static void main(String[] args) {
        Day_05_BankAccount acc = new Day_05_BankAccount("ACC-101", "Abebe Bekele", 500.00);
        acc.deposit(150.00);
        acc.withdraw(100.00);
        acc.withdraw(600.00); // Should fail minimum balance check ($25.00)
    }
}

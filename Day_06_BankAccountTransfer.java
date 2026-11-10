class BankAccount {
    private final String accountNumber;
    private String accountHolder;
    private double balance;
    static final double MINIMUM_BALANCE = 25.00;

    public BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
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

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
}

public class Day_06_BankAccountTransfer {

    // TODO 6.1: Implement static transfer(BankAccount src, BankAccount dest, double amount)
    public static boolean transfer(BankAccount src, BankAccount dest, double amount) {
        if (src == null || dest == null) {
            System.out.println("❌ Error: Source or destination account cannot be null.");
            return false;
        }
        if (src == dest) {
            System.out.println("❌ Error: Source and destination accounts cannot be the same.");
            return false;
        }
        if (amount <= 0.0) {
            System.out.println("❌ Error: Transfer amount must be positive.");
            return false;
        }

        // Attempt to withdraw from source. If successful, proceed to deposit into destination.
        if (src.withdraw(amount)) {
            dest.deposit(amount);
            System.out.printf("🔄 [TRANSFER SUCCESS] Transferred $%.2f from %s to %s.%n",
                    amount, src.getAccountNumber(), dest.getAccountNumber());
            return true;
        }

        System.out.printf("❌ [TRANSFER FAILED] Transfer of $%.2f from %s to %s failed due to insufficient funds.%n",
                amount, src.getAccountNumber(), dest.getAccountNumber());
        return false;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("ACC-101", "Abebe", 500.0);
        BankAccount acc2 = new BankAccount("ACC-102", "Kebede", 200.0);

        boolean success = transfer(acc1, acc2, 150.0);
        System.out.println("Transfer Success: " + success);
        System.out.println("ACC-101 Balance: $" + acc1.getBalance());
        System.out.println("ACC-102 Balance: $" + acc2.getBalance());
    }
}

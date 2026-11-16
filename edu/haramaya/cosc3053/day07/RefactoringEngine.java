package edu.haramaya.cosc3053.day07;

class BankAccount {
    private final String accountNumber;
    private String accountHolder;
    private double balance;
    public static final double MINIMUM_BALANCE = 25.00;

    public BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be null or blank.");
        }
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(String.format(
                    "Initial deposit ($%.2f) is below the minimum required balance ($%.2f).",
                    initialDeposit, MINIMUM_BALANCE));
        }

        this.accountNumber = accountNumber.trim();
        this.accountHolder = accountHolder.trim();
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
            System.out.printf("❌ [REJECTED] Withdrawal of $%.2f from %s denied. Remaining balance ($%.2f) would violate minimum ($%.2f).%n",
                    amount, accountNumber, (this.balance - amount), MINIMUM_BALANCE);
            return false;
        }

        this.balance -= amount;
        System.out.printf("✅ [SUCCESS] Withdrew $%.2f from %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
}

public class RefactoringEngine {

    // TODO 7.1 & 7.2: Clean, validated account lookup using Arrays & Strings concepts
    public static BankAccount findAccountByNumber(BankAccount[] accounts, String targetAccountNumber) {
        if (accounts == null || targetAccountNumber == null || targetAccountNumber.isBlank()) {
            return null;
        }

        String normalizedTarget = targetAccountNumber.trim();
        for (BankAccount account : accounts) {
            if (account != null && account.getAccountNumber().equalsIgnoreCase(normalizedTarget)) {
                return account;
            }
        }
        return null;
    }

    // Core object-to-object transfer
    public static boolean transfer(BankAccount src, BankAccount dest, double amount) {
        if (src == null || dest == null) {
            System.out.println("❌ [ERROR] Source or destination account cannot be null.");
            return false;
        }
        if (src == dest || src.getAccountNumber().equalsIgnoreCase(dest.getAccountNumber())) {
            System.out.println("❌ [ERROR] Source and destination accounts must be distinct.");
            return false;
        }

        // Delegate amount and minimum-balance checks directly to src.withdraw()
        if (src.withdraw(amount)) {
            dest.deposit(amount);
            System.out.printf("🔄 [TRANSFER SUCCESS] Transferred $%.2f from %s (%s) to %s (%s).%n",
                    amount, src.getAccountNumber(), src.getAccountHolder(),
                    dest.getAccountNumber(), dest.getAccountHolder());
            return true;
        }

        System.out.printf("❌ [TRANSFER FAILED] Could not transfer $%.2f from %s to %s.%n",
                amount, src.getAccountNumber(), dest.getAccountNumber());
        return false;
    }

    // High-level refactored transfer combining lookup + transfer validation
    public static boolean transferByAccountNumber(BankAccount[] registry, String srcId, String destId, double amount) {
        BankAccount src = findAccountByNumber(registry, srcId);
        BankAccount dest = findAccountByNumber(registry, destId);

        if (src == null) {
            System.out.printf("❌ [LOOKUP FAILED] Source account '%s' not found.%n", srcId);
            return false;
        }
        if (dest == null) {
            System.out.printf("❌ [LOOKUP FAILED] Destination account '%s' not found.%n", destId);
            return false;
        }

        return transfer(src, dest, amount);
    }

    public static void main(String[] args) {
        BankAccount[] bankRegistry = {
                new BankAccount("ACC-101", "Abebe Bekele", 500.00),
                new BankAccount("ACC-102", "Kebede Alemu", 200.00),
                new BankAccount("ACC-103", "Tigist Haile", 350.00)
        };

        // 1. Valid transfer via lookup
        transferByAccountNumber(bankRegistry, "ACC-101", "ACC-102", 150.00);

        // 2. Edge case: Account not found
        transferByAccountNumber(bankRegistry, "ACC-999", "ACC-103", 50.00);

        // 3. Edge case: Violates minimum balance
        transferByAccountNumber(bankRegistry, "ACC-102", "ACC-103", 340.00);

        System.out.println("=== DAY 07 WEEKLY REFACTORING & CATCH-UP COMPLETED ===");
    }
}


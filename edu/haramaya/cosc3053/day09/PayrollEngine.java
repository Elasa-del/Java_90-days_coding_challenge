package edu.haramaya.cosc3053.day09;

public class PayrollEngine {

    public static abstract class Employee {
        private final String id;
        private final String name;

        public Employee(String id, String name) {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("Employee ID cannot be null or blank.");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Employee name cannot be null or blank.");
            }
            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract double calculatePay();
    }

    // TODO 9.1: Implement FullTimeEmployee (monthlySalary) extending Employee
    public static class FullTimeEmployee extends Employee {
        private final double monthlySalary;

        public FullTimeEmployee(String id, String name, double monthlySalary) {
            super(id, name);
            if (monthlySalary < 0) {
                throw new IllegalArgumentException("Monthly salary cannot be negative.");
            }
            this.monthlySalary = monthlySalary;
        }

        @Override
        public double calculatePay() {
            return monthlySalary;
        }
    }

    // TODO 9.2: Implement ContractEmployee (hourlyRate, hoursWorked) extending Employee
    public static class ContractEmployee extends Employee {
        private final double hourlyRate;
        private final double hoursWorked;

        public ContractEmployee(String id, String name, double hourlyRate, double hoursWorked) {
            super(id, name);
            if (hourlyRate < 0 || hoursWorked < 0) {
                throw new IllegalArgumentException("Hourly rate and hours worked cannot be negative.");
            }
            this.hourlyRate = hourlyRate;
            this.hoursWorked = hoursWorked;
        }

        @Override
        public double calculatePay() {
            return hoursWorked * hourlyRate;
        }
    }

    // TODO 9.3: Polymorphic payroll processor looping over Employee[] array
    public static double runEmployeePayroll(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            System.out.println("No employees found to process.");
            return 0.0;
        }

        double totalPayroll = 0.0;
        System.out.println("=== PROCESSING MONTHLY PAYROLL ===");

        for (Employee emp : employees) {
            if (emp != null) {
                // Runtime polymorphism: invokes FullTimeEmployee or ContractEmployee calculatePay()
                double pay = emp.calculatePay();
                totalPayroll += pay;
                System.out.printf("[%s] %-16s (%s) -> Pay: $%.2f%n",
                        emp.getId(), emp.getName(), emp.getClass().getSimpleName(), pay);
            }
        }

        System.out.printf("Total Payroll Disbursed: $%.2f%n", totalPayroll);
        return totalPayroll;
    }

    public static void main(String[] args) {
        System.out.println("Day 09 Payroll Hierarchy Challenge");

        Employee[] employees = {
                new FullTimeEmployee("FTE-101", "Ebisa Fufa", 15000.00),
                new FullTimeEmployee("FTE-102", "Dechasa Shibiru", 12000.00),
                new ContractEmployee("CTE-101", "Elias Melkamu", 250.00, 40.0),
                new ContractEmployee("CTE-102", "Ibsa Gemmechis", 253.00, 50.0)
        };

        runEmployeePayroll(employees);
    }
}

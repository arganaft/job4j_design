package ru.job4j.ood.lsp;

class Employee {
    protected double baseSalary;

    public Employee(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }

    class Manager extends Employee {
        private double bonus;

        public Manager(double baseSalary, double bonus) {
            super(baseSalary);
            this.bonus = bonus;
        }

        @Override
        public double calculateSalary() {
            return super.calculateSalary() + bonus;
        }
    }

    class Director extends Employee {
        private double taxRate;

        public Director(double baseSalary, double taxRate) {
            super(baseSalary);
            this.taxRate = taxRate;
        }

        @Override
        public double calculateSalary() {
            return super.calculateSalary() * (1 - taxRate);
        }
    }
}

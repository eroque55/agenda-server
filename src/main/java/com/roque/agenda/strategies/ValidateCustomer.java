package com.roque.agenda.strategies;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;

import java.time.LocalDate;

public class ValidateCustomer implements IStrategy {
    @Override
    public String execute(DomainEntity entity) {
        StringBuilder sb = new StringBuilder();

        if (entity == null) {
            sb.append("Entidade nula, ");
        }

        Customer customer = (Customer) entity;

        if (customer.getName() == null || customer.getName().isEmpty()) {
            sb.append("Nome do cliente é obrigatório, ");
        }

        if (customer.getCpf() == null || customer.getCpf().isEmpty()) {
            sb.append("CPF é obrigatório, ");
        } else if (!isValidCPF(customer.getCpf())) {
            sb.append("CPF inválido, use apenas números, ");
        }

        if (customer.getBirthday() != null) {
            if (customer.getBirthday().getYear() < 1900 || customer.getBirthday().isAfter(LocalDate.now())) {
                sb.append("Data de nascimento inválida, ");
            }
        }

        return sb.toString();
    }

    private boolean isValidCPF(String cpf) {
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return checkVerificationDigits(cpf);
    }

    private boolean checkVerificationDigits(String cpf) {
        int firstDigit = calculateDigit(cpf, 10);
        int secondDigit = calculateDigit(cpf, 11);

        return (firstDigit == Character.getNumericValue(cpf.charAt(9)) &&
                secondDigit == Character.getNumericValue(cpf.charAt(10)));
    }

    private int calculateDigit(String cpf, int initialWeight) {
        int sum = 0;
        for (int i = 0; i < initialWeight - 1; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (initialWeight - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}

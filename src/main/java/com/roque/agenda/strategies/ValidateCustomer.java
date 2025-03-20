package com.roque.agenda.strategies;

import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;

import java.util.Date;


public class ValidateCustomer implements IStrategy {
    @Override
    public String execute(DomainEntity entity) {
        StringBuilder sb = new StringBuilder();
        if (entity == null) {
            sb.append("Entidade nula\n");
        }
        if (entity instanceof Customer) {
            Customer customer = (Customer) entity;

            if (customer.getName() == null || customer.getName().isEmpty()) {
                sb.append("Nome do cliente é obrigatório\n");
            }
            if (customer.getCpf() == null || customer.getCpf().isEmpty()) {
                sb.append("CPF é obrigatório\n");

            }
            if (customer.getCpf().length() != 11 || checkRepeatedDigits(customer.getCpf()) || checkVerificationDigits(customer.getCpf())) {
                sb.append("CPF inválido\n");
            }
            if (customer.getBirthday() != null) {
                if (customer.getBirthday().getYear() < 1900 || customer.getBirthday().after(new Date())) {
                    sb.append("Data de nascimento inválida\n");
                }
            }
        }
        return "";
    }

    private boolean checkRepeatedDigits(String cpf) {
        return (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999"));
    }

    private int calculateDigit(String cpf, int initialWeight) {
        int sum = 0;
        for (int i = 0; i < initialWeight; i++) {
            sum += cpf.charAt(i) * initialWeight - i;
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private boolean checkVerificationDigits(String cpf) {
        int firstDigit = calculateDigit(cpf, 10);
        int secondDigit = calculateDigit(cpf, 11);

        return (firstDigit == cpf.charAt(9) && secondDigit == cpf.charAt(10));
    }
}

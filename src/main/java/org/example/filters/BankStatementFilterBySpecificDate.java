package org.example.filters;

import org.example.model.BankStatement;

import java.time.LocalDate;

public class BankStatementFilterBySpecificDate implements BankStatementFilter {

    private final LocalDate date;

    public BankStatementFilterBySpecificDate(LocalDate date){
        this.date = date;
    }

    @Override
    public boolean filter(BankStatement bankStatement) {
        return bankStatement.getDate().equals(date);
    }
}

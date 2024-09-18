package org.example.filters;

import org.example.model.BankStatement;

import java.time.Month;

public class BankStatementFilterByMonth implements BankStatementFilter{

    private final Month month;

    public BankStatementFilterByMonth(Month month) {
        this.month = month;
    }

    @Override
    public boolean filter(BankStatement bankStatement) {
        return bankStatement.getDate().getMonth().equals(month);
    }
}

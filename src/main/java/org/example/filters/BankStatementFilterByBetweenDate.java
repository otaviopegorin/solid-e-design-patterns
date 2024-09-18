package org.example.filters;

import org.example.model.BankStatement;

import java.time.LocalDate;

public class BankStatementFilterByBetweenDate implements BankStatementFilter{
    public final LocalDate dateBefore;
    public final LocalDate dateAfter;

    public BankStatementFilterByBetweenDate(LocalDate dateBefore, LocalDate dateAfter) {
        this.dateBefore = dateBefore;
        this.dateAfter = dateAfter;
    }

    @Override
    public boolean filter(BankStatement bankStatement) {
        LocalDate date = bankStatement.getDate();
        return date.isEqual(dateBefore) || date.equals(dateAfter) || (date.isAfter(dateBefore) && date.isBefore(dateAfter));
    }
}

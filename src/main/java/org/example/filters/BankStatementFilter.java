package org.example.filters;

import org.example.model.BankStatement;

public interface BankStatementFilter {
    public boolean filter(BankStatement bankStatement);
}

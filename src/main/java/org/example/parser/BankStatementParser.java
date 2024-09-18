package org.example.parser;

import org.example.model.BankStatement;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface BankStatementParser<T, C> {
    public BankStatement parseFrom(T line);
    public List<BankStatement> parseFromLines(C lines);
}

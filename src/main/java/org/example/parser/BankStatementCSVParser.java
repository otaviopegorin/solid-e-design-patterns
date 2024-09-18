package org.example.parser;

import org.example.model.BankStatement;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser<String, List<String>> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private File file;

    @Override
    public BankStatement parseFrom(String line){
        String[] arguments = line.split(",");
        BankStatement bankStatement = new BankStatement();

        bankStatement.setValue(Double.parseDouble(arguments[1]));
        bankStatement.setDate(LocalDate.parse(arguments[0], formatter));
        bankStatement.setDescription(arguments[2]);
        return bankStatement;
    }

    @Override
    public List<BankStatement> parseFromLines(List<String> lines){
        final List<BankStatement> bankStatements = new ArrayList<BankStatement>();
        for (String line : lines){
            bankStatements.add(parseFrom(line));
        }
        return bankStatements;
    }
}

package org.example.services;

import org.example.filters.BankStatementFilter;
import org.example.model.BankStatement;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {

    private List<BankStatement> lines = new ArrayList<BankStatement>();

    public BankStatementProcessor(List<BankStatement> lines){
        this.lines = lines;
    }

    public Double getAmount() {
        return lines.stream().mapToDouble(BankStatement::getValue).sum();
    }

    public Double getAmountByMonth(Month month){
        return lines.stream().filter(n -> n.getDate().getMonth().equals(month)).mapToDouble(BankStatement::getValue).sum();
    }

    public Double getAmountByDate(LocalDate date){
        return lines.stream().filter(n -> n.getDate().equals(date)).mapToDouble(BankStatement::getValue).sum();
    }

    public Double getAmountByDescription(String description){
        return lines.stream().filter(n -> n.getDescription().equals(description)).mapToDouble(BankStatement::getValue).sum();
    }

    public Double getMaxValue(){
        List<Double> collect = lines.stream().map(BankStatement::getValue).collect(Collectors.toList());
        return Collections.max(collect);
    }

    public Double getMinValue(){
        List<Double> collect = lines.stream().map(BankStatement::getValue).collect(Collectors.toList());
        return Collections.min(collect);
    }

    public List<BankStatement> filter(BankStatementFilter filter){
        List<BankStatement> results = new ArrayList<BankStatement>();
        for(BankStatement statement : lines){
            if(filter.filter(statement)){
                results.add(statement);
            }
        }
        return results;
    }
}

package org.example.model;

import java.time.LocalDate;

public class BankStatement {

    private LocalDate date;
    private Double value;
    private String description;

    public BankStatement(LocalDate date, Double value, String description) {
        this.date = date;
        this.value = value;
        this.description = description;
    }

    public BankStatement() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(BankStatement bankStatement){
        if(this == bankStatement) return true;

        return bankStatement.getDate().equals(this.getDate()) &&
                bankStatement.getDescription().equals(this.getDescription()) &&
                bankStatement.getValue().equals(this.getValue());
    }
}


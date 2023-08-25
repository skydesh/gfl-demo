package com.example.gfldemo.validators;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Optional;

public class Validator {
    
    public Optional<Expression> checkOnValid(String expression) {
    try {
        Expression expr = new ExpressionBuilder(expression).variable("x").build();
        if (expr.validate(false).isValid()) {
            return Optional.of(expr);
        } else {
            return Optional.empty();
        }
    } catch (Exception e) {
        return Optional.empty();
    }
}
}

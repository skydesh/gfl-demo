package com.example.gfldemo.services;

import com.example.gfldemo.repositorries.ExpressionRepository;
import com.example.gfldemo.validators.Validator;
import lombok.AllArgsConstructor;
import net.objecthunter.exp4j.Expression;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpressionService {
    ExpressionRepository expressionRepository;

    public boolean check(String expression, char first, char second) {
        int count = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == first) {
                count++;
            } else if (c == second) {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public boolean isCorrect(String expression) {
        Validator validator = new Validator();
        Optional<Expression> expr = validator.checkOnValid(expression);
        return expr.isPresent();
    }

    public void saveToDB(String e) {
        com.example.gfldemo.data.Expression expression = new com.example.gfldemo.data.Expression();
        expression.setExpr(e);
        expressionRepository.save(expression);
    }

    public List<com.example.gfldemo.data.Expression> getExpressions() {
        return expressionRepository.findAll();
    }
}

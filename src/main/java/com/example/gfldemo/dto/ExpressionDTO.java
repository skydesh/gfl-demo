package com.example.gfldemo.dto;

import com.example.gfldemo.validators.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.objecthunter.exp4j.Expression;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ExpressionDTO {
    private String expression;
    private String checkBrackets;
    private String validationResult;
    private String infoBracketsColor;
    private String validationResultColor;

    public String getButtonClass() {
        Validator validator = new Validator();
        Optional<Expression> expr = validator.checkOnValid(expression);
        if (expr.isPresent()) {
            return "btn btn-success";
        } else {
            return "btn btn-danger";
        }
    }

    public String getType() {
        Validator validator = new Validator();
        Optional<Expression> expr = validator.checkOnValid(expression);
        if (expr.isPresent()) {
            return "submit";
        } else {
            return "button";
        }
    }
}

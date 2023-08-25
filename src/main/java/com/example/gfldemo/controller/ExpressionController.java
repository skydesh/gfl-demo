package com.example.gfldemo.controller;

import com.example.gfldemo.data.Expression;
import com.example.gfldemo.dto.ExpressionDTO;
import com.example.gfldemo.services.ExpressionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ExpressionController {
    ExpressionService expressionService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("store")
    public String database(Model model) {
        List<Expression> expressions = expressionService.getExpressions();
        model.addAttribute("expressions", expressions);
        return "expressions";
    }

    @PostMapping("calculate")
    public String calculate(@RequestParam String expression, Model model) {
        String infoBrackets, validationResult, infoBracketsColor, validationResultColor;
        String correct = "color:green;", incorrect = "color: red";

        if (expressionService.check(expression, '(', ')')) {
            infoBrackets = "Brackets either correct or absent";
            infoBracketsColor = correct;
        } else {
            infoBrackets = "Brackets are incorrect";
            infoBracketsColor = incorrect;
        }
        if (expressionService.isCorrect(expression)) {
            validationResult = "Expression is correct";
            validationResultColor = correct;
        } else {
            validationResult = "Expression is incorrect";
            validationResultColor = incorrect;
        }
        model.addAttribute("exprDto", new ExpressionDTO(
                expression, infoBrackets, validationResult, infoBracketsColor, validationResultColor));
        return "result";
    }

    @PostMapping("store")
    public String saveToDB(@RequestParam String expr) {
        expressionService.saveToDB(expr);
        return "redirect:store";
    }
}

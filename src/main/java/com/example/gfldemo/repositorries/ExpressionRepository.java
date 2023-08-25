package com.example.gfldemo.repositorries;

import com.example.gfldemo.data.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends JpaRepository<Expression, Integer> {

}

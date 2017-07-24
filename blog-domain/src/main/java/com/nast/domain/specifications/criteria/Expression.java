package com.nast.domain.specifications.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface Expression {

    BooleanExpressionBuilder and();

    BooleanExpressionBuilder or();

    BooleanExpression build();
}

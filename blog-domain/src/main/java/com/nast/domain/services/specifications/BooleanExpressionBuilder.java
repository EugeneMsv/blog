package com.nast.domain.services.specifications;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Строитель логического выражения для поиска доменных сущностей
 */
public final class BooleanExpressionBuilder {

    private BooleanExpression predicate = Expressions.TRUE;

    private BooleanExpressionBuilder(BooleanExpression predicate) {
        this.predicate = predicate;
    }

    private BooleanExpressionBuilder() {
    }

    public static BooleanExpressionBuilder create(BooleanExpression predicate) {
        return new BooleanExpressionBuilder(predicate);
    }

    public static BooleanExpressionBuilder create() {
        return new BooleanExpressionBuilder();
    }

    /**
     * Построить логическое выражение(предикат) из билдера
     *
     * @return построенный логический предикат
     */
    public BooleanExpression build() {
        return predicate;
    }

    /**
     * Добавить в к предикату логическое выражение(предикат) результата функции {@code expressionFunc} для {@code value},
     * в случае если значение {@code value} НЕ  прошло проверку на предикат {@code appliancePrd}
     *
     * @param value          значение
     * @param appliancePrd   предикат для проверки
     * @param expressionFunc функции для обработки значения
     * @param <V>            тип значения
     * @return билдер предиката
     */
    public <V> BooleanExpressionBuilder and(V value, Predicate<V> appliancePrd,
                                            Function<V, BooleanExpression> expressionFunc) {
        if (!appliancePrd.test(value)) {
            predicate.and(expressionFunc.apply(value));
        }
        return this;
    }

    /**
     * Добавить предикат напрямую в текущий предикат
     * @param expression логическое выражение(предикат)
     * @return билдер предиката
     */
    public BooleanExpressionBuilder and(@Nullable BooleanExpression expression) {
        predicate.and(expression);
        return this;
    }

}

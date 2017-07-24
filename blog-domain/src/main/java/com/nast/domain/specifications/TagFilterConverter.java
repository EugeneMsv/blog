package com.nast.domain.specifications;

import com.nast.domain.entities.QTag;
import com.nast.domain.filters.TagFilter;
import com.nast.domain.specifications.criteria.BooleanExpressionBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Спецификации поиска сущности {@link com.nast.domain.entities.Tag}
 */
@Component
public final class TagFilterConverter implements Converter<TagFilter, Predicate> {

    private static final QTag TAG = QTag.tag;

    @Override
    public Predicate convert(TagFilter filter) {
        return buildPredicate(filter);
    }

    public static Predicate buildPredicate(TagFilter filter) {
        return BooleanExpressionBuilder.create(TAG.isNotNull())
                .and().value(filter.getCode()).checkIfNot(String::isEmpty).that(TAG.code::containsIgnoreCase)
                .build();
    }
}

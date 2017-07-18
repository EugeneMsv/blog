package com.nast.domain.services.specifications;

import com.nast.domain.entities.QTag;
import com.nast.domain.filters.TagFilter;
import com.querydsl.core.types.Predicate;
import org.springframework.util.StringUtils;

/**
 * Спецификации поиска сущности {@link com.nast.domain.entities.Tag}
 */
public final class TagSpecification {

    private TagSpecification() {
        throw new UnsupportedOperationException();

    }

    private static final QTag TAG = QTag.tag;


    public static Predicate buildPredicate(TagFilter filter) {
        return BooleanExpressionBuilder.create(TAG.isNotNull())
                .and(filter.getCode(), StringUtils::isEmpty, TAG.code::containsIgnoreCase)
                .build();
    }
}

package com.nast.domain.services.specifications;

import com.nast.domain.entities.QPost;
import com.nast.domain.filters.PostFilter;
import com.nast.domain.services.specifications.criteria.BooleanExpressionBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Спецификации поиска сущности {@link com.nast.domain.entities.Post}
 */
public final class PostSpecification {

    private PostSpecification() {
        throw new UnsupportedOperationException();

    }

    private static final QPost POST = QPost.post;


    public static Predicate buildPredicate(PostFilter filter) {
        return BooleanExpressionBuilder.create(POST.isNotNull())
                .and().value(filter.getAuthor()).checkIfNot(StringUtils::isEmpty).that(POST.author::containsIgnoreCase)
                .and().value(filter.getTitle()).checkIfNot(StringUtils::isEmpty).that(POST.author::containsIgnoreCase)
                .and().value(filter.getFrom()).checkIf(Objects::nonNull).that(POST.postRegister.createdTime::after)
                .and().value(filter.getTo()).checkIf(Objects::nonNull).that(POST.postRegister.createdTime::before)
                .and().value(filter.getTags()).checkIfNot(CollectionUtils::isEmpty).that(POST.tags.any().code::in)
                .build();
    }
}

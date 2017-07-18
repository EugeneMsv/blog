package com.nast.domain.services.specifications;

import com.nast.domain.entities.QPost;
import com.nast.domain.filters.PostFilter;
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
                .and(filter.getAuthor(), StringUtils::isEmpty, POST.author::containsIgnoreCase)
                .and(filter.getTitle(), StringUtils::isEmpty, POST.title::containsIgnoreCase)
                .and(filter.getFrom(), Objects::isNull, POST.postRegister.createdTime::after)
                .and(filter.getTo(), Objects::isNull, POST.postRegister.createdTime::before)
                .and(filter.getTags(), CollectionUtils::isEmpty, POST.tags.any().code::in)
                .build();
    }
}

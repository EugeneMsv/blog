package com.nast.services.impl;

import com.nast.domain.entities.QTag;
import com.nast.domain.entities.Tag;
import com.nast.services.TagService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class TagServiceImplTest {


    @Autowired
    private TagService tagService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void end(){
        tagService.deleteAll();
    }

    @Test
    public void saveTag_Success() throws Exception {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setGroupCode("19909");
        Tag saved = tagService.save(saveTarget);
        assertTrue(saved.isPersisted());
    }

    @Test
    public void doubleSaveTag_Exception() throws Exception {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setGroupCode("19909");
        Tag saved = tagService.save(saveTarget);
        Tag saved2 = tagService.save(saveTarget);
        assertTrue(saved.isPersisted());
    }

    @Test
    public void testQueryPredicate() {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setGroupCode("19909");
        Tag saved = tagService.save(saveTarget);
        QTag tagQuery = QTag.tag;
        BooleanExpression expression = tagQuery.groupCode.like("199%");
        Page<Tag> tagsPage = tagService.findAll(expression, new QPageRequest(0, 20));
        assertEquals(1,tagsPage.getNumberOfElements());
        for (Tag tag : tagsPage) {
            assertEquals(saved, tag);
        }
    }

}
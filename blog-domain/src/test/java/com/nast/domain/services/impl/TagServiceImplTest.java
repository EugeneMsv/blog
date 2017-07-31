package com.nast.domain.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.domain.filters.TagFilter;
import com.nast.domain.services.TagService;
import com.nast.domain.specifications.TagFilterConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class TagServiceImplTest {


    @Autowired
    private TagService tagService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void end() {
        tagService.deleteAll();
    }

    @Test
    public void saveTag_Success() throws Exception {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setCode("19909");
        Tag saved = tagService.save(saveTarget);
        assertTrue(saved.isPersisted());
    }

    @Test
    public void doubleSaveTag_Exception() throws Exception {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setCode("19909");
        Tag saved = tagService.save(saveTarget);
        Tag saved2 = tagService.save(saveTarget);
        assertTrue(saved.isPersisted());
    }


    @Test
    @Ignore
    public void callProfilingTime() throws Exception {
        long allCalls = 0;
        int cycles = 300000;
        for (int i = 0; i < cycles; i++) {
            Tag saveTarget = new Tag(UUID.randomUUID().toString().substring(0,20), UUID.randomUUID().toString().substring(0,20));
            Instant start = Instant.now();
            tagService.save(saveTarget);
            allCalls += Duration.between(start, Instant.now()).getNano();
        }
        System.out.println("Average time " + allCalls / cycles);
    }


    @Test
    public void testQueryPredicate() {
        Tag saveTarget = new Tag();
        saveTarget.setDescription("Test description");
        saveTarget.setCode("19909");
        Tag saved = tagService.save(saveTarget);
        TagFilter tagFilter = new TagFilter();
        tagFilter.setCode("199");
        Page<Tag> tagsPage = tagService.findAll(tagFilter, new QPageRequest(0, 20));
        assertEquals(1, tagsPage.getNumberOfElements());
        for (Tag tag : tagsPage) {
            assertEquals(saved, tag);
        }
    }

}
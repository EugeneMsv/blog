package com.nast.domain.services.impl;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nast.domain.entities.Tag;
import com.nast.domain.services.TagService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static com.nast.domain.utils.AssertionUtils.*;
import static com.nast.domain.utils.DomainEntityBuilder.buildRandomString;
import static com.nast.domain.utils.DomainEntityBuilder.buildRandomTag;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/preset/clean.xml", type = DatabaseOperation.DELETE_ALL)
public class TagServiceImplIT {

    @Autowired
    private TagService tagService;

    @Test
    public void test_save_Tag_Success() throws Exception {
        Tag randTag = buildRandomTag();
        Tag saved = tagService.save(randTag);
        assertPersist(saved);
        assertFieldsEquals(randTag, saved);
    }

    @Test
    @Ignore
    // TODO: 17.08.2017 имеет смысл для dto  т.к.  здесь обхект по одной ссылке
    public void test_save_UpdateAfterFoundSaved_Success() throws Exception {
        //save
        Tag randTag = buildRandomTag();
        Tag saved = tagService.save(randTag);
        assertPersist(saved);
        assertFieldsEquals(randTag, saved);
        //find
        Optional<Tag> optTag = tagService.findOne(saved.getId());
        Tag found = optTag.get();
        assertNotNull(found);
        assertPersist(found);
        assertFieldsEquals(saved, found);
        //update
        found.setDescription(buildRandomString());

        Tag updated = tagService.save(found);

        assertReflectionEquals(randTag, updated, "description");
    }


    @Test
    public void test_findOne_ById_Success() throws Exception {
        Tag randTag = buildRandomTag();
        Tag saved = tagService.save(randTag);
        assertFieldsEquals(randTag, saved);
        Optional<Tag> optTag = tagService.findOne(saved.getId());
        Tag found = optTag.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(tagService.exists());
        //save
        Tag randTag = buildRandomTag();
        Tag saved = tagService.save(randTag);
        assertFieldsEquals(randTag, saved);

        assertTrue(tagService.exists());
        //delete
        tagService.delete(saved.getId());
        assertFalse(tagService.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(tagService.exists());
        //save
        Tag randTag1 = buildRandomTag();
        Tag saved1 = tagService.save(randTag1);
        assertFieldsEquals(randTag1, saved1);

        Tag randTag2 = buildRandomTag();
        Tag saved2 = tagService.save(randTag2);
        assertFieldsEquals(randTag2, saved2);

        Page<Tag> page = tagService.findAll(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), is(page.getContent()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(tagService.exists());
        //save
        Tag randTag1 = buildRandomTag();
        Tag saved1 = tagService.save(randTag1);
        assertFieldsEquals(randTag1, saved1);

        Tag randTag2 = buildRandomTag();
        Tag saved2 = tagService.save(randTag2);
        assertFieldsEquals(randTag2, saved2);

        Page<Tag> page1 = tagService.findAll(new PageRequest(0, 1));
        Page<Tag> page2 = tagService.findAll(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());
        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }


    @Test
    @Ignore
    public void test_callProfilingTime() throws Exception {
        long allCalls = 0;
        int cycles = 300000;
        for (int i = 0; i < cycles; i++) {
            Tag saveTarget = new Tag(UUID.randomUUID().toString().substring(0, 20),
                    UUID.randomUUID().toString().substring(0, 20));
            Instant start = Instant.now();
            tagService.save(saveTarget);
            allCalls += Duration.between(start, Instant.now()).getNano();
        }
        System.out.println("Average time " + allCalls / cycles);
    }

}
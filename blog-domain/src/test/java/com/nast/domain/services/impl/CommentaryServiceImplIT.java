package com.nast.domain.services.impl;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.nast.domain.entities.Commentary;
import com.nast.domain.entities.PostRegister;
import com.nast.domain.services.CommentaryService;
import com.nast.domain.services.PostRegisterService;
import org.junit.Before;
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

import java.util.Arrays;
import java.util.Optional;

import static com.nast.domain.utils.AssertionUtils.assertFieldsEquals;
import static com.nast.domain.utils.AssertionUtils.assertPersist;
import static com.nast.domain.utils.DomainEntityBuilder.buildRandomCommentary;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@DatabaseSetups({@DatabaseSetup(value = "/preset/clean.xml", type = DatabaseOperation.DELETE_ALL),
        @DatabaseSetup(value = "/preset/set_commentary.xml", type = DatabaseOperation.CLEAN_INSERT)
})

public class CommentaryServiceImplIT {

    @Autowired
    private CommentaryService commentaryService;

    @Autowired
    private PostRegisterService postRegisterService;

    private static PostRegister POST_REGISTER;


    @Before
    public void setUp() {
        PostRegister found = postRegisterService.findOne(0L).get();
        assertNotNull(found);
        assertPersist(found);

        POST_REGISTER = new PostRegister();
        POST_REGISTER.setId(found.getId());
    }

    @Test
    public void test_save_Commentary_Success() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryService.save(randCommentary);
        assertPersist(saved);
        assertFieldsEquals(randCommentary, saved);
    }

    @Test(expected = Exception.class)
    public void test_save_NotSetReferencePostRegisterCommentary_Exception() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        Commentary saved = commentaryService.save(randCommentary);
    }

    @Test
    public void test_findOne_ById_Success() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryService.save(randCommentary);
        assertPersist(saved);
        assertFieldsEquals(randCommentary, saved);
        Optional<Commentary> optCommentary = commentaryService.findOne(saved.getId());
        Commentary found = optCommentary.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(commentaryService.exists());
        //save
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryService.save(randCommentary);
        assertFieldsEquals(randCommentary, saved);

        assertTrue(commentaryService.exists());
        //delete
        commentaryService.delete(saved.getId());
        assertFalse(commentaryService.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(commentaryService.exists());
        //save
        Commentary randCommentary1 = buildRandomCommentary();
        randCommentary1.setPostRegister(POST_REGISTER);
        Commentary saved1 = commentaryService.save(randCommentary1);
        assertFieldsEquals(randCommentary1, saved1);

        Commentary randCommentary2 = buildRandomCommentary();
        randCommentary2.setPostRegister(POST_REGISTER);
        Commentary saved2 = commentaryService.save(randCommentary2);
        assertFieldsEquals(randCommentary2, saved2);

        Page<Commentary> page = commentaryService.findAll(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), is(page.getContent()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(commentaryService.exists());
        //save
        Commentary randCommentary1 = buildRandomCommentary();
        randCommentary1.setPostRegister(POST_REGISTER);
        Commentary saved1 = commentaryService.save(randCommentary1);
        assertFieldsEquals(randCommentary1, saved1);

        Commentary randCommentary2 = buildRandomCommentary();
        randCommentary2.setPostRegister(POST_REGISTER);
        Commentary saved2 = commentaryService.save(randCommentary2);
        assertFieldsEquals(randCommentary2, saved2);

        Page<Commentary> page1 = commentaryService.findAll(new PageRequest(0, 1));
        Page<Commentary> page2 = commentaryService.findAll(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());
        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }


}
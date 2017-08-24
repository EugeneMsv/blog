package com.nast.domain.repositories;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.nast.domain.entities.Post;
import com.nast.domain.entities.PostRegister;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static com.nast.domain.utils.AssertionUtils.assertFieldsEquals;
import static com.nast.domain.utils.AssertionUtils.assertPersist;
import static com.nast.domain.utils.DomainEntityBuilder.buildRandomString;
import static com.nast.domain.utils.DomainEntityBuilder.getPostRegisterBuilder;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@DatabaseSetups({@DatabaseSetup(value = "/preset/clean.xml", type = DatabaseOperation.DELETE_ALL),
        @DatabaseSetup(value = "/preset/postregister/set_postregister.xml", type = DatabaseOperation.CLEAN_INSERT)
})
public class PostRegisterRepositoryIT {

    private static final Random rand = new Random(System.nanoTime());
    @Autowired
    private PostRegisterRepository postRegisterRepository;

    @Autowired
    private PostRepository postRepository;

    private static Post preSavedPost;

    @Before
    public void setUp() {
        preSavedPost = postRepository.findOne(0L).get();
    }

    private PostRegister buildRandomPostRegister() {
        return getPostRegisterBuilder()
                .entity()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .post(preSavedPost)
                .buildEntity();
    }


    @Test
    public void test_save_PostRegister_Success() throws Exception {
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertPersist(saved);
        assertFieldsEquals(randPostRegister, saved);
    }


    @Test
    public void test_findOne_ById_Success() throws Exception {
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertFieldsEquals(randPostRegister, saved);
        Optional<PostRegister> optPostRegister = postRegisterRepository.findOne(saved.getId());
        PostRegister found = optPostRegister.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertFieldsEquals(randPostRegister, saved);

        assertTrue(postRegisterRepository.exists());
        //delete
        postRegisterRepository.delete(saved.getId());
        assertFalse(postRegisterRepository.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister1 = buildRandomPostRegister();
        PostRegister saved1 = postRegisterRepository.save(randPostRegister1);
        assertFieldsEquals(randPostRegister1, saved1);

        PostRegister randPostRegister2 = buildRandomPostRegister();
        PostRegister saved2 = postRegisterRepository.save(randPostRegister2);
        assertFieldsEquals(randPostRegister2, saved2);

        Page<PostRegister> page = postRegisterRepository.findAll(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), containsInAnyOrder(page.getContent().toArray()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister1 = buildRandomPostRegister();
        PostRegister saved1 = postRegisterRepository.save(randPostRegister1);
        assertFieldsEquals(randPostRegister1, saved1);

        PostRegister randPostRegister2 = buildRandomPostRegister();
        PostRegister saved2 = postRegisterRepository.save(randPostRegister2);
        assertFieldsEquals(randPostRegister2, saved2);

        Page<PostRegister> page1 = postRegisterRepository.findAll(new PageRequest(0, 1));
        Page<PostRegister> page2 = postRegisterRepository.findAll(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());

        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }

}
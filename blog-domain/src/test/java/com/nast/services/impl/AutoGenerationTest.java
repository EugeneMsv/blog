package com.nast.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class AutoGenerationTest {


    @Test
    public void autoGen_Success() throws Exception {
        System.out.println("AutoGenerationTest.autoGen_Success");
    }


}
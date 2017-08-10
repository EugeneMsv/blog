package com.nast.domain.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class DomainEngineTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void model_AutoGeneration_Success() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            checkTable(metaData, "post_tag");
            checkTable(metaData, "post_attachment");
            checkTable(metaData, "attachment");
            checkTable(metaData, "postregister");
            checkTable(metaData, "commentary");
            checkTable(metaData, "post");
            checkTable(metaData, "tag");
            checkTable(metaData, "PERSISTENT_LOGINS");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkTable(DatabaseMetaData metaData, String tableName) throws SQLException {
        ResultSet tables = metaData.getTables(null, null, tableName, null);
        if (!tables.next()) {
            tables = metaData.getTables(null, null, tableName.toUpperCase(), null);
            assertTrue(tables.next());
            assertFalse(tables.next());
        } else {
            assert true;
            assertFalse(tables.next());
        }

    }


}
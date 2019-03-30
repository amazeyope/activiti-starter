/*
 * @(#) ShareniuDbSqlSession
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author ningyp
 * <br> 2019-03-30 17:26:32
 */

package org.activiti.examples.sharedb;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.persistence.cache.EntityCache;

import java.sql.Connection;

import lombok.extern.log4j.Log4j2;

/**
 * @author Yope
 */
@Log4j2
public class ShareDbSqlSession extends DbSqlSession {


    public ShareDbSqlSession(DbSqlSessionFactory dbSqlSessionFactory, EntityCache entityCache) {
        super(dbSqlSessionFactory, entityCache);
    }

    public ShareDbSqlSession(DbSqlSessionFactory dbSqlSessionFactory, EntityCache entityCache, Connection connection, String catalog, String schema) {
        super(dbSqlSessionFactory, entityCache, connection, catalog, schema);
    }

    @Override
    protected void dbSchemaCreateEngine() {
        super.dbSchemaCreateEngine();
        log.info("dbSchemaCreateEngine 创建引擎的时候加一下自定义的 可以重写其他方法进行触发");
        executeMandatorySchemaResource("create", "sharedb");
    }


    @Override
    public String getResourceForDbOperation(String directory, String operation, String component) {
        String databaseType = dbSqlSessionFactory.getDatabaseType();
        // 如果是其他数据库类型也可自定义 sql
        log.info("databaseType {}", databaseType);
        log.info("directory= {} operation= {} component= {} ", directory, operation, component);
        String resource = "";
        if ("create".equals(operation) && "sharedb".equals(component)) {
            resource = "sharedb/activiti.mysql.create.engine.sql";
        } else {
            resource = super.getResourceForDbOperation(directory, operation, component);
        }
        log.info("resource={}", resource);
        return resource;
    }
}
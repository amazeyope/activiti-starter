/*
 * @(#) ShareniuDbSqlSessionFactory
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author ningyp
 * <br> 2019-03-30 17:26:32
 */

package org.activiti.examples.sharedb;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;

import java.sql.SQLException;

/**
 * @author Yope
 */

public class ShareDbSqlSessionFactory extends DbSqlSessionFactory {



    @Override
    public Class<?> getSessionType() {
        return DbSqlSession.class;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        ShareDbSqlSession dbSqlSession = new ShareDbSqlSession(this, commandContext.getEntityCache());
        if (this.getDatabaseSchema() != null && this.getDatabaseSchema().length() > 0) {
            try {
                dbSqlSession.getSqlSession().getConnection().setSchema(this.getDatabaseSchema());
            } catch (SQLException var5) {
                throw new ActivitiException("Could not set database schema on connection", var5);
            }
        }

        if (this.getDatabaseCatalog() != null && this.getDatabaseCatalog().length() > 0) {
            try {
                dbSqlSession.getSqlSession().getConnection().setCatalog(this.getDatabaseCatalog());
            } catch (SQLException var4) {
                throw new ActivitiException("Could not set database catalog on connection", var4);
            }
        }

        return dbSqlSession;
    }
}
/*
 * @(#) ActivitiConfig
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2019
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author ningyp
 * <br> 2019-03-24 16:12:30
 */

package org.activiti.examples;

import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.examples.sharedb.ShareDbSqlSessionFactory;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.io.IOException;

/**
 * activiti配置文件
 * AbstractProcessEngineAutoConfiguration在activiti-spring-boot-starter-basic下
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
    static final String NAME = "master";

    //注入数据源和事务管理器
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
        @Qualifier("dataSource") DataSource dataSource,
        @Qualifier("transactionManager") PlatformTransactionManager transactionManager,
        SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration configuration
            = this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
        ShareDbSqlSessionFactory shareDbSqlSessionFactory=new ShareDbSqlSessionFactory();
        configuration.setDbSqlSessionFactory(shareDbSqlSessionFactory);
        return configuration;
    }
}
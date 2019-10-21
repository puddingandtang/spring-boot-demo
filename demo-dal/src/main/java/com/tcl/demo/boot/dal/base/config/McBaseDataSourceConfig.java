package com.tcl.demo.boot.dal.base.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidFilterConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.stat.DruidWebStatFilterConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = {"com.tcl.demo.boot.dal.base"},
        sqlSessionFactoryRef = "mcBaseSqlSessionFactory"
)
@Import(value = {
        DruidSpringAopConfiguration.class,
        DruidStatViewServletConfiguration.class,
        DruidWebStatFilterConfiguration.class,
        DruidFilterConfiguration.class})
public class McBaseDataSourceConfig {


    /**
     * 构建 DataSource
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.mc-base")
    public DataSource mcBaseDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 构建 SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory mcBaseSqlSessionFactory(@Qualifier("mcBaseDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //读取mybatis小配置文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:dal/mapper/base/sqlMapper-*.xml"));
        bean.setTypeAliasesPackage("com.tcl.demo.boot.dal.base.dataobject");
        return bean.getObject();
    }

    /**
     * 构建 事务
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager mcBaseTransactionManager(@Qualifier("mcBaseDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate mcBaseSqlSessionTemplate(@Qualifier("mcBaseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

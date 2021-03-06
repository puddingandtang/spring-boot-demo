package com.tcl.demo.boot.dal.coupon.config;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mc_coupon数据库配置
 * 注解配置和xml配置其实是一样的，只是配置模式不同而已
 * <p>
 * step1：com.tcl.demo.boot.dal.user.config.McUserDataSourceConfig#mcUserDataSource()
 * step2：com.tcl.demo.boot.dal.user.config.McUserDataSourceConfig#mcUserSqlSessionFactory(javax.sql.DataSource)
 * step3：@MapperScan
 */
@Configuration
@MapperScan(
        basePackages = {"com.tcl.demo.boot.dal.coupon"},
        sqlSessionFactoryRef = "mcCouponSqlSessionFactory"
)
@Import(value = {
        DruidSpringAopConfiguration.class,
        DruidStatViewServletConfiguration.class,
        DruidWebStatFilterConfiguration.class,
        DruidFilterConfiguration.class})
public class McCouponDataSourceConfig {

    /**
     * 构建 DataSource
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.mc-coupon")
    public DataSource mcCouponDataSource() {
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
    public SqlSessionFactory mcCouponSqlSessionFactory(@Qualifier("mcCouponDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //读取mybatis小配置文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:dal/mapper/coupon/sqlMapper-*.xml"));
        bean.setTypeAliasesPackage("com.tcl.demo.boot.dal.coupon.dataobject");
        return bean.getObject();
    }

    /**
     * 构建 事务
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager mcCouponTransactionManager(@Qualifier("mcCouponDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate mcCouponSqlSessionTemplate(@Qualifier("mcCouponSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

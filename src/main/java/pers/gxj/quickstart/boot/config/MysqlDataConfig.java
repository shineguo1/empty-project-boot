package pers.gxj.quickstart.boot.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * mysql数据源配置
 *
 * @author sean
 * @date 2022/3/1
 */
@Configuration
@MapperScan(value = {"com.blockin.subgraph.dal.mysql.mapper"}, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataConfig {

    @Value("${mysql.data.core.db.driver}")
    private String driveClassName;
    @Value("${mysql.data.core.url}")
    private String jdbcUrl;
    @Value("${mysql.data.core.db.username}")
    private String userName;
    @Value("${mysql.data.core.db.write.password}")
    private String password;
    @Value("${mysql.data.core.db.maxPoolSize}")
    private int maxPoolSize;
    @Value("${mysql.data.core.db.minPoolSize}")
    private int minIdle;
    @Value("${mysql.data.core.db.connectionTimeout}")
    private long connectionTimeout;
    @Value("${mysql.data.core.db.idleTimeout}")
    private long idleTimeout;
    @Value("${mysql.data.core.db.maxLifetime}")
    private long maxLifetime;
    @Value("${mysql.data.core.db.connectionInitSql}")
    private String connectionInitSql;
    @Value("${mysql.data.core.db.connectionTestQuery}")
    private String connectionTestQuery;

    @Bean("dataSource") //新建bean实例
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(driveClassName);
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);
        hikariDataSource.setMaximumPoolSize(maxPoolSize);
        hikariDataSource.setMinimumIdle(minIdle);
        hikariDataSource.setConnectionTimeout(connectionTimeout);
        hikariDataSource.setIdleTimeout(idleTimeout);
        hikariDataSource.setMaxLifetime(maxLifetime);
        hikariDataSource.setConnectionTestQuery(connectionTestQuery);
        hikariDataSource.setConnectionInitSql(connectionInitSql);
        return hikariDataSource;
    }

    @Bean(name = "templateReport")
    public JdbcTemplate templateXbRisk() {
        return new JdbcTemplate(dataSource());
    }

    @Bean("mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mysql/*.xml"));
        bean.setDataSource(dataSource);
        MybatisConfiguration config = new MybatisConfiguration();
        // 开启驼峰命名映射
        config.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(config);
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MysqlMetaObjectHandler());
        bean.setGlobalConfig(globalConfig);
        return bean.getObject();
    }


}


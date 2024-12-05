package com.utils.datasource;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceUtil {

	    private static final HikariDataSource dataSource;

	    static {
	        // 配置 HikariCP
	        HikariConfig config = new HikariConfig();
	        config.setJdbcUrl("jdbc:mysql://localhost:3306/voicebus?serverTimezone=Asia/Taipei");
	        config.setUsername("root");
	        config.setPassword("tony797977");
	        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

	        // HikariCP 相關設定
	        config.setMaximumPoolSize(20);
	        config.setMinimumIdle(5);
	        config.setIdleTimeout(60000);
	        config.setMaxLifetime(1800000);
	        config.setConnectionTimeout(30000);

	        dataSource = new HikariDataSource(config);
	    }
	 // 公用方法：取得 DataSource
	    public static DataSource getDataSource() {
	        return dataSource;
	    }
	}


package ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


// Ex : xml 
//<import resource="classpath:appCtx2.xml" />

@Configuration
@Import({AppCtx2.class})
public class AppCtx3 {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

}
/*
//Hikari CP
//@Bean(destroyed = "close")
public HikariDataSource dataSource() {
	HikariDataSource dataSource = new HikariDataSource();
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/webapp");
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	return dataSource;
}
*/



*/
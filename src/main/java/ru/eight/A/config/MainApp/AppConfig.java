//package ru.eight.A.config.MainApp;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class AppConfig {
//    @Bean
//    PayService payService(PayRepository payRepository) {
//       return new PayService(payRepository);
//    }
//
////    @Bean
////    public DataSource myDataSource() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName("org.h2.Driver");
////        dataSource.setUrl("jdbc:h2:~/test");
////        dataSource.setUsername("sa");
////        dataSource.setPassword("");
////
////        return dataSource;
////    }
////
////    @Bean
////    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
////        return new JdbcTemplate(dataSource);
////    }
//}

package madbrains.config;

import lombok.extern.slf4j.Slf4j;
import madbrains.catalogues.Catalogue;
import madbrains.catalogues.MyComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@ComponentScan("madbrains.catalogues")
public class ComparatorConfig {
    @Bean
    @Primary
    public MyComparator getComp() {
        MyComparator catalogue = new MyComparator();
        return catalogue;
    }

    @Bean("CompName")
    public String getComparator() {
        return "Default Catalogue";
    }
}

package madbrains.config;

import lombok.extern.slf4j.Slf4j;
import madbrains.catalogues.Catalogue;
import org.springframework.context.annotation.*;

@Slf4j
@Configuration
@ComponentScan("madbrains.catalogues")
public class CatalogueConfig {
    @Bean
    @Primary
    public Catalogue getITCompany() {
        Catalogue catalogue = new Catalogue();
        return catalogue;
    }

    @Bean("CatalogueName")
    public String getListName() {
        return "Default Catalogue";
    }
}

package cat.udl.eps.softarch.unicloud.config;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class ExposeIdConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry regs) {
        config.exposeIdsFor(University.class);
        config.exposeIdsFor(Resource.class);
    }
}

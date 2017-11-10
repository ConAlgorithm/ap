package network.relationship;

import catfish.base.StartupConfig;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(BeansConfiguration.class)
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "network.relationship.repository")
public class CatfishNeo4jConfiguration extends Neo4jConfiguration {
    public static final String URL = StartupConfig.get("catfish.graph.hapath");
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "network.relationship.domain");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI(URL);
        return config;
    }

}

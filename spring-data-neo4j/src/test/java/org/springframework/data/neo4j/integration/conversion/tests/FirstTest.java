package org.springframework.data.neo4j.integration.conversion.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.examples.galaxy.context.GalaxyContext;
import org.springframework.data.neo4j.examples.galaxy.domain.World;
import org.springframework.data.neo4j.examples.galaxy.repo.WorldRepository;
import org.springframework.data.neo4j.integration.conversion.tests.FirstTest.MyConfig;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GalaxyContext.class, MyConfig.class})
@Transactional
@Rollback
public class FirstTest {

    @Autowired
    WorldRepository worldRepository;

    @Test
    public void name() throws Exception {
        worldRepository.save(new World("Tatooine", 0));
    }

    @Configuration
    public static class MyConfig {

        @Bean
        public SessionFactory getSessionFactory() {
            org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration();
            configuration.driverConfiguration().setDriverClassName(EmbeddedDriver.class.getCanonicalName());
            return new SessionFactory(configuration, "org.springframework.data.neo4j.examples.galaxy.domain");
        }
    }
}

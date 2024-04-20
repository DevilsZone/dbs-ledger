package org.dbs.ledger.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import managers.jwt.AccessTokenAlgorithm;
import managers.jwt.configurations.JwtManagerConfiguration;
import managers.jwt.impl.JwtAccessTokenManager;
import managers.jwt.impl.RsaAccessTokenAlgorithmImpl;
import org.dbs.ledger.configuration.contexts.RequestContext;
import org.dbs.ledger.configuration.contexts.UserContext;
import org.dbs.ledger.transformer.JwtTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.data.mongodb.MongoDatabaseFactory;

@Configuration
@EnableWebMvc
@EnableMongoAuditing
public class ApplicationConfiguration {
    @Bean
    @Primary
    public JsonMapper getJsonMapper() {
        return JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .findAndAddModules()
                .build();
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    @Bean
    public RequestContext getRequestContext() {
        return new RequestContext();
    }

    @Bean
    public UserContext getUserContext() {
        return new UserContext();
    }

    @Bean
    public JwtAccessTokenManager getJwtTokenManager(JwtConfiguration jwtConfiguration, JwtTransformer jwtTransformer) {
        AccessTokenAlgorithm accessTokenAlgorithm = new RsaAccessTokenAlgorithmImpl(
                jwtConfiguration.getRsaPublicKey(),
                jwtConfiguration.getRsaPrivateKey()
        );
        JwtManagerConfiguration jwtManagerConfiguration = jwtTransformer.convertJwtConfigurationToJwtManagerConfiguration(jwtConfiguration);
        return new JwtAccessTokenManager(accessTokenAlgorithm, jwtManagerConfiguration);
    }
}

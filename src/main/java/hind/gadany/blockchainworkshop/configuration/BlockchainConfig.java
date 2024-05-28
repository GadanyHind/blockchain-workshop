package hind.gadany.blockchainworkshop.configuration;

import hind.gadany.blockchainworkshop.entity.Blockchain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BlockchainConfig {
    @Bean(name = "blockchainConfigBean")
    public Blockchain blockchain() {
        return new Blockchain();
    }
}
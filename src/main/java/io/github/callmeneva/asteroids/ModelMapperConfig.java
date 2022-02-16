package io.github.callmeneva.asteroids;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        var mapperConfig = mapper.getConfiguration();
        mapperConfig.setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper;
    }
}

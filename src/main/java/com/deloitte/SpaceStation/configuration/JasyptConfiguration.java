package com.deloitte.SpaceStation.configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Configuration;

// Configuration class for encryption data in application.properties
@Configuration
@EnableEncryptableProperties
public class JasyptConfiguration {
}

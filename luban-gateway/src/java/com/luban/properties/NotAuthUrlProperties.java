package com.luban.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;
@Data
@ConfigurationProperties("luban.gateway")
public class NotAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}

package com.jamie.web.resource.freemark;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class FreeMarkConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freemark = new FreeMarkerConfigurer();

        freemark.setTemplateLoaderPaths("classpath:/templates/");

        Properties properties = new Properties();
        properties.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.put("number_format", "0.######");
        properties.put("defaultEncoding", "UTF-8");
        properties.put("url_escaping_charset", "UTF-8");
        properties.put("locale", "zh_CN");
        properties.put("template_update_delay", "0");

        freemark.setFreemarkerSettings(properties);
        freemark.setPreferFileSystemAccess(false);

        Map<String, Object> map = new HashMap<>();
        map.put("block", new BlockDirective());
        map.put("override", new OverrideDirective());
        map.put("extends", new ExtendsDirective());

        freemark.setFreemarkerVariables(map);

        return freemark;
    }

}

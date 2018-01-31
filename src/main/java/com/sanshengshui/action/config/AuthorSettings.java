package com.sanshengshui.action.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 穆书伟
 * @date 2018年1月29日 上午3:29
 * @description 类型安全的配置(基于properties)
 */
@Component
@ConfigurationProperties(prefix = "author")
@PropertySource("classpath:config/author.properties")
public class AuthorSettings {

    private String name;

    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}

package cn.edu.zzu.oj.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "zzuoj.filter")
@Getter
@Setter
public class FilterProperties {
    private List<String> allowPaths;
}
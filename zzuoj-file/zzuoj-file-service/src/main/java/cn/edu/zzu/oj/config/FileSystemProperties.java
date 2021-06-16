package cn.edu.zzu.oj.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "zzuoj.filesys")
public class FileSystemProperties {
    private String baseDir; // 工作目录
}
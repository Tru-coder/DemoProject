package com.example.demoproject.Storage;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StorageProperties {
    private String location;

}

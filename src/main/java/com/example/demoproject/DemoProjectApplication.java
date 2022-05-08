package com.example.demoproject;

import com.example.demoproject.Storage.StorageProperties;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.python.util.PythonInterpreter;
import org.testng.annotations.Test;

import static java.util.Optional.empty;
import static javatests.TestSupport.assertEquals;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("print('String to execute')");
        }
    }
    @Test
    public void givenPythonInterpreter_whenPrintExecuted_thenOutputDisplayed() {

    }


}

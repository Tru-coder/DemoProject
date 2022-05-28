package com.example.demoproject.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    private static String ENCRYPTION_KEY;

    public Utility(@Value("${my-security.encryptionKey}") String ENCRYPTION_KEY){
        Utility.ENCRYPTION_KEY = ENCRYPTION_KEY;
    }

    public static String getEncryptionKey(){
        return  ENCRYPTION_KEY;
    }
}

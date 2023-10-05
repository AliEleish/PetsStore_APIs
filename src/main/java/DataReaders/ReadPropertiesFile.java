package DataReaders;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesFile {
    Properties properties;

    public ReadPropertiesFile(String filepath) {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(filepath);
            properties.load(file);
            file.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  String getPropertyValue(String key) {
            String value = properties.getProperty(key);
            System.out.println(value);
           return value;
        }
    }


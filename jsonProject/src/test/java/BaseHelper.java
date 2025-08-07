import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseHelper  {

//    static String filePath = "C:\\Users\\pbotty.s\\git\\myProject\\jsonProject\\src\\main\\resources\\configFiles\\DB.properties";
    static  Properties prop;
    ConfigFileNames configFileNames;
    static String filePath = findSrcPath() + "\\" + findConfigFilePath() + "\\" + ConfigFileNames.DB + ".properties";

    public static Properties readPropertyFile()   {
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

//    public static String readAllDataFromPropertyFile() throws Exception {
//
//        Map<String, String> propertiesMap = new HashMap<>();
//        try  {
//            Properties properties = new Properties();
//            FileInputStream fileInputStream = new FileInputStream(filePath);
//            if (fileInputStream == null) {
//                System.out.println("Unable to find " + filePath);
//                return propertiesMap.toString();
//            }
//            properties.load(fileInputStream);
//            for (String key : properties.stringPropertyNames()) {
//                String value = properties.getProperty(key);
//                propertiesMap.put(key, value);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return propertiesMap.toString();
//    }

    Path projectPath = Paths.get("").toAbsolutePath();

    // Get the absolute path of the target file/directory
//    Path targetPath = Paths.get("src", "main/resource", "java", "com", "example", "resource").toAbsolutePath();

   //get the resource/configFile path
    Path configFiles = Paths.get("main/resource/configFiles");

       Path src = Paths.get("src").toAbsolutePath();

    public static Path findSrcPath(){
        Path src = Paths.get("src").toAbsolutePath();
        return src;
    }

    public static Path findConfigFilePath(){
        Path configFiles = Paths.get("main/resource/configFiles");
        return configFiles;
    }
}

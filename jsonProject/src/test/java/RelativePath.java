import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RelativePath {

    Properties properties;

    public static void main(String[] args) {

       ConfigFileNames configFileNames;
        // Get the absolute path of the project (or a reference directory)
        Path projectPath = Paths.get("").toAbsolutePath();

        // Get the absolute path of the target file/directory
//        Path targetPath = Paths.get("src", "main/resource", "java", "com", "example", "resource").toAbsolutePath();
        Path targetPath = Paths.get("src").toAbsolutePath();
        Path configFiles = Paths.get("main/resource/configFiles");

        // Calculate the relative path
        Path relativePath = projectPath.relativize(targetPath);

        System.out.println("Project Path: " + projectPath);
        System.out.println("Target Path: " + targetPath);
        System.out.println("Relative Path: " + relativePath);
        System.out.println("configFiles: " + configFiles);
        System.out.println("configFiles: " + configFiles +"\\" + ConfigFileNames.DB + ".properties");

        RelativePath relativePath1 = new RelativePath();
        System.out.println(relativePath1.getProperty("informix_db_url"));

    }

public void configLoader(){
     properties = new Properties();
    try(InputStream input = getClass().getClassLoader().getResourceAsStream("DB.properties")){
        if(input == null){
            throw new IOException("Configuration file not found");
        }properties.load(input);
    }catch (IOException e){
        e.printStackTrace();
    }
}
public String getProperty(String key){
        return properties.getProperty(key);
}
}

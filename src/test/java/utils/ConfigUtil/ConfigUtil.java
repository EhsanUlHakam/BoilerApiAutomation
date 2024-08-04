package utils.ConfigUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {
    public static Logger log = LogManager.getLogger(ConfigUtil.class.getName());

    public static Properties getConfig(String filename) {
        Properties myProp = new Properties();
        try {
            //            File configExternalFile = new File(System.getProperty("user.dir")+"/config.properties");
            //            File configExternalFile = new
            // File(System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"allure-results"+File.separator+filename+".properties");
            File configExternalFile = new File(
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
                            + "java" + File.separator + "config" + File.separator + filename + ".properties");
            if (configExternalFile.exists()) {
                // log.info("Using config: "+ configExternalFile.getAbsolutePath());
                myProp.load(new FileInputStream(configExternalFile));
            } else {
                log.info("File Not found " + filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myProp;
    }

    public static Map<String, Object> getYaml() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(".//src//test//resources//config.yml"));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        return data;
    }
}

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Created by magnus
 */
public class Main {
    private static Tomcat tomcat;

    public static void main(String [ ] args)
    {
        tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        String port = System.getenv("PORT"); //Environment variable on server
        if (port==null){
            port="80";
        }

        tomcat.setPort(Integer.parseInt(port));
        tomcat.getConnector();

        tomcat.addWebapp("/lektion10", new File("Lektion 10/WebContent").getAbsolutePath());
        tomcat.addWebapp("/lektion11", new File("Lektion 11/WebContent").getAbsolutePath());
        tomcat.addWebapp("/lektion12", new File("Lektion 12/WebContent").getAbsolutePath());

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();

    }
}



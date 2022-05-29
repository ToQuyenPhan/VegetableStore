package quyen.vegetablestore.listeners;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;


/**
 *
 * @author To Quyen Phan
 */
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {//Khởi tạo Listener, đọc vị trí tệp log4j properties 
        ServletContext context = sce.getServletContext();//Gọi servlet context
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);//xây dựng đường dẫn đến file log4j properties
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}

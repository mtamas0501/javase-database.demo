package hu.ulyssys.java.course.database.oraifeladat;
import hu.ulyssys.java.course.database.oraifeladat.entity.Student;
import hu.ulyssys.java.course.database.oraifeladat.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;
//Nem csináljuk Singleton
public class DataBaseSessionProvider {

    private static DataBaseSessionProvider instance = null;
    private static Session sessionObj;

    private DataBaseSessionProvider() {
        configDatabase();
    }

    private void configDatabase() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
        Properties properties = new Properties();
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "Admin1234");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/demo");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        sessionObj = configuration.buildSessionFactory(serviceRegistry).openSession();
    }

    public static DataBaseSessionProvider getInstance() {
        if (instance == null) {
            instance = new DataBaseSessionProvider();
        }
        return instance;
    }

    public Session getSessionObj() {
        return sessionObj;
    }
}
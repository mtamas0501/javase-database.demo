package hu.ulyssys.java.course.database.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DDLMain {

    private static final String DATABASE_URL="jdbc:postgresql://localhost:5432/demo";
    private static final String DATABASE_USER="postgres";
    private static final String DATABASE_PASSWORD="Admin1234";

    public static void main(String[] args) {
        try {
            //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "Admin1234");

            Properties properties=new Properties();
            properties.setProperty("user",DATABASE_USER);
            properties.setProperty("password",DATABASE_PASSWORD);
            DriverManager.getConnection(DATABASE_URL,properties);

            //   truncateDemo(connection);
            //  dropTableDemo(connection);
            // createTableDemo(connection);
            // alterTableAddColumn(connection);
            //createTable(connection,"apple","apple_name");
            //createTable(connection,"pear","pear_name");
            //createTable(connection,"cherry","cherry_name");
            System.out.println("Sikeresen végre hajtódott a program");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("Vége a programunknak");
        }
    }

    //alma,körte,cseresznye
    //alma_sequence,körte_sequence,
    private static void createTable(Connection connection, String tableName, String columnName) throws SQLException{
        Statement statement=connection.createStatement();
        statement.execute("CREATE TABLE "+tableName+" ( id serial not null, "+columnName+
                " character varying (255), CONSTRAINT "+tableName+"_sequence PRIMARY KEY (id))");
    }

    private static void alterTableAddColumn(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("ALTER TABLE adress ADD test BOOLEAN");
    }

    private static void createTableDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //Create table demo
        statement.execute("CREATE TABLE public.address\n" +
                "(\n" +
                "    id serial NOT NULL,\n" +
                "    employee_id integer NOT NULL,\n" +
                "    value character varying(255) COLLATE pg_catalog.\"default\",\n" +
                "    CONSTRAINT address_pkey PRIMARY KEY (id),\n" +
                "    CONSTRAINT employee_foreign_key FOREIGN KEY (employee_id)\n" +
                "        REFERENCES public.employee (id) MATCH SIMPLE\n" +
                "        ON UPDATE NO ACTION\n" +
                "        ON DELETE NO ACTION\n" +
                ")\n");
    }

    public static void truncateDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        // truncate SQL parancs, kitöröljük az address tábla minden elemét
        statement.execute("truncate address");
    }

    public static void dropTableDemo(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //adatbázis tábla törlése
        statement.execute("drop table address");
    }
}
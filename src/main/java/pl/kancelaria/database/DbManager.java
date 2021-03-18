package pl.kancelaria.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.kancelaria.database.models.Case;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.database.models.Employee;
import pl.kancelaria.database.models.Task;
import pl.kancelaria.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;


public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./libraryDB";
    private static final String USER = "admin";
    private static final String PASS =  "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){

        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD,USER,PASS);
        } catch (SQLException e) {
            DialogUtils.dialogError(e.getMessage());
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Case.class);
            TableUtils.createTableIfNotExists(connectionSource, Client.class);
            TableUtils.createTableIfNotExists(connectionSource, Employee.class);
            TableUtils.createTableIfNotExists(connectionSource, Task.class);
        } catch (SQLException e) {
            DialogUtils.dialogError(e.getMessage());
        }
    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Case.class,true);
            TableUtils.dropTable(connectionSource, Client.class,true);
            TableUtils.dropTable(connectionSource, Employee.class,true);
            TableUtils.dropTable(connectionSource, Task.class,true);
        } catch (SQLException e) {
            DialogUtils.dialogError(e.getMessage());
        }
    }

    public static void closeConnectionSource() {
        if(connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                DialogUtils.dialogError(e.getMessage());
            }
        }
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}

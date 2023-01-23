package com.portaleps.helper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.exception.CustomException;
import com.portaleps.model.entity.Archive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.function.Consumer;

@Service
public class DatabaseHelper {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseHelper.class);

    public enum DatabaseDriver {
        mysql("com.mysql.cj.jdbc.Driver"),
        postgresql("org.postgresql.Driver");

        public final String driver;

        private DatabaseDriver(String driver) {
            this.driver = driver;
        }
    }

    public static Connection createConnection(Archive archive){
        LOGGER.info("createConnection() - start");
        if(archive != null){
            try {
                String driver = mapDriverClassName(archive.getDatabaseType());
                Class.forName(driver);
                Connection connection = DriverManager.getConnection("jdbc:" + archive.getDatabaseType() + "://" + archive.getUrl(),
                        archive.getUsername(),
                        archive.getPassword());
                LOGGER.info("createConnection() - connection created");
                return connection;
            } catch (ClassNotFoundException e) {
                LOGGER.error("createConnection() - className not found");
                e.printStackTrace();
                throw new CustomException("Class Name not found", HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (SQLException e) {
                LOGGER.error("createConnection() - bad connection");
                e.printStackTrace();
                throw new CustomException("Bad connection", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        LOGGER.info("createConnection() - end");
        return null;
    }

    public void performQuery(Archive archive, String query, ObjectNode filters, Consumer<ResultSet> function){
        LOGGER.info("performQuery() - start");
        Connection connection = createConnection(archive);
        try {
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(filters != null && !filters.isEmpty()){
                QueryBuilder.applyFilters(filters, statement);
            }
            ResultSet rs = statement.executeQuery();
            function.accept(rs);
            rs.close();
            statement.close();
            connection.close();
            LOGGER.info("performQuery() - connection closed");
        } catch (SQLException e) {
            LOGGER.error("performQuery() - query not executed: " + query);
            e.printStackTrace();
            throw new CustomException("Bad query", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("performQuery() - end");
    }

    // PRIVATE METHODS

    private static String mapDriverClassName(String driver){
        LOGGER.info("mapDriverClassName() - start");
        try{
            LOGGER.info("mapDriverClassName() - class name found");
            LOGGER.info("mapDriverClassName() - end");
            return DatabaseDriver.valueOf(driver).driver;
        } catch (Exception e){
            LOGGER.error("mapDriverClassName() - className not found");
            LOGGER.info("mapDriverClassName() - end");
            throw new CustomException("Class name not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
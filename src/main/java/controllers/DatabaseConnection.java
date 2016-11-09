package controllers;

import org.apache.commons.dbcp2.BasicDataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Asta on 2016-10-28.
 */
public class DatabaseConnection {
//    protected BasicDataSource dataSource;

    static public BasicDataSource dataSource() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        BasicDataSource dataSource = new BasicDataSource();

        if (dbUri.getUserInfo() != null) {
            dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
            dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
        }
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setInitialSize(1);
        return dataSource;
    }
}
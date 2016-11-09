package controllers;

import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Asta on 2016-11-10.
 */
public final class Database {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        try {
            URI dbUri = new URI(System.getenv("HEROKU_POSTGRESQL_RED_URL"));
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            if (dbUri.getUserInfo() != null) {
                dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
                dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
            }
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(dbUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Database() {
        //
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}

package controllers;

import models.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Asta on 2016-10-20.
 */
public class UsersController extends DatabaseConnection{
    private BasicDataSource dataSource;

    public UsersController() throws SQLException, URISyntaxException {
        setDataSource();
        this.dataSource = data;

    }

    public void createTable() {
        String query = "CREATE TABLE `users` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(20) NOT NULL , " +
                "`email` VARCHAR(20) NOT NULL , `password` VARCHAR(20) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET utf8"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable() {
        String query = "DROP TABLE users"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Įvykdome SQL užklausą
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int save(User a) {
        int status = 0;
        String query = "INSERT INTO users (name,password,email)" +
                " VALUES ('" + a.getName() + "', '" + a.getPassword() + "', '" + a.getEmail() + "')"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            status = statement.executeUpdate(query); // Įvykdome SQL užklausą
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean userExists(String email, String password) {
        String query = "SELECT * FROM users WHERE email = '" + email + "'" + " AND password = '" + password + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next() && resultSet != null) {      // resultSet != null
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User u = new User();
        String query = "SELECT * FROM users WHERE email = '" + email + "'" + " AND password = '" + password + "'"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {      // resultSet != null
                u.setName(resultSet.getString("name"));
                u.setEmail(resultSet.getString("email"));
                u.setId(resultSet.getInt("id"));
                u.setPassword("empty");   // Ar reikia pasiimti slaptazodi?
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User getUserById(int id) {
        User u = new User();
        String query = "SELECT * FROM users WHERE id = '" + id + "'"; // SQL užklausa
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {      // resultSet != null
                u.setName(resultSet.getString("name"));
                u.setEmail(resultSet.getString("email"));
                u.setId(resultSet.getInt("id"));
                u.setPassword("empty");   // Ar reikia pasiimti slaptazodi?
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }


}

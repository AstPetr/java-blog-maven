package controllers;

import models.Article;
import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Asta on 2016-10-17.
 */
public class ArticlesController extends DatabaseConnection{


    private BasicDataSource dataSource;
    private Connection connection = null;
    PreparedStatement statement = null;
    public ArticlesController() throws URISyntaxException, SQLException {
        setDataSource();
        this.dataSource = data;
    }

    public void createTable(String name) throws SQLException {
        String query = "CREATE TABLE " + name
                + " (id INTEGER NOT NULL, title VARCHAR(255), body VARCHAR(10000), created_at DATE, updated_at DATA, PRIMARY KEY (id))"; // SQL užklausa
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
//    String query = "DROP TABLE " + name; // SQL užklausa
    public void deleteTable(String name) throws SQLException {
        String query = "DROP TABLE " + name; // SQL užklausa
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<Article> getAllArticles() throws SQLException {
        String query = "SELECT id, title, body, created_at, updated_at, userID FROM articles";
        List<Article> list = new ArrayList<Article>();
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article a = new Article();
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setUserId(resultSet.getInt("userId"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public int save(Article a) throws SQLException {
        int status = 0;
        String query = "INSERT INTO articles (title,body,created_at,updated_at,userId)" +
                " VALUES ('" + a.getTitle() + "', '" + a.getBody() + "', '" + a.getCreated_at() + "', '" + a.getUpdated_at() + "', '" + a.getUserId() + "')"; // SQL užklausa
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return status;
    }

    public int delete(int id) throws SQLException {
        int status = 0;
        String query = "DELETE FROM articles WHERE id=" + id; // SQL užklausa
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return status;
    }

    public int update(Article a) throws SQLException {
        int status = 0;
        String query = "UPDATE articles SET title = '" + a.getTitle() + "', body = '" + a.getBody() + "', updated_at = '" +
                a.getUpdated_at() + "' WHERE ID = " + a.getId();
        // System.out.println(query);
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return status;
    }



    public Article getArticleById(int id) throws SQLException {
        Article a = new Article();
        String query = "SELECT * FROM articles WHERE ID = " + id; // SQL užklausa
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setBody(resultSet.getString("body"));
                a.setCreated_at(resultSet.getString("created_at"));
                a.setUpdated_at(resultSet.getString("updated_at"));
                a.setUserId(resultSet.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return a;
    }

    public List<Article> getAllArticlesIdByUserId(int userId) throws SQLException {
        String query = "SELECT id, title, body, created_at, updated_at FROM articles WHERE userID = '" + userId + "'";
        List<Article> list = new ArrayList<Article>();
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article a = new Article();
                a.setId(resultSet.getInt("ID"));
                a.setTitle(resultSet.getString("title"));
                a.setBody(resultSet.getString("body"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }


}

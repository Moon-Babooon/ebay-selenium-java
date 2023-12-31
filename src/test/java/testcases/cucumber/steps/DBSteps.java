package testcases.cucumber.steps;

import base.BrowserType;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import utils.DataHolder;

import java.sql.*;

public class DBSteps {

    @When("get the {string} and {string} from the database")
    public void getCredentials(String username, String password) {
        getDataFromDB(username, password);
    }

    @SneakyThrows
    private void getDataFromDB(String keyUser, String keyPasswd) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(getSQLHost(), "testUsername", "testPassword");
            statement = connection.createStatement();
            String queryBase = "SELECT %s FROM %s WHERE website='%s'";
            String tableName = "accounts";
            String columns = "username, password";
            String keyValue = "ebay.com";
            String query = String.format(queryBase, columns, tableName, keyValue);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                DataHolder.getInstance().put(keyUser, username);
                DataHolder.getInstance().put(keyPasswd, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String getSQLHost() {
        // jdbc:postgresql://postgres-db-1:5432/TestDatabase
        String typeChrome = System.getProperty("driver.type", "DOCKER_CHROME");
        String typeFirefox = System.getProperty("driver.type", "DOCKER_FIREFOX");
        BrowserType dockerChrome = BrowserType.valueOf(typeChrome);
        BrowserType dockerFirefox = BrowserType.valueOf(typeFirefox);
        if (BrowserType.DOCKER_CHROME.equals(dockerChrome) || BrowserType.DOCKER_FIREFOX.equals(dockerFirefox)) {
            return "jdbc:postgresql://postgres-db-1:5432/TestDatabase";
        }
        return "jdbc:postgresql://localhost:5432/TestDatabase";
    }

}

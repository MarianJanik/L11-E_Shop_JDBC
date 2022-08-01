package cz.marianjanik.ekurz;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EshopMethods implements GoodsMethods {

    private Connection callSqlDatabase() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/e-shop2",
                "eshopuser",
                "abcd123456789");
    }

    @Override
    public Item loadItemById(Integer id) throws SQLException {
        final String SELECT_BY_ID = "SELECT * FROM `item` WHERE `id`=" + id + ";";
        Connection dtbConnection = callSqlDatabase();
        Statement statement = dtbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_BY_ID);

        resultSet.next();
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setPartNo(resultSet.getString("partNo"));
        item.setSerialNo(resultSet.getString("serialNo"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        item.setNumberInStock(resultSet.getInt("numberInStock"));
        item.setPrice(resultSet.getBigDecimal("price"));

        statement.close();
        dtbConnection.close();
        return item;
    }

    @Override
    public void deleteAllOutOfStockItems() throws SQLException {
        final String DELETE_ITEM = "DELETE FROM `item` WHERE (`numberInStock`=0);";
        Connection dtbConnection = callSqlDatabase();
        Statement statement = dtbConnection.createStatement();

        statement.executeUpdate(DELETE_ITEM);

        statement.close();
        dtbConnection.close();
    }

    @Override
    public List<Item> loadAllAvailableItems() throws SQLException {
        final String SELECT_ALL = "SELECT * FROM `item`;";
        Connection dtbConnection = callSqlDatabase();
        Statement statement = dtbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);

        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            Item item = new Item();
            item.setId(resultSet.getInt("id"));
            item.setPartNo(resultSet.getString("partNo"));
            item.setSerialNo(resultSet.getString("serialNo"));
            item.setName(resultSet.getString("name"));
            item.setDescription(resultSet.getString("description"));
            item.setNumberInStock(resultSet.getInt("numberInStock"));
            item.setPrice(resultSet.getBigDecimal("price"));
            items.add(item);
        };

        statement.close();
        dtbConnection.close();
        return items;
    }

    @Override
    public void saveItem(Item item) throws SQLException {
        final String INSERT_ITEM = "INSERT INTO `item` (`partNo`,`serialNo`,`name`,`description`,`numberInStock`,`price`) " +
                "VALUES ('" + item.getPartNo() + "','" + item.getSerialNo() + "','" + item.getName() + "','"
                + item.getDescription() + "'," + item.getNumberInStock() + "," + item.getPrice() + ");";
        Connection dtbConnection = callSqlDatabase();
        Statement statement = dtbConnection.createStatement();

        statement.executeUpdate(INSERT_ITEM);

        statement.close();
        dtbConnection.close();
}

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) throws SQLException {
        final String UPDATE_BY_ID = "UPDATE `item` SET `price`=" + newPrice + " WHERE `id`=" + id + ";";
        Connection dtbConnection = callSqlDatabase();
        Statement statement = dtbConnection.createStatement();

        statement.executeUpdate(UPDATE_BY_ID);

        statement.close();
        dtbConnection.close();
    }


}

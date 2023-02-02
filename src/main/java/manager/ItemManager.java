package manager;

import db.DBConnectionProvider;
import model.Item;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ItemManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();


    public void addItem(Item item) {
        String sql = "insert into item(title,price,category_Id,profile_pic,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getProfilePic());
            ps.setInt(5, item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        String sql = "select * from item";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getItemById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeItemById(int id) {
        String sql = "delete from item where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        return Item.builder().id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .price(resultSet.getDouble("price"))
                .category(categoryManager.getCategoryById(resultSet.getInt("category_id")))
                .profilePic(resultSet.getString("profile_pic"))
                .user(userManager.getUserById(resultSet.getInt("user_Id")))
                .build();
    }

    public void editItem(Item item) {
        String sql = "UPDATE item set title = ?,price = ?, profile_pic=? WHERE id = ?";  //,category_id = ?,user_id=?
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
//            ps.setInt(3, item.getCategory().getId());
            ps.setString(3, item.getProfilePic());
//            ps.setInt(5, item.getUser().getId());
            ps.setInt(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Item> getLast20Items() {
        String sql = "select * from item order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .profilePic(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Item> getLast20ItemsByCategory(int categoryId) {
        String sql = "select * from item where category_id=" + categoryId + " order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .profilePic(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Item> getAllUserItems(int userId) {
        String sql = "select * from item where user_id = " + userId;
        List<Item> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .profilePic(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
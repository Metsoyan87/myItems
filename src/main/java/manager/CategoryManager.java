package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class CategoryManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addCategory(Category category) {
        String sql = "insert into category(title,category_id) VALUES (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        String sql = "select * from category";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        String sql = "select * from category where id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeCategoryById(int id) {
        String sql = "delete from book where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        return Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    public void editCategory(Category category) {
        String sql = "update category set name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
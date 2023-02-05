
import dto.CategoryInsert;
import dto.CategoryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    String conStr;
    String userName;
    String password;

    public CategoryService(String conStr, String userName, String password) {
        this.conStr = conStr;
        this.userName = userName;
        this.password = password;
    }

    public void Insert(CategoryInsert category) {
        try (Connection con = DriverManager.getConnection(conStr, userName, password)) {
            System.out.println("Зяднаня успішне");
            String query = "INSERT INTO categories (name) " +
                    "VALUES (?)";
            PreparedStatement command = con.prepareStatement(query);
            command.setString(1, category.getName());
            command.executeUpdate();
        } catch (Exception ex) {
            System.out.println("------Bad Connection db-------" + ex.getMessage());
        }
    }

    public List<CategoryItem> GetList() {
        List<CategoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try(Connection con = DriverManager.getConnection(conStr, userName, password)) {
            PreparedStatement command = con.prepareStatement(sql);
            ResultSet resultSet = command.executeQuery();
            while(resultSet.next()) {
                CategoryItem item = new CategoryItem();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                list.add(item);
            }
        }
        catch (Exception ex) {
            System.out.println("Помилка підлкючення до БД");
        }
        return list;
    }
}

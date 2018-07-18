package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.CoolClass;
import com.codecool.queststore.model.Level;
import com.codecool.queststore.model.Mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelDAO {
    private Connection connection;
    private List<Level> levels;


    public LevelDAO() {
        connection = new ConnectionProvider().getConnection();
        levels = createLevelsList();
    }


    public List<Level> getLevelsList() {
        return levels;
    }


    private List<Level> createLevelsList() {
        List<Level> levelList = new ArrayList<>();
        String query = "select * from experience_level;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String levelName = resultSet.getString("level_name");
                Integer experience = resultSet.getInt("achieve_money");
                levelList.add(new Level(levelName, experience));
            }

            return levelList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR LevelDAO] LEVEL LIST NOT FOUND");
        return null;
    }


    public void addLevelToDB(String name, Integer experience) {
        levels.add(new Level(name, experience));
        String query = "INSERT INTO experience_level (level_name, achieve_money) VALUES (?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, experience);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
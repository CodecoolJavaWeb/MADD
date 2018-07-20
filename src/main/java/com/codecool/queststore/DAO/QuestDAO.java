package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;

import javax.xml.ws.spi.http.HttpExchange;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestDAO {

    private Integer questId;
    private String questName;
    private String questDescription;
    private Integer questValue;
    private String questCategory;

    private Connection connection;
    private List<Quest> quests;
    private static final String GET_QUESTS =
            "SELECT * FROM quest;";


    public QuestDAO()  {
        connection = new ConnectionProvider().getConnection();
    }

    private Connection connect() {
        return new ConnectionProvider().getConnection();
    }

    public List<Quest> getQuests() {
        quests = new ArrayList<>();
        try (Connection connection = this.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                questId = resultSet.getInt("id_quest");
                questName = resultSet.getString("quest_name");
                questDescription = resultSet.getString("description");
                questValue = resultSet.getInt("price");
                questCategory = resultSet.getString("category");

                Quest quest = new Quest(questId, questName, questCategory, questValue, questDescription);
                quests.add(quest);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return quests;
    }

    public void addQuest(Map<String, String> questData) {
        questName = questData.get("name");
        questValue = Integer.valueOf(questData.get("value"));
        questCategory = questData.get("type");
        questDescription = questData.get("description");

        String sql = "INSERT INTO quest (quest_name, description, price, category) VALUES(?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, questName);
            preparedStatement.setString(2, questDescription);
            preparedStatement.setInt(3, questValue);
            preparedStatement.setString(4, questCategory);
            preparedStatement.executeUpdate();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

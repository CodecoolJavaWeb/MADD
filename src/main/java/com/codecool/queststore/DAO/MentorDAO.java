package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorDAO {

    private Connection connection;
    private List<User> mentors;

    public MentorDAO()  {
        connection = new ConnectionProvider().getConnection();
        mentors = new ArrayList<>();
    }


    public List<User> getMentorsList() {
        String query = "select * from app_user where role = 'mentor';";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Integer userId = resultSet.getInt("id_user");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                User user = new Mentor(userId, firstName, lastName, phoneNumber, email, role);
                mentors.add(user);
            }

            return mentors;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;


    }
}

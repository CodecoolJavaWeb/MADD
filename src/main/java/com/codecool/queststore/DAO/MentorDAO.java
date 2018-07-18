package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.CoolClass;
import com.codecool.queststore.model.Mentor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MentorDAO {
    private Connection connection;
    private List<Mentor> mentors;


    public MentorDAO()  {
        connection = new ConnectionProvider().getConnection();
        mentors = createMentorList();
    }


    public List<Mentor> getMentorsList() {
        return mentors;
    }


    private List<Mentor> createMentorList() {
        List<Mentor> mentorList = new ArrayList<>();
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
                Mentor newMentor = new Mentor(userId, firstName, lastName, phoneNumber, email, role);
                Integer mentorId = getMentorIdByUserId(newMentor.getUserId());
                List<Integer> mentorClassIds = getClassIdsByMentorId(mentorId);
                List<CoolClass> mentorsClasses = getMentorClasses(mentorClassIds);
                newMentor.setClasses(mentorsClasses);
                mentorList.add(newMentor);
            }
            return mentorList;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR MentorDAO] MENTOR LIST NOT FOUND ===>> null");
        return null;
    }


    public List<Integer> getClassIdsByMentorId(Integer mentorId) {
        List<Integer> classIdsByMentorId = new ArrayList<>();

        String query = "select * from mentor_2_class where id_mentor = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, mentorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) classIdsByMentorId.add(resultSet.getInt("id_class"));
            return classIdsByMentorId;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR MentorDAO] CLASS IDS BY MENTOR NOT FOUND");
        return null;

    }


    public List<CoolClass> getMentorClasses(List<Integer> classIds) {
        List<CoolClass> coolClasses = new ArrayList<>();
        String query = "select * from cool_class where id_class = ?;";
        try {
            for(Integer classId : classIds) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, classId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) coolClasses.add(new CoolClass(resultSet.getString("class_name")));
            }
            return  coolClasses;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR MentorDAO] MENTOR CLASSES NOT FOUND");
        return null;
    }


    public Integer getMentorIdByUserId(Integer userId) {
        String query = "select id_mentor from mentor where id_user = ?;";
        Integer mentorId = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) mentorId = resultSet.getInt("id_mentor"); return mentorId;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR MentorDAO] MENTOR ID NOT FOUND BY USER ID"); return null;
    }


    public void updateMentorByUserId(Mentor mentorToUpdate, String[] mentorPropertiesToUpdate) {
        String FIRST_NAME = mentorPropertiesToUpdate[0];
        String LAST_NAME = mentorPropertiesToUpdate[1];
        String PHONE_NUMBER = mentorPropertiesToUpdate[2];
        String EMAIL_ADDRESS = mentorPropertiesToUpdate[3];


        mentorToUpdate.setFirstName(FIRST_NAME);
        mentorToUpdate.setLastName(LAST_NAME);
        mentorToUpdate.setPhoneNumer(PHONE_NUMBER);
        mentorToUpdate.setEmail(EMAIL_ADDRESS);

        List<CoolClass> coolclasses = new ArrayList<>();
        StringTokenizer sppliter = new StringTokenizer(mentorPropertiesToUpdate[4], " [,]");
        while (sppliter.hasMoreTokens()) coolclasses.add(new CoolClass(sppliter.nextToken()));
        mentorToUpdate.setClasses(coolclasses);
        Integer userId = mentorToUpdate.getUserId();
        updateMentorInDatabase(userId, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS);
//        TODO ----->>>>> UPDATE CLASSES
    }






    public Mentor getMentorByUserId(Integer userId) {
        for(Mentor mentor : mentors) if(mentor.getUserId().equals(userId)) return mentor;
        System.out.println("[ERROR MentorDAO] MENTOR NOT FOUND BY USER ID"); return null;
    }


    public void updateMentorInDatabase(Integer userId, String firstName, String lastName, String phoneNumber, String Email) {
        String query = "update app_user set first_name = ?, last_name = ?, phone = ?, email = ? where id_user = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, Email);
            preparedStatement.setInt(5, userId);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public Mentor addUserToDB(String firstName, String lastName, String phone, String email, String role) {
        Mentor newMentor = new Mentor(firstName, lastName, phone, email, role);
        mentors.add(newMentor);
        String query = "INSERT INTO app_user(first_name, last_name, phone, email, role) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, role);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return newMentor;
    }


    public void addMentorToDB(Mentor mentor) {
        Integer userID = getUserIdByMentorMail(mentor.getEmail());
        String query = "INSERT INTO mentor (id_user) VALUES (?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public Integer getUserIdByMentorMail(String mail) {
        String query = "select id_user from app_user where email = ?";
        Integer userId = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) userId = resultSet.getInt("id_user"); return userId;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR MentorDAO] getUserIdByMentorMail(String mail) ======>>> null"); return null;
    }
}

package DAO;

import Models.Enums.UserRoles;
import Models.Users.User;
import Utils.DBUtil;
import Utils.SQLScripts;

import java.sql.*;

public class UserDAO {

    public void saveUser(User user ){

        try {
        Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQLScripts.saveUserScript);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserRole().name().toUpperCase());
            ps.setObject(6, user.getAge(), Types.INTEGER);
            ps.setString(7, user.getCompanyName());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existByEmail(String email){

    try {
        Connection connection = DBUtil.getConnection();

        PreparedStatement ps = connection.prepareStatement(SQLScripts.existByEmailScript);

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        return rs.next();       //Fonksiyon çalıştığında bir değer varsa true dönecek, bu da kayıt oluşturmayı engelleyecek.

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;

    }


    public User findByEmail(String email) {

        User user = null;

        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQLScripts.findByEmailScript);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();



            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("first_name"));
                user.setSurname(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("passwrd"));
                System.out.println(rs.getString("user_role"));
                user.setUserRole(UserRoles.valueOf(rs.getString("user_role")));
                user.setAge(rs.getInt("age"));
                user.setCompanyName(rs.getString("company_name"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
}

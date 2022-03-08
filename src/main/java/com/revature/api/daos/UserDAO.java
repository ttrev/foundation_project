package com.revature.api.daos;

import com.revature.api.models.UserRoles;
import com.revature.api.models.Users;
import com.revature.api.util.ConnectionFactory;
import com.revature.api.util.exceptions.DataSourceException;
import com.revature.api.util.exceptions.ResourcePersistenceException;
import sun.awt.X11.XSystemTrayPeer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;

public class UserDAO implements CrudDAO<Users>{

    private  final String rootSelect = "SELECT * FROM ers_users eu " +
            "JOIN ers_user_roles eur " +
            "ON eu.role_id = eur.role_id ";


    public Users findUserByUsername(String username){
        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));
            }
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;
    }

    public Users findUserByEmail(String email){
        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));
            }
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;
    }

    public Users findUserById(String id){
        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    rootSelect + "WHERE user_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));
            }
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;
    }

    public Users findUserByUsernameAndPassword(String username, String password){
        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(
                    rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;
    }

    //For Admin
    public void updateUserPassword (Users user, String password){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE users eu "+
                    "SET password = ? "+
                    "WHERE eu.user_id = ?");
            pstmt.setString(1,password);
            pstmt.setString(2,user.getUserId());

            int passwordUpdate = pstmt.executeUpdate();
            if(passwordUpdate != 1){
                conn.rollback();
                throw new ResourcePersistenceException("Failed to update password");
            }
            conn.commit();
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public void deactivateUserAccount (Users user){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("UPDATE users eu "+
                    "SET is_active = ? "+
                    "WHERE eu.user_id = ?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2,user.getUserId());
            int deactivate = pstmt.executeUpdate();
            if(deactivate != 1){
                conn.rollback();
                throw new ResourcePersistenceException("Failed to deactivate user account");
            }

            conn.commit();
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public void reactivateUserAccount (Users ersUser){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("UPDATE users eu "+
                    "SET is_active = ? "+
                    "WHERE eu.user_id = ?");
            pstmt.setBoolean(1,true);
            pstmt.setString(2,ersUser.getUserId());
            int reactivate = pstmt.executeUpdate();
            if(reactivate != 1){
                conn.rollback();
                throw new ResourcePersistenceException("Failed to reactivate user account");
            }

            conn.commit();
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }


    //helper function
    public UserRoles findUserRoleByRoleName(String role_name){
        UserRoles userRole = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user_roles "+
                    "WHERE role = ?");
            pstmt.setString(1, role_name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                userRole = new UserRoles();
                userRole.setRoleId(rs.getString("role_id"));
                userRole.setRole(rs.getString("role"));
            }
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return userRole;
    }

    @Override
    public void save(Users newErsUser) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newErsUser.getUserId());
            pstmt.setString(2, newErsUser.getUserName());
            pstmt.setString(3, newErsUser.getEmail());
            pstmt.setString(4, newErsUser.getPassword());
            pstmt.setString(5, newErsUser.getGivenName());
            pstmt.setString(6, newErsUser.getSurname());
            pstmt.setBoolean(7, newErsUser.getIsActive());
            pstmt.setString(8, newErsUser.getUserRole().getRoleId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public Users getById(String id) {
        Users user = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {


            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE user_id = ?");

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                System.out.println(rs.getString("user_id"));

                user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("userName"));

                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));

                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));


            }


        }
        catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return user;
    }

    @Override
    public List<Users> getAll() {
        List<Users> users= new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(rootSelect);

            while (rs.next()){
                Users user = new Users();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));
                users.add(user);
            }
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return users;
    }

    @Override
    public void update(Users updateUsers) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE users " +
                    "SET username = ?, " +
                    "email = ?, " +
                    "password = ?, " +
                    "given_name = ?, " +
                    "surname = ?, " +
                    "is_active = ?, " +
                    "role_id = ? " +
                    "WHERE user_id = ?");

            pstmt.setString(1, updateUsers.getSurname());
            pstmt.setString(2, updateUsers.getEmail());
            pstmt.setString(3, updateUsers.getPassword());
            pstmt.setString(4, updateUsers.getGivenName());
            pstmt.setString(5, updateUsers.getSurname());
            pstmt.setBoolean(6,updateUsers.getIsActive());
            pstmt.setString(6, updateUsers.getUserRole().getRoleId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                throw new ResourcePersistenceException("Failed to update user data within datasource.");
            }

            conn.commit();

        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE user_id = ?");
            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from data source");
            }

            conn.commit();
        }catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}



















/*
// TODO attempt to centralize exception handling in service layer
public class UserDAO implements CrudDAO<Users> {

    private final String rootSelect = "SELECT * FROM users u " + "JOIN user_roles ur " + "ON u.role_id = ur.role_id ";

    public Users findUserByUsername(String username) {

        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("User_id"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setIsActive(rs.getBoolean("Is_active"));
                user.setUserRole(new UserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Users findUserByEmail(String email) {

        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getString("User_id"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setIsActive(rs.getBoolean("Is_active"));
                user.setUserRole(new UserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public Users findUserByUsernameAndPassword(String username, String password) {

        Users authUser = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUser = new Users();
                authUser.setUserId(rs.getString("User_id"));
                authUser.setUserName(rs.getString("Username"));
                authUser.setEmail(rs.getString("Email"));
                authUser.setPassword(rs.getString("Password"));
                authUser.setGivenName(rs.getString("Given_name"));
                authUser.setSurname(rs.getString("Surname"));
                authUser.setIsActive(rs.getBoolean("Is_active"));
                authUser.setUserRole(new UserRoles(rs.getString("Role_id"), rs.getString("role")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);

        }

        return authUser;
    }

    @Override
    public void save(Users newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUserName());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getGivenName());
            pstmt.setString(6, newUser.getSurname());
            pstmt.setBoolean(7, newUser.getIsActive());
            pstmt.setString(8, newUser.getUserRole().getRoleId());


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public Users getById(String id) {

        Users user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE user_id=?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user=new Users();
                user.setUserId(rs.getString("user_id"));


                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIsActive(rs.getBoolean("is_active"));
                user.setUserRole(new UserRoles(rs.getString("role_id"), rs.getString("role")));

            }




        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    @Override
    public List<Users> getAll() {

        List<Users> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            System.out.println(rs);
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getString("User_id"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGivenName(rs.getString("Given_name"));
                user.setSurname(rs.getString("Surname"));
                user.setIsActive(rs.getBoolean("Is_active"));
                user.setUserRole(new UserRoles(rs.getString("Role_Id"), rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }

    @Override
    public void update(Users updatedObject) {

    }

    @Override
    public void deleteById(String id) {

    }
}

*/
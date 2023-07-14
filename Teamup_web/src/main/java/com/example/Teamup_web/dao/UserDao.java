package com.example.Teamup_web.dao;

import com.example.Teamup_web.entity.User;
import com.example.Teamup_web.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author phoeniX.R
 */
public class UserDao {
    public boolean query(User user)
    {
        Connection connection = DBUtils.getConnection();
        String sql = "select * from user where id = ? and password = ?";
        try {
            PreparedStatement preparedStatement = null;
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            DBUtils.closeConnection();
        }
        return false;
    }

    public boolean add(User user)
    {
        Connection connection = DBUtils.getConnection();
        String sql = "insert into user(id,password) values(?,?)";
        try {
            PreparedStatement preparedStatement = null;
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.executeUpdate();
                return preparedStatement.getUpdateCount() != 0;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            DBUtils.closeConnection();
        }
        return false;
    }

}


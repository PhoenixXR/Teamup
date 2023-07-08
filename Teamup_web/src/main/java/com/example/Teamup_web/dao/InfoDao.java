package com.example.Teamup_web.dao;

import com.example.Teamup_web.entity.Info;
import com.example.Teamup_web.util.DBUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author phoeniX.R
 */
public class InfoDao {
    /**
     * Pub：public
     * listv:listview
     */
    public Queue listv(){
        Connection connection = DBUtils.getConnection();
        String sql = "select  kook,lv,num,TIMESTAMPDIFF(SECOND, create_time, NOW()) from info";
        //队列用来接收数据
        Queue<Info> queue = new LinkedList<>();
        //Queue queue1 = new LinkedList<>();
        ResultSet response = null;
        try {
            PreparedStatement preparedStatement = null;
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                //取结果集
                response = preparedStatement.executeQuery();
                while(response.next()) {
                    Info i = new Info();
                    i.setKook(response.getString(1));
                    i.setLv(response.getString(2));
                    i.setNum(response.getString(3));
                    i.setT(response.getString(4));
                    queue.offer(i);

//                    queue1.offer(response.getString(1));
//                    queue1.offer(response.getString(2));
//                    queue1.offer(response.getString(3));
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            DBUtils.closeConnection();
        }
        return queue;
    }
    public boolean Pub(Info info){
        Connection connection = DBUtils.getConnection();
        String sql = "insert into info(kook,lv,num,create_time) values(?,?,?,NOW())";
        try {
            PreparedStatement preparedStatement = null;
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,info.getKook());
                preparedStatement.setString(2,info.getLv());
                preparedStatement.setString(3,info.getNum());
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

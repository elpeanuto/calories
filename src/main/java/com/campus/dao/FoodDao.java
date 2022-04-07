package com.campus.dao;

import com.campus.config.Config;
import com.campus.domain.Food;
import com.campus.domain.exception.DaoException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.campus.dao.ConnectionBuilder.getConnection;

public class FoodDao {
    public static final String GET_FOOD = "SELECT * FROM food WHERE UPPER(f_name) LIKE UPPER (?);";
    public static final String SET_FOOD = "INSERT INTO food (f_name, f_calories) VALUES (?, ?);";
    public static final String DEL_FOOD = "DELETE FROM food WHERE f_name = 'Хубабуба'(?)";
    public static final String DB_NAME = "food";

    public void show() throws DaoException{
         String sql = "SELECT * FROM " + DB_NAME;

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString(2) + " " + rs.getString(3));
            }

        }catch (SQLException ex){
            throw new DaoException(ex);
        }
    }

    public Food getByName(String pattern) throws DaoException {
        Food result;

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_FOOD);){

            statement.setString(1, pattern);

            ResultSet rs = statement.executeQuery();
            rs.next();

            result = new Food(rs.getString(2), rs.getInt(3));
        }catch (SQLException ex){
            throw new DaoException(ex);
        }

        return result;
    }

    public void add(String name, int calories) throws DaoException{
        try(Connection connection = ConnectionBuilder.getConnection();
            PreparedStatement statement = connection.prepareStatement(SET_FOOD);){

            statement.setString(1, name);
            statement.setInt(2, calories);
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new DaoException(ex);
        }
    }

    public List<Food> read() throws DaoException {
        List<Food> result = new LinkedList<>();

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + DB_NAME)){

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                result.add(new Food(rs.getString(2), rs.getInt(3)));
            }

        }catch (SQLException ex){
            throw new DaoException(ex);
        }

        return result;
    }

    public void delete(String name) throws DaoException{
        String sql = "DELETE FROM food WHERE f_name = '" + name + "'";

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()){

           statement.executeUpdate(sql);

        }catch (SQLException ex){
            throw new DaoException(ex);
        }
    }
}

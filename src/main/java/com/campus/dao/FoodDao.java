package com.campus.dao;


import com.campus.domain.Food;
import com.campus.domain.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FoodDao {
    public static final String DB_NAME = "food";
    public static final String GET_FOOD = "SELECT * FROM food WHERE UPPER(f_name) LIKE UPPER (?);";
    public static final String SET_FOOD = "INSERT INTO food (f_name, f_calories) VALUES (?, ?);";
    public static final String DEL_FOOD = "DELETE FROM food WHERE f_name=?";
    public static final String SELECT_FOOD = "SELECT * FROM " + DB_NAME;

    private static final Logger logger = LoggerFactory.getLogger(FoodDao.class);

    public void show() throws DaoException {
        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_FOOD);

            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3));
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    public Food getByName(String pattern) throws DaoException {
        Food result;

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_FOOD);) {

            statement.setString(1, "%" + pattern + "%");

            ResultSet rs = statement.executeQuery();
            rs.next();

            result = new Food(rs.getString(2), rs.getInt(3));
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }

    public void add(String name, int calories) throws DaoException {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_FOOD);) {

            statement.setString(1, name);
            statement.setInt(2, calories);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    public List<Food> read() throws DaoException {
        List<Food> result = new LinkedList<>();

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOOD)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(new Food(rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }

    public void delete(String name) throws DaoException {
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEL_FOOD)) {

            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }
}

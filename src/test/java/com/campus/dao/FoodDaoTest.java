package com.campus.dao;

import com.campus.domain.Food;
import com.campus.domain.exception.DaoException;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class FoodDaoTest {
    FoodDao foodDao;

    @Before
    public void initialize(){
        foodDao = new FoodDao();
    }

    @BeforeClass
    public static void startUp() throws Exception {
        URL url = FoodDao.class.getClassLoader().getResource("food_DB.sql");

        List<String> str = Files.readAllLines(Paths.get(url.toURI()));
        String sql = str.stream().collect(Collectors.joining());

        try (Connection con = ConnectionBuilder.getConnection(); Statement statement = con.createStatement();) {
            statement.executeUpdate(sql);
        }
    }

    @Test
    public void deleteTest() throws DaoException {
        foodDao.delete("Буряк");
    }

    @Test
    public void addTest() throws DaoException {
        foodDao.add("Чипсы", 43);
    }

    @Test
    public void getByNameTest() throws DaoException {
        String str = foodDao.getByName("Рис").getFoodName();
        Assert.assertTrue(str.equals("Рис"));
    }

    @Test
    public void showTest() throws DaoException {
        foodDao.show();
    }

    @Test
    public void readTest() throws DaoException {
        List<Food> foodList = foodDao.read();
    }
}
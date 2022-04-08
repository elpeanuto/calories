package com.campus.domain;

import com.campus.dao.FoodDao;
import com.campus.domain.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleMenu.class);
    FoodDao foodDao = new FoodDao();
    List<Food> foodList;

    public ConsoleMenu() {
        try {
            foodList = foodDao.read();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {
        System.out.println("1. Посчитать каллории\n2. Добавить еду\n3. Удалить еду\n4. Вывести таблицу\n5. Выход");
    }

    private Scanner getRequest() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер: ");

        return in;
    }

    public void launch() {
        showMenu();
        processingRequest();
    }

    private void processingRequest() {

        switch (getRequest().nextInt()) {
            case 1:
                try {
                    String foodName = getRequest().nextLine();
                    int caloriesEaten = getRequest().nextInt();

                    for (Food food : foodList) {
                        System.out.println(food.getFoodName());
                        if (foodName.equals(food.getFoodName())) {
                            System.out.println(food.caloriesEaten(caloriesEaten));
                        }
                    }
                } catch (InputMismatchException ex) {
                    logger.info("Incorrect request");
                    System.err.println("Incorrect request");
                }
                break;
            case 2:
                try {
                    foodDao.add(getRequest().nextLine(), getRequest().nextInt());
                } catch (DaoException ex) {
                    logger.error(ex.getMessage(), ex);
                    ex.printStackTrace();
                } catch (InputMismatchException ex) {
                    logger.info("Incorrect request");
                    System.err.println("Incorrect request");
                }
                break;
            case 3:
                try {
                    foodDao.delete(getRequest().nextLine());
                } catch (DaoException ex) {
                    logger.error(ex.getMessage(), ex);
                    ex.printStackTrace();
                } catch (InputMismatchException ex) {
                    logger.info("Incorrect request");
                    System.err.println("Incorrect request");
                }
                break;
            case 4:
                try {
                    foodDao.show();
                } catch (DaoException ex) {
                    logger.error(ex.getMessage(), ex);
                    ex.printStackTrace();
                }
                break;
            case 5:
                System.exit(0);
            default:
                break;
        }
    }
}

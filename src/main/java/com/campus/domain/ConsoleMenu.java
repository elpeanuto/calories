package com.campus.domain;

import com.campus.dao.FoodDao;
import com.campus.domain.exception.DaoException;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    FoodDao foodDao = new FoodDao();
    List<Food> foodList;

    public ConsoleMenu(){
        try {
            foodList = foodDao.read();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void showMenu(){
        System.out.println("1. Посчитать каллории\n2. Добавить еду\n3. Удалить еду\n4. Вывести таблицу\n");
    }

    private Scanner getRequest(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер: ");

        return in;
    }

    public void launch(){
        showMenu();
        processingRequest();
    }

    private void processingRequest(){

        switch (getRequest().nextInt()){
            case 1:
                String foodName = getRequest().nextLine();
                int caloriesEaten = getRequest().nextInt();

                for (Food food : foodList){
                    System.out.println(food.getFoodName());
                    if(foodName.equals(food.getFoodName())){
                        System.out.println(food.caloriesEaten(caloriesEaten));
                    }
                }
                break;
            case 2:
                try {
                    foodDao.add(getRequest().nextLine(), getRequest().nextInt());
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    foodDao.delete(getRequest().nextLine());
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    foodDao.show();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}

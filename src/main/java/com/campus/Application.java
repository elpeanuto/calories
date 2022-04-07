package com.campus;

import com.campus.dao.FoodDao;
import com.campus.domain.ConsoleMenu;
import com.campus.domain.Food;

import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void launch(String[] args) throws Exception {
        ConsoleMenu consoleMenu = new ConsoleMenu();

        while(true){
            consoleMenu.launch();
        }

    }

    public static void main(String[] args)  {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

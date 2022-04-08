package com.campus;

import com.campus.domain.ConsoleMenu;

public class Application {
    public static void launch(String[] args) {
        ConsoleMenu consoleMenu = new ConsoleMenu();

        while (true) {
            consoleMenu.launch();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

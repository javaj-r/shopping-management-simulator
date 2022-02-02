package com.group4;

import com.group4.context.ApplicationContext;
import com.group4.util.Screen;

import java.util.List;

/**
 * @author javid
 * Created on 2/1/2022
 */
public class Application {

    public static void main(String[] args) {
        while (true) {
            try {
                int choice = Screen.showMenu("Exit", List.of("Admin login", "Customer signup", "Customer login"));

                if (choice == 0)
                    break;

                switch (choice) {
                    case 1 -> ApplicationContext.getAdminConsole().login();
                    case 2 -> ApplicationContext.getCustomerConsole().signup();
                    case 3 -> ApplicationContext.getCustomerConsole().login();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int tryAgainOrExit(String header) {
        return Screen.showMenu(header
                , "Select from menu: ", "Invalid choice."
                , "Exit to main.", List.of("Try again."));
    }

    public static int confirmMenu(String item) {
        return Screen.showMenu("Cancel", List.of(item));
    }

    public static boolean isForUpdate(String element) {
        return !"-".equals(element.trim());
    }
}

package com.group4.console;

import com.group4.Application;
import com.group4.context.ApplicationContext;
import com.group4.entities.Admin;
import com.group4.services.AdminService;
import com.group4.util.Screen;
import com.group4.validation.exception.ValidationException;

import java.util.List;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class AdminConsole {

    private final AdminService adminService;

    public AdminConsole(AdminService adminService) {
        this.adminService = adminService;
        createDefaultAdmin();
    }

    public void createDefaultAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin");
        try {
            adminService.findByUsernameAndPassword(admin);
        } catch (ValidationException e) {
            adminService.save(admin);
        }
    }

    public void login() {
        boolean loopFlag = true;

        while (loopFlag) {
            int choice = -1;
            Admin admin = new Admin();
            admin.setUsername(Screen.getString("Username: "));
            admin.setPassword(Screen.getPassword("Password: "));

            try {
                adminService.findByUsernameAndPassword(admin);
            } catch (ValidationException e) {
                choice = Application.tryAgainOrExit("\n\t" + e.getMessage());
                if (choice == 0)
                    break;
            }

            if (choice < 0) {
                adminMenu();
                loopFlag = false;
            }
        }
    }

    private void adminMenu() {
        boolean loopFlag = true;

        while (loopFlag) {
            int choice = Screen.showMenu("Logout", List.of("Show all products", "Add product", "Edit product"));

            if (choice == 0)
                break;

            switch (choice) {
                case 1 -> ApplicationContext.getProductConsole().showAll();
                case 2 -> ApplicationContext.getProductConsole().create();
                case 3 -> ApplicationContext.getProductConsole().update();
            }
        }
    }
}

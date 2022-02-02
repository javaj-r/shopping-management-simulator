package com.group4.console;

import com.group4.Application;
import com.group4.context.ApplicationContext;
import com.group4.entities.Cart;
import com.group4.entities.Customer;
import com.group4.entities.Product;
import com.group4.services.CartService;
import com.group4.services.CustomerService;
import com.group4.util.Screen;
import com.group4.validation.exception.ValidationException;

import java.util.List;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class CustomerConsole {

    private final CustomerService customerService;
    private final CartService cartService;
    private Customer customer;

    public CustomerConsole(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    public void login() {
        boolean loopFlag = true;
        while (loopFlag) {
            Customer loginCustomer = new Customer();
            loginCustomer.setUsername(Screen.getString("Username: "));
            loginCustomer.setPassword(Screen.getPassword("Password: "));

            try {
                this.customer = customerService.findByUsernameAndPassword(loginCustomer);
            } catch (ValidationException e) {
                int choice = Application.tryAgainOrExit("\n\t" + e.getMessage());
                if (choice == 0)
                    break;
            }

            if (this.customer != null && !this.customer.isNew()) {
                customerMenu();
                loopFlag = false;
            }
        }
    }

    public void signup() {
        boolean loopFlag = true;
        while (loopFlag) {
            Customer newCustomer = new Customer();
            newCustomer.setUsername(Screen.getString("Username: "));
            newCustomer.setPassword(Screen.getPassword("Password: "));
            newCustomer.setFirstName(Screen.getString("Firstname: "));
            newCustomer.setLastName(Screen.getString("Lastname: "));
            newCustomer.setNationalCode(Screen.getLong("National code: "));
            newCustomer.setPhoneNumber(Screen.getLong("Phone number: "));
            newCustomer.setEmail(Screen.getString("Email: "));

            try {
                newCustomer.setId(customerService.save(newCustomer));
                this.customer = newCustomer;
            } catch (ValidationException e) {
                int choice = Application.tryAgainOrExit("\n\t" + e.getMessage());
                if (choice == 0)
                    break;
            }

            if (this.customer != null && !this.customer.isNew()) {
                System.out.println("Registered successfully.");
                customerMenu();
                loopFlag = false;
            }
        }
    }

    private void customerMenu() {
        this.customer.setCart(new Cart());
        while (true) {
            int choice = Screen.showMenu("Logout", List.of("Add product to cart", "Pay cart"));

            if (choice == 0) {
                this.customer = null;
                break;
            }

            switch (choice) {
                case 1 -> addProductToCart();
                case 2 -> payCart();
            }
        }
    }

    private void payCart() {
        try {
            Cart cart = this.customer.getCart();
            cart.setCostumer(this.customer);
            cart.setDone(true);
            cart.setAddress(Screen.getString("Enter destination address:"));
            cart.setPhoneNumber(Screen.getLong("Enter phone number:"));

            if (cartService.save(cart) != null) {
                System.out.println("Your order saved successfully.");
                this.customer.setCart(new Cart());
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProductToCart() {
        Product product = ApplicationContext.getProductConsole()
                .selectByCategory("Select product for adding to cart: ");
        while (true) {
            int num = Screen.getInt("Enter number: ");
            int sum = num + getProductCountInCart(product);
            if (sum <= product.getStock()) {
                addProductToCart(product, num);
                break;
            }
            int choice = Application.tryAgainOrExit("No enough product: ");
            if (choice == 0)
                return;
        }
    }

    private void addProductToCart(Product product, int num) {
        for (int i = 0; i < num; i++) {
            this.customer.getCart().getProducts().add(product);
        }
    }

    private int getProductCountInCart(Product product) {
        List<Product> products = this.customer.getCart().getProducts();
        return (int) products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .count();
    }
}

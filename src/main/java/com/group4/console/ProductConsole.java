package com.group4.console;

import com.group4.Application;
import com.group4.context.ApplicationContext;
import com.group4.entities.Category;
import com.group4.entities.Product;
import com.group4.services.ProductService;
import com.group4.util.Screen;
import com.group4.validation.exception.ValidationException;

import java.util.List;

/**
 * @author javid
 * Created on 2/2/2022
 */
public class ProductConsole {

    private final ProductService productService;

    public ProductConsole(ProductService productService) {
        this.productService = productService;
    }

    public void showAll() {
        String message = "Press ENTER to continue: ";
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            System.out.println("\n\tThere are no products!");
            Screen.getString(message);
            return;
        }
        StringBuilder builder = new StringBuilder();
        productList.forEach(product -> builder.append("\t")
                .append(getStringProduct(product))
                .append("\n"));
        System.out.println(builder.append("\n").toString());
        Screen.getString(message);
    }

    public Product selectByCategory(String message) {
        Category category = ApplicationContext.getCategoryConsole()
                .select("Select product category from list: ");
        if (category.isNew())
            return new Product();

        return select(message, productService.findAllByCategory(category));
    }

    public Product select(String message) {
        return select(message, productService.findAll());
    }

    public Product select(String message, List<Product> products) {
        List<String> items = products.stream()
                .map(this::getStringProduct)
                .toList();

        int choice = Screen.showMenu(message, "Cancel", items);
        if (choice == 0) {
            return new Product();
        }

        return products.get(choice - 1);
    }

    public void create() {
        Category category = ApplicationContext.getCategoryConsole()
                .selectOrAdd("Select product category from list: ");
        if (category.isNew())
            return;

        boolean loopFlag = true;
        while (loopFlag) {
            Product product = new Product();
            product.setCategory(category);
            product.setName(Screen.getString("Enter product name: "));
            product.setPrice(Screen.getInt("Enter product price: "));
            product.setStock(Screen.getInt("Enter product stock: "));
            try {
                if (Application.confirmMenu("Save product") > 0) {
                    productService.save(product);
                    System.out.println("Product added successfully.");
                }
                loopFlag = false;
            } catch (ValidationException e) {
                int choice = Application.tryAgainOrExit("\n\t" + e.getMessage());
                if (choice == 0)
                    break;
            }
        }
    }

    public void update() {
        Product product = select("Select product to update: ");
        if (product.isNew())
            return;

        String name = Screen.getString("Enter - or new name: ");
        if (Application.isForUpdate(name))
            product.setName(name);

        Category category = ApplicationContext.getCategoryConsole().selectOrAdd("Select new category: ");
        if (!category.isNew())
            product.setCategory(category);

        int num = Screen.getInt("Enter -1 or new price: ");
        if (num >= 0)
            product.setPrice(num);

        num = Screen.getInt("Enter -1 or new stock: ");
        if (num >= 0)
            product.setStock(num);

        try {
            if (Application.confirmMenu("Save changes") > 0) {
                productService.update(product);
                System.out.println("Product updated successfully.");
            }
        } catch (ValidationException e) {
            System.out.println("Failed to save product.");
            System.out.println(e.getMessage());
        }
    }

    private String getStringProduct(Product product) {
        return new StringBuilder("[ name:")
                .append(product.getName())
                .append(", price:")
                .append(product.getPrice())
                .append(", stock:")
                .append(product.getStock())
                .append(" ]").toString();
    }
}

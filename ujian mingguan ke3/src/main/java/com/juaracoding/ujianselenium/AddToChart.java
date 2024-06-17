package com.juaracoding.ujianselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToChart {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Maximize Window");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open Browser");

        // Isi form login dengan kredensial yang valid
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Verifikasi login berhasil
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));

        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("Login berhasil");
        } else {
            System.out.println("Login gagal");
            driver.quit(); // Menutup browser saat login gagal
            return;
        }
        // Tunggu hingga tombol "Add to Cart" muncul
        try {
            delay(3);
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")));

            // Klik tombol "Add to Cart"
            addToCartButton.click();
            delay(3);

            // Verifikasi produk ditambahkan ke keranjang
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_link"));
            String actualBadgeText = cartBadge.getText();
            if (actualBadgeText.equals("1")) {
                System.out.println("Produk berhasil ditambahkan ke keranjang");
                delay(3);
            } else {
                System.out.println("Produk gagal ditambahkan ke keranjang");
            }

            // Assert untuk verifikasi dengan menggunakan assertEquals
            if (actualBadgeText.equals("1")) {
                System.out.println("Test case Add Product to Cart: PASSED");
            } else {
                System.out.println("Test case Add Product to Cart: FAILED");
            }
        } catch (Exception e) {
            System.out.println("Gagal menemukan tombol 'Add to Cart': " + e.getMessage());
        }
        delay(3);

        // Menutup browser setelah selesai menjalankan tes
        driver.quit();
    }
    private static void delay(long detik) {
        try {
            Thread.sleep(1000*detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

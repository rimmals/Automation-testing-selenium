package com.juaracoding.ujianselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidLogin {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Maximize Window");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open Browser");
        // Temukan elemen input username dan password serta tombol login
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Isi form login
        delay(2);
        usernameField.sendKeys("standard_user");
        delay(2);
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Verifikasi login berhasil
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Login berhasil");
        } else {
            System.out.println("Login gagal");
        }

        delay(3);
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

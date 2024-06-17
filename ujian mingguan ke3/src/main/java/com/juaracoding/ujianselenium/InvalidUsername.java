package com.juaracoding.ujianselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvalidUsername {
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

        // Isi form login dengan username yang salah
        usernameField.sendKeys("invalid_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Tunggu beberapa detik agar halaman termuat setelah mencoba login
        delay(2);

        // Verifikasi login gagal dengan username yang salah
        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Login gagal karena username tidak sesuai");
        } else {
            System.out.println("Kesalahan: Login berhasil dengan username yang salah");
        }
        // Tutup browser
        driver.quit();
    }

        private static void delay(long detik){
            try {
                Thread.sleep(1000*detik);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



    }
}

package test.addressbook.pack.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends  HelperBase{
    //private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        super (wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
        return;
        } else {
            wd.findElement(By.linkText("groups")).click();
        }
    }

    public void goHome() {

        if (isElementPresent(By.id("maintable"))) {
            return;
        } else {
            wd.findElement(By.linkText("home")).click();
        }
    }

    public void popupConfirm(){
        wd.switchTo().alert().accept();
    }

}

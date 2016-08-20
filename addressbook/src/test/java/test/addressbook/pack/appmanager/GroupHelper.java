package test.addressbook.pack.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.addressbook.pack.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void edit(int index, GroupData group) {
        selectGroup(index);
        openForEditGroup();
        fillGroupForm(group);
        updateGroup();
        returnToGroupPage();
    }


    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void openForEditGroup() {
        click(By.name("edit"));
    }

    public void updateGroup() {
        click(By.name("update"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void delete(int index) {
        selectGroup(index);
        deleteGroup();
        returnToGroupPage();
    }

    public boolean isGroupExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}

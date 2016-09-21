package test.addressbook.pack.tests;


import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import test.addressbook.pack.appmanager.ApplicationManager;
import test.addressbook.pack.model.ContactData;
import test.addressbook.pack.model.Contacts;
import test.addressbook.pack.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
 // protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
     Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart (Method method, Object[] p) {
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop (Method method) {
        logger.info("Stop test " + method.getName());
    }

    public void verifyGroupListInUI(){
       if (Boolean.getBoolean("verifyUI")){
           Groups dbGroups = app.db().groups();
           Groups uiGroups = app.group().all();
           assertThat(uiGroups, equalTo(dbGroups));
       }
    }

    public void verifyContactListInUI(){
        if (Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts));
        }
    }
}

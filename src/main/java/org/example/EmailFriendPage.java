package org.example;

import org.openqa.selenium.By;

public class EmailFriendPage extends Utils{

    // this is DECLARING data type and storing the value in it
    private  By _friendsEmail= By.className("friend-email");
    private  By _yourEmail =By.id("YourEmailAddress");
    private  By _Message =By.id("PersonalMessage");
    private  By _sendEmail= By.name("send-email");

    public void toVerifyUserCAnRefer(){
        typeText(_friendsEmail,"friend-email");//Passing unique locator and sending user value
        typeText(_yourEmail, "YourEmailAddress");
        //Uniquely identify the element within the web page fill it with the given user data
        typeText(_Message,"PersonalMessage");//Passing unique locator and sending user value
        clickButton(_sendEmail);//clicking the send email button
    }

}

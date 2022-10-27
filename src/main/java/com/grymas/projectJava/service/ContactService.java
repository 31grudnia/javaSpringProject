package com.grymas.projectJava.service;


import com.grymas.projectJava.controller.ContactController;
import com.grymas.projectJava.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j      // creates log object by lombok
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println("Contact Service Bran ini.");
    }
    // Save detials of msg into DB and return bool
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        // TD; need to persist data to DB
        log.info(contact.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

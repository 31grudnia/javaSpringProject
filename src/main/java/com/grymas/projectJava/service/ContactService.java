package com.grymas.projectJava.service;


import com.grymas.projectJava.controller.ContactController;
import com.grymas.projectJava.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j      // creates log object by lombok
@Service
public class ContactService {


    // Save detials of msg into DB and return bool
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        // TD; need to persist data to DB
        log.info(contact.toString());
        return isSaved;
    }
}

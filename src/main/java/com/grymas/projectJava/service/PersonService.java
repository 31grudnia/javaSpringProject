package com.grymas.projectJava.service;

import com.grymas.projectJava.constants.SchoolConstants;
import com.grymas.projectJava.model.Contact;
import com.grymas.projectJava.model.Person;
import com.grymas.projectJava.model.Roles;
import com.grymas.projectJava.repository.ContactRepository;
import com.grymas.projectJava.repository.PersonRepository;
import com.grymas.projectJava.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(SchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
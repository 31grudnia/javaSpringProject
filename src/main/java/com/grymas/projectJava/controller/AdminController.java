package com.grymas.projectJava.controller;

import com.grymas.projectJava.model.Class;
import com.grymas.projectJava.model.Person;
import com.grymas.projectJava.repository.ClassRepository;
import com.grymas.projectJava.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<Class> classes = classRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("eClass", new Class());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("eClass") Class eClass) {
        classRepository.save(eClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<Class> eClass = classRepository.findById(id);
        for(Person person : eClass.get().getPersons()){
            person.setEClass(null);
            personRepository.save(person);
        }
        classRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<Class> eClass = classRepository.findById(classId);
        modelAndView.addObject("eClass", eClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("class", eClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Class eClass = (Class) session.getAttribute("class");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setEClass(eClass);
        personRepository.save(personEntity);
        eClass.getPersons().add(personEntity);
        classRepository.save(eClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        Class eClass = (Class) session.getAttribute("Class");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setEClass(null);
        eClass.getPersons().remove(person.get());
        Class classSaved = classRepository.save(eClass);
        session.setAttribute("Class", classSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+eClass.getClassId());
        return modelAndView;
    }
}
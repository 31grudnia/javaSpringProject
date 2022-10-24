package com.grymas.projectJava.model;

import lombok.Data;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
public class Contact {

    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

}

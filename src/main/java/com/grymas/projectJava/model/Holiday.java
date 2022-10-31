package com.grymas.projectJava.model;


import lombok.Data;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
public class Holiday extends BaseEntity{

    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}

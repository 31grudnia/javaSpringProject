package com.grymas.projectJava.model;


import lombok.Data;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
public class Holiday {

    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}

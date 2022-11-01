package com.grymas.projectJava.model;


import lombok.Data;

import javax.persistence.*;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity{

    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}

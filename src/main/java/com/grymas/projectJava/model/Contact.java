package com.grymas.projectJava.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
public class Contact extends BaseEntity{

    private int contactId;

    //  @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
    //  @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
    //  @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
    //  @Pattern for regex
    //  @Size defines needed size of text

    @NotBlank(message="Name cannot be empty!")
    @Size(min=3, message="Name must be at least 3 characters long!")
    private String name;

    @NotBlank(message="Phone number cannot be empty!")
    @Pattern(regexp="(^$|[0-9]{9})",message = "Mobile number must be 9 digits!")
    private String mobileNum;

    @NotBlank(message="Email cannot be empty!")
    @Email(message = "Please provide a valid email address!")
    private String email;

    @NotBlank(message="Subject cannot be empty!")
    @Size(min=5, message="Subject must be at least 5 characters long!")
    private String subject;

    @NotBlank(message="Message cannot be empty!")
    @Size(min=10, message="Message must be at least 10 characters long!")
    private String message;

    private String status;

}

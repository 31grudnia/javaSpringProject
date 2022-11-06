package com.grymas.projectJava.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data   // Data is from lombok library, provides getters, setters etc. methods and Constructor at compile time.
@Entity
@Table(name = "contact_msg")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status",
                resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
                query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
})
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "contact_id")
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

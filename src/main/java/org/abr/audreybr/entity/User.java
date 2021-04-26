package org.abr.audreybr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter

public class User {

    @Id
    private Long id;
    private String LastName;
    private String FirstName;
    private String Address;
    private int PhoneNumber;
}

package com.example.Employee_Management_app.entity;

import jakarta.persistence.*;

/**
 * we create the table here with use of entity / Table annotation
 */
@Entity
@Table(name = "Employee")
public class EmployeeDetailStructure {
    @Id//  this is will be valued as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// used to generate value automatically
    private Long id;
    // column annotation use to indicate the variables as column name
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique =true)// unique option ensure no duplicate value entered in to database
    private Long phonenumber;
    @Column(nullable = false, unique = true)
    private String email;

    // no argument constructor
    public EmployeeDetailStructure() {
    }
// argumented constructor which create object with all employee details


    public EmployeeDetailStructure(Long id, String firstName, String lastName, int age, String address, Long phonenumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    // the below are getters and setter
    // using setter value will be given to the variables and stored in database or refelect in html
    // using getter we can retrieve the value from database or any

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    //    public EmployeeDetailStructure() {
//    }
//
//    public EmployeeDetailStructure(Long id, String firstName, String lastName, String email) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}

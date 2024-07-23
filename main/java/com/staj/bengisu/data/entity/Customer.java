package com.staj.bengisu.data.entity;

import jakarta.persistence.*; //import all
import lombok.*;

import java.util.Date;

@Entity
@Table(name="CUSTOMER")
@Data
public class Customer extends Auditable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //it uses the strategy whatever it wants
    private Long id;   //AUTO means 50 ID

    @Column(name="C_NAME") //search for this column
    private String name;

    @Column(name="C_LASTNAME")
    private String lastname;

    @Column(name = "C_AGE")
    private int age;

    /*@Temporal(TemporalType.DATE)  //only shows date
    private Date orderDate;*/

    @Column(name = "SUBSCRIPTION_TYPE")
    private String subscriptionType;

    /*@Column(name = "EMPLOYEE_ID")
    private Long employeeID;*/

    private int contactNumber;











}

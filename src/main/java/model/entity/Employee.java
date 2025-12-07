package model.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String title;
    private String address;
    private String nic;
    private String contact;
    private String dob;
    private String city;
    private String bankAccountNo;
    private String bankBranch;
}

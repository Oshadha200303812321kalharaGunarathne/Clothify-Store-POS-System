package model.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Employee {
    private Integer id;
    private String name;
    private String title;
    private String address;
    private String email;
    private String nic;
    private String contact;
    private String dob;
    private String city;
    private String jobRole;
    private String bankAccountNo;
    private String bankBranch;
}

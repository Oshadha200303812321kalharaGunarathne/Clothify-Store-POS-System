package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private Integer id;
    private String userName;
    private String password;
    private String email;
}

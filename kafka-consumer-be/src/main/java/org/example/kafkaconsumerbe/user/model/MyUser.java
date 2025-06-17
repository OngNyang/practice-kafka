package org.example.kafkaconsumerbe.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyUser {
    private String  username;
    private String  password;
    private String  role;
    
}

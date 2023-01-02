package com.genie.ludalantern.domain.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")},name = "user")
//@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String userId;

    @Column(nullable = false)
    private String username;

    private String password;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.



    @Builder
    public UserEntity(String username,String email){
        this.email = email;
        this.username = username;
    }


}


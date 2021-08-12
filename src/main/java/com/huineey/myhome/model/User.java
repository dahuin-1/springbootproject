package com.huineey.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;

    private String password;

    private Boolean enabled;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

   // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //사용자 입장에서 보드를 가져올 때
    //원투매니 매핑 시킬때는 보통 많은쪽, 매니 투 원을 작성하는 쪽(=board)에서 소유하는 쪽의 매핑 정보를 많이 적어줌
    //원 투 매니를 작성하는 쪽에서는 mappedBy 사용
    //board가 fk를 가
    @OneToMany(mappedBy = "user")
            //fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();

}

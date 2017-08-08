package com.lbj.issuetracker.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lbj.issuetracker.user.role.Role;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

/**
 * Created by LBJ on 2017-07-27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    private String username;
    @JsonIgnore
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Role> roleList;
}

package com.lbj.issuetracker.user.role;

import com.lbj.issuetracker.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by LBJ on 2017-07-27.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private Integer srl;
    private String rolename;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name= "username")
    private User user;
}

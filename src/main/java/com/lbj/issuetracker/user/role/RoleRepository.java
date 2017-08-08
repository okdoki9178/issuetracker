package com.lbj.issuetracker.user.role;
import com.lbj.issuetracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LBJ on 2017-07-27.
 */

public interface RoleRepository extends JpaRepository<Role, String> {
}

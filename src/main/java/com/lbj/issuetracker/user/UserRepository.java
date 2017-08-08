package com.lbj.issuetracker.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LBJ on 2017-07-27.
 */

public interface UserRepository extends JpaRepository<User, String> {
}

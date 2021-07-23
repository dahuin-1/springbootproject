package com.huineey.myhome.repository;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

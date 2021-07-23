package com.huineey.myhome.service;

import com.huineey.myhome.model.Role;
import com.huineey.myhome.model.User;
import com.huineey.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());//암호화
        user.setPassword(encodedPassword); //저장
        user.setEnabled(true); //트루로 변경
        Role role = new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

}

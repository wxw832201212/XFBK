package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findUserByStudentIdAndUserName(String studentId, String userName);
}

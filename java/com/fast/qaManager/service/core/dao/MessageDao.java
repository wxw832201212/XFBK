package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {
    List<Message> findMessagesByProblemId(Integer problemId);
}

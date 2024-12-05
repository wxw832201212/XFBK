package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.MessageProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageProblemDao extends JpaRepository<MessageProblem, Integer> {
    MessageProblem findMessageProblemByProblemIdAndUserId(Integer problemId, Integer userId);

    List<MessageProblem> findMessageProblemsByProblemIdAndUserIdNot(Integer problemId, Integer userId);
}

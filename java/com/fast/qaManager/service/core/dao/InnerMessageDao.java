package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.InnerMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InnerMessageDao extends JpaRepository<InnerMessage,Integer> {
    List<InnerMessage> findInnerMessagesByUserIdAndType(Integer userId,Integer type);
}

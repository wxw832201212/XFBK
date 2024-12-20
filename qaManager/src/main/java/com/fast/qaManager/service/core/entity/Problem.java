package com.fast.qaManager.service.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "problem")
public class Problem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Lob
  @Column(name = "topic", nullable = false)
  private String topic;

  @Column(name = "browse_count", nullable = false)
  private Integer browseCount;

  public Integer getBrowseCount() {
    return browseCount;
  }

  public void setBrowseCount(Integer browseCount) {
    this.browseCount = browseCount;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
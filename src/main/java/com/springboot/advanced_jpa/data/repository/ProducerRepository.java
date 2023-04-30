package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

}

package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}

package com.example.demo.repository.conf;

import com.example.demo.model.conf.EmailConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfRepository extends JpaRepository<EmailConf, Integer> {
}

package com.example.demo.repository.conf;

import com.example.demo.model.conf.TemperatureConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureConfRepository extends JpaRepository<TemperatureConf, Integer> {
}

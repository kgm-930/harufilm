package com.ssafy.harufilm.repository.subscribe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
    
    List<Subscribe> findAllBySubfrom(int subfrom); //subfrom(from) 
    
    List<Subscribe> findAllBySubto(int subto); //subto(to)
    
    <Optional>Subscribe findBySubfromAndSubto(int subfrom, int subto);
}

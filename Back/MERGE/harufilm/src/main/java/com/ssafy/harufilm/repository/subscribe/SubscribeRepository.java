package com.ssafy.harufilm.repository.subscribe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.dto.account.SmallProfileResponseDto;
import com.ssafy.harufilm.entity.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
    
    @Query("select userpid, username, userimg from User u where u.userpid = ( select s.subto from Subscribe s where s.subfrom = :userpid)")
    List<SmallProfileResponseDto> followList(int userpid); 
    
    @Query("select userpid, username, userimg from User u where u.userpid = ( select s.subfrom from Subscribe s where s.subto = :userpid)")
    List<SmallProfileResponseDto> followerList(int userpid);
    
    <Optional>Subscribe findBySubfromAndSubto(int subfrom, int subto);

    List<Subscribe> findBySubfrom(int subfrom);

    List<Subscribe> findBySubto(int subto);
}

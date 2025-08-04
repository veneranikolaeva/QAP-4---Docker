package com.veneranikolaeva.golfapi.repository;

import com.veneranikolaeva.golfapi.model.Member;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContaining(String name);
    List<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findByStartDate(LocalDate startDate);
}
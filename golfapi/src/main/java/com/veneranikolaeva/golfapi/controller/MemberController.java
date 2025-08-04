package com.veneranikolaeva.golfapi.controller;

import com.veneranikolaeva.golfapi.model.Member;
import com.veneranikolaeva.golfapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // Create a new member
    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    // Retrieve all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Search members by name, phone, or start date
    @GetMapping("/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String startDate) {

        if (name != null) {
            return memberRepository.findByNameContaining(name);
        } else if (phone != null) {
            return memberRepository.findByPhoneNumber(phone);
        } else if (startDate != null) {
            LocalDate date = LocalDate.parse(startDate);
            return memberRepository.findByStartDate(date);
        }
        return memberRepository.findAll();
    }
}
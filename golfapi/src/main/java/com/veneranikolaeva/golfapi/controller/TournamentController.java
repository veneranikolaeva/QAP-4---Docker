package com.veneranikolaeva.golfapi.controller;

import com.veneranikolaeva.golfapi.model.Tournament;
import com.veneranikolaeva.golfapi.model.Member;
import com.veneranikolaeva.golfapi.repository.TournamentRepository;
import com.veneranikolaeva.golfapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Create a new tournament
    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Retrieve all tournaments
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    // Add member to tournament
    @PostMapping("/{tournamentId}/add-member")
    public ResponseEntity<String> addMemberToTournament(@PathVariable Long tournamentId, @RequestBody Member member) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(tournamentId);
        Optional<Member> optionalMember = memberRepository.findById(member.getId());
        if (optionalTournament.isPresent() && optionalMember.isPresent()) {
            Tournament tournament = optionalTournament.get();
            Member m = optionalMember.get();
            tournament.getParticipants().add(m);
            tournamentRepository.save(tournament);
            return ResponseEntity.ok("Member added to tournament");
        } else {
            return ResponseEntity.badRequest().body("Invalid tournament or member ID");
        }
    }

    // Search tournaments by start date or location
    @GetMapping("/search")
    public List<Tournament> searchTournaments(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String startDate) {

        if (location != null) {
            return tournamentRepository.findByLocation(location);
        } else if (startDate != null) {
            LocalDate date = LocalDate.parse(startDate);
            return tournamentRepository.findByStartDate(date);
        }
        return tournamentRepository.findAll();
    }

    // Get all members in a specific tournament
    @GetMapping("/{tournamentId}/members")
    public ResponseEntity<List<Member>> getMembersInTournament(@PathVariable Long tournamentId) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(tournamentId);
        if (optionalTournament.isPresent()) {
            List<Member> members = new ArrayList<>(optionalTournament.get().getParticipants());
            return ResponseEntity.ok(members);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
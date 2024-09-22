package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.controller.api.PollApi;
import com.ogcs.nikob.RestaurantOrdering.dto.MinPollDTO;
import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import com.ogcs.nikob.RestaurantOrdering.service.PollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Poll}.
 * Provides endpoints to perform CRUD operations on polls.
 * Implements the {@link PollApi} API doc interface.
 */


@RestController
@RequestMapping("/polls")
public class PollController implements PollApi {

    @Autowired
    private PollService pollService;

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {
        return ResponseEntity.ok(pollService.getAllPolls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        Optional<Poll> poll = pollService.getPollById(id);
        return poll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody MinPollDTO pollDTO) {
        Poll poll = new Poll();

        poll.setCreatedAt(pollDTO.getCreatedAt());
        poll.setClosedAt(pollDTO.getClosedAt());

        Poll savedPoll = pollService.savePoll(poll);
        return ResponseEntity.ok(savedPoll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> updatePoll
            (@PathVariable Long id, @RequestBody MinPollDTO pollDetails) {
        Optional<Poll> poll = pollService.getPollById(id);
        if (poll.isPresent()) {
            Poll existingPoll = poll.get();
            existingPoll.setCreatedAt(pollDetails.getCreatedAt());
            existingPoll.setClosedAt(pollDetails.getClosedAt());
            Poll updatedPoll = pollService.savePoll(existingPoll);
            return ResponseEntity.ok(updatedPoll);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        Optional<Poll> poll = pollService.getPollById(id);
        if (poll.isPresent()) {
            pollService.deletePoll(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

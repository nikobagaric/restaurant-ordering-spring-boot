package com.ogcs.nikob.RestaurantOrdering.controller;

import com.ogcs.nikob.RestaurantOrdering.dto.VoteDTO;
import com.ogcs.nikob.RestaurantOrdering.model.Vote;
import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import com.ogcs.nikob.RestaurantOrdering.model.User;
import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;
import com.ogcs.nikob.RestaurantOrdering.service.VoteService;
import com.ogcs.nikob.RestaurantOrdering.repository.PollRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.UserRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.RestaurantRepository;
import com.ogcs.nikob.RestaurantOrdering.controller.api.VoteApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Vote}.
 * Provides endpoints to perform CRUD operations on votes.
 * Implements the {@link VoteApi} API doc interface.
 */


@RestController
@RequestMapping("/votes")
public class VoteController implements VoteApi {

    @Autowired
    private VoteService voteService;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long id) {
        Optional<Vote> vote = voteService.getVoteById(id);
        return vote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody VoteDTO voteDTO) {
        User user = userRepository.findById(voteDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Poll poll = pollRepository.findById(voteDTO.getPollId())
                .orElseThrow(() -> new IllegalArgumentException("Poll not found"));
        Restaurant restaurant = restaurantRepository.findById(voteDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setPoll(poll);
        vote.setRestaurant(restaurant);

        Vote savedVote = voteService.saveVote(vote);
        return ResponseEntity.ok(savedVote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable Long id, @RequestBody VoteDTO voteDTO) {
        Optional<Vote> existingVoteOpt = voteService.getVoteById(id);
        if (existingVoteOpt.isPresent()) {
            Vote existingVote = existingVoteOpt.get();

            Restaurant restaurant = restaurantRepository.findById(voteDTO.getRestaurantId())
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

            existingVote.setRestaurant(restaurant);

            Vote updatedVote = voteService.saveVote(existingVote);
            return ResponseEntity.ok(updatedVote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        Optional<Vote> vote = voteService.getVoteById(id);
        if (vote.isPresent()) {
            voteService.deleteVote(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.ogcs.nikob.RestaurantOrdering.service;

import com.ogcs.nikob.RestaurantOrdering.model.Vote;
import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import com.ogcs.nikob.RestaurantOrdering.repository.VoteRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.PollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Vote} entities.
 * Provides methods to perform CRUD operations on votes.
 */

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollRepository pollRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Optional<Vote> getVoteById(Long id) {
        return voteRepository.findById(id);
    }

    public Vote saveVote(Vote vote) {
        Poll poll = vote.getPoll();
        if (poll.getClosedAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot vote after the poll has closed.");
        }
        return voteRepository.save(vote);
    }

    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }
}

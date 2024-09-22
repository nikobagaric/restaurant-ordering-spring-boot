package com.ogcs.nikob.RestaurantOrdering.repository;

import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import com.ogcs.nikob.RestaurantOrdering.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Vote} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and query methods.
 */


@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByPoll(Poll poll);
}

package com.ogcs.nikob.RestaurantOrdering.service;

import com.ogcs.nikob.RestaurantOrdering.model.Poll;
import com.ogcs.nikob.RestaurantOrdering.model.Vote;
import com.ogcs.nikob.RestaurantOrdering.model.User;
import com.ogcs.nikob.RestaurantOrdering.model.Order;
import com.ogcs.nikob.RestaurantOrdering.model.Restaurant;
import com.ogcs.nikob.RestaurantOrdering.repository.PollRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.VoteRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.UserRepository;
import com.ogcs.nikob.RestaurantOrdering.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Service class for managing {@link Poll} entities.
 * Provides methods to perform CRUD operations on polls.
 */


@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VoteRepository voteRepository;

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }

    @Scheduled(fixedDelay = 1, fixedRate = 1, timeUnit = TimeUnit.MINUTES) // Check every minute
    public void createOrdersForClosedPolls() {
        List<Poll> closedPolls = pollRepository.findAll().stream()
                .filter(p -> p.getClosedAt().isBefore(LocalDateTime.now()) && p.getOrder() == null)
                .toList();
        System.out.println("1m!\n");
        for (Poll poll : closedPolls) {
            createOrderForPoll(poll);
        }
    }

    private void createOrderForPoll(Poll poll) {
        Restaurant winningRestaurant = getWinningRestaurant(poll);
        if (winningRestaurant == null) {
            return; // No votes for this poll, so no order will be created
        }

        List<User> voters = poll.getVotes().stream()
                .map(Vote::getUser)
                .distinct()
                .toList();

        if (!voters.isEmpty()) {
            Random random = new Random();
            User randomUser = voters.get(random.nextInt(voters.size()));

            Order order = new Order();
            order.setPoll(poll);
            order.setUser(randomUser);
            order.setRestaurant(winningRestaurant);
            order.setCreatedAt(LocalDateTime.now());
            order.setSentAt(poll.getClosedAt().plusMinutes(10)); // Adjust as needed

            orderRepository.save(order);
        }
    }

    private Restaurant getWinningRestaurant(Poll poll) {
        Map<Restaurant, Long> voteCount = voteRepository.findByPoll(poll).stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));

        return voteCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}

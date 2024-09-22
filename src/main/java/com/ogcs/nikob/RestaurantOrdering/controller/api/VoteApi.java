package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.dto.VoteDTO;
import com.ogcs.nikob.RestaurantOrdering.model.Vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Vote", description = "Vote API")
public interface VoteApi {

    @Operation(
            summary = "Fetch all votes",
            description = "Fetches all votes and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))})
    })
    ResponseEntity<List<Vote>> getAllVotes();

    @Operation(
            summary = "Fetch specific vote",
            description = "Fetches specific vote via vote's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))})
    })
    ResponseEntity<Vote> getVoteById(@PathVariable Long id);

    @Operation(
            summary = "Create vote",
            description = "Creates vote given vote data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created vote",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))})
    })
    ResponseEntity<Vote> createVote(@RequestBody VoteDTO vote);

    @Operation(
            summary = "Updates vote",
            description = "Updates vote given new vote data and the vote's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated vote",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))})
    })
    ResponseEntity<Vote> updateVote(@PathVariable Long id, @RequestBody VoteDTO voteDetails);

    @Operation(
            summary = "Deletes vote",
            description = "Deletes vote given vote ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted vote",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class))})
    })
    ResponseEntity<Void> deleteVote(@PathVariable Long id);
}

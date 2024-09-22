package com.ogcs.nikob.RestaurantOrdering.controller.api;

import com.ogcs.nikob.RestaurantOrdering.dto.MinPollDTO;
import com.ogcs.nikob.RestaurantOrdering.model.Poll;

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

@Tag(name = "Poll", description = "Poll API")
public interface PollApi {

    @Operation(
            summary = "Fetch all polls",
            description = "Fetches all polls and their data from source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))})
    })
    ResponseEntity<List<Poll>> getAllPolls();

    @Operation(
            summary = "Fetch specific poll",
            description = "Fetches specific poll via poll's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))})
    })
    ResponseEntity<Poll> getPollById(@PathVariable Long id);

    @Operation(
            summary = "Create poll",
            description = "Creates poll given poll data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created poll",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))}),
            @ApiResponse(responseCode = "409", description = "Duplicate error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))})
    })
    ResponseEntity<Poll> createPoll(@RequestBody MinPollDTO poll);

    @Operation(
            summary = "Updates poll",
            description = "Updates poll given new poll data and the poll's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated poll",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))})
    })
    ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody MinPollDTO pollDetails);

    @Operation(
            summary = "Deletes poll",
            description = "Deletes poll given poll ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted poll",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))})
    })
    ResponseEntity<Void> deletePoll(@PathVariable Long id);
}

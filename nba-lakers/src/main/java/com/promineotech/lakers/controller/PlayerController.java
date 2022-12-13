package com.promineotech.lakers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.lakers.entity.Player;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/Players")
@OpenAPIDefinition(info = @Info(title = "Players"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.") })
public interface PlayerController {
//@formatter:off
  @Operation(
      summary = "Returns a list of players",
      description = "Returns a list of all current Laker players",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A list of players is returned.", 
              content = @Content(
                  mediaType = "application/json", 
              schema = @Schema(implementation = Player.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid.", 
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No players were found with the input criteria.", 
              content = @Content(
                  mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
     }
  )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Player> fetchPlayers(
     );

  
  // @formatter:off
  @Operation( 
      summary = "Return a player",
      description = "Returns a plaer given their first and last name",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All players are returned",
              content = @Content(
              mediaType = "application/json", 
                  schema = @Schema(implementation = Player.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Players found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName",
              allowEmptyValue = false,
              required = false,
              description = "Player's first name"),
          @Parameter(name = "lastName",
          	  allowEmptyValue = false,
              required = false,
          	  description = "Player's last name"),      }
  )
  @GetMapping("/aPlayer")
  @ResponseStatus(code = HttpStatus.OK)
  List<Player> fetchAPlayer(                                                               
      @RequestParam(required = false)
      String firstName,
      @RequestParam(required = false)
      String lastName);             

  
  
  // @formatter:off
  @Operation( 
      summary = "Returns a Player when you have only their first name",
      description = "Returns the player entered",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A player gets returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Player.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Player with that name found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName",
              allowEmptyValue = false,
              required = false,
              description = "Player's first name"),
     }
  )
  @GetMapping("/firstName")
  @ResponseStatus(code = HttpStatus.OK)
  List<Player> fetchPlayerByFirstName(                                                     
      @RequestParam(required = false)
      String firstName);

  
  // @formatter:off
  @Operation(
      summary = "Creates a new Playerr",
      description = "Returns the created Player",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A new Player has been added",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Player.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Player were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName", 
              allowEmptyValue = false, 
              required = false, 
              description = "The players first name"),
          @Parameter(name = "lastName", 
          	  allowEmptyValue = false, 
          	  required = false, 
          	  description = "The play3ers last name"),
          @Parameter(name = "yearsPlayed", 
          	  allowEmptyValue = false, 
          	  required = false, 
          	  description = "Number of years played."),
          @Parameter(name = "jerseyNumber",
          	  allowEmptyValue = false,
          	  required = false,
          	  description = "Player's jersey number")
          
          }
  )
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Player createPlayer(String firstName, String lastName, int yearsPlayed, int jerseyNumber);                    
  
  
  // @formatter:off
  @Operation(
      summary = "updates a Player",
      description = "Returns the updated Player",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns updated Player",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Player.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Player's were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      }, 
          parameters = {
              @Parameter(name = "playerId", 
                  allowEmptyValue = false, 
                  required = false, 
                  description = "The Player's Id within our database")
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Player updatePlayer(                                                                     
       int playerId, 
      @Valid @RequestBody Player updatedPlayer); 
}

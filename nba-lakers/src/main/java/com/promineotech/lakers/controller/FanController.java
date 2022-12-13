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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;

import com.promineotech.lakers.entity.Fan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/Fans")
@OpenAPIDefinition(info = @Info(title = "Fans"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface FanController {
	
	// @formatter:off
	  @Operation(
	      summary = "Returns a list of Fans",
	      description = "Returns a list of Fans all first and last name",
	      responses = {
	          @ApiResponse(
	              responseCode = "200",
	              description = "A list of Fans is returned",
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Fan.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "No Fans were found with the input criteria",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "500", 
	              description = "An unplanned error occurred.",  
	              content = @Content(mediaType = "application/json"))
	      }

	  )
	  @GetMapping("/all")
	  @ResponseStatus(code = HttpStatus.OK)
	  List<Fan> fetchFans(                                                            
	    );
	  
	  
	  
	  // @formatter:off
	  @Operation( //!!!
	      summary = "Return a fan",
	      description = "Returns a fan given their first and last name",
	      responses = {
	          @ApiResponse(
	              responseCode = "200",
	              description = "All fans are returned",
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Fan.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "No Fans found",  
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
	              description = "Fan's first name"),
	          @Parameter(name = "lastName",
	          	 allowEmptyValue = false,
	          	 required = false,
	          	 description = "Fan's last name"),      }
	  )
	  @GetMapping("/aFan")
	  @ResponseStatus(code = HttpStatus.OK)
	  List<Fan> fetchAFan(                                                               
	      @RequestParam(required = false)
	      String firstName,
	      @RequestParam(required = false)
	      String lastName);             

	  
	  
	  
	  // @formatter:off
	  @Operation(
	      summary = "Returns a Fan when you have only their first name",
	      description = "Returns the fan entered",
	      responses = {
	          @ApiResponse(
	              responseCode = "200",
	              description = "A fan gets returned",
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Fan.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "No Fan with that name found",  
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
	              description = "Fan's first name"),
	     }
	  )
	  @GetMapping("/firstName")
	  @ResponseStatus(code = HttpStatus.OK)
	  List<Fan> fetchFanByFirstName(                                                     
	      @RequestParam(required = false)
	      String firstName);


	  
	  // @formatter:off
	  @Operation(
	      summary = "Creates a new Fan",
	      description = "Returns the created Fan",
	      responses = {
	          @ApiResponse(
	              responseCode = "201",
	              description = "A new Fan has been added",
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Fan.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "No Fan was found",  
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
	              description = "The fans first name"),
	          @Parameter(name = "lastName", 
	          	  allowEmptyValue = false, 
	          	  required = false, 
	          	  description = "The fans last name"),
	          @Parameter(name = "age", 
	          	  allowEmptyValue = false, 
	          	  required = false, 
	          	  description = "The fans age."),
	          @Parameter(name = "fanOf",
	        	  allowEmptyValue = false,
	        	  required = false,
	        	  description = " The fans favorite player")
	          }
	  )
	  @PostMapping
	  @ResponseStatus(code = HttpStatus.CREATED)
	  Fan createFan(String firstName, String lastName, int age, String fanOf);                    
	  
	  
	  
	  
	  // @formatter:off
	  @Operation(
	      summary = "updates a Fan",
	      description = "Returns the updated Fan",
	      responses = {
	          @ApiResponse(
	              responseCode = "200",
	              description = "Returns updated Fan",
	              content = @Content(
	                  mediaType = "application/json", 
	                  schema = @Schema(implementation = Fan.class))),
	          @ApiResponse(
	              responseCode = "400", 
	              description = "The request parameters are invalid",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "404", 
	              description = "No Fan was found",  
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(
	              responseCode = "500", 
	              description = "An unplanned error occurred.",  
	              content = @Content(mediaType = "application/json")),
	      }, 
	          parameters = {
	              @Parameter(name = "fanId", 
	                  allowEmptyValue = false, 
	                  required = false, 
	                  description = "The Fan's Id within our database")
	      }
	  )
	  @PutMapping
	  @ResponseStatus(code = HttpStatus.OK)
	  Fan updateFan(                                                                     
	       int fanId, 
	      @Valid @RequestBody Fan updatedFan); 
	}




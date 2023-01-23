package com.codility.tasks.spring.healthcheck;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
class HealthcheckController {

 public enum HealthCheckResponseFormats {
  FULL("full"),
  SHORT("short");

  private final String text;

  HealthCheckResponseFormats(final String text) {
   this.text = text;
  }
  public String getFormat() {
   return this.text;
  }
 }

 class HealthCheckResponseDto {

  @JsonInclude(JsonInclude.Include.NON_NULL) private ZonedDateTime currentTime;
  private final String status;

  public HealthCheckResponseDto(String status) {
   this.status = status;
  }

  public HealthCheckResponseDto(String status, ZonedDateTime currentTime) {
   this.status = status;
   this.currentTime = currentTime;
  }

  public String getStatus() {
   return status;
  }

  public String getCurrentTime() {
   return currentTime != null ? DateTimeFormatter.ISO_INSTANT.format(Instant.now()) : null;
  }

  public void setCurrentTime(ZonedDateTime currentTime) {
   this.currentTime = currentTime;
  }
 }


 @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
 public HealthCheckResponseDto healthcheck(@RequestParam String format) {
  if(HealthCheckResponseFormats.FULL.getFormat().equalsIgnoreCase(format)){
   return new HealthCheckResponseDto("OK", ZonedDateTime.now());
  } else if(HealthCheckResponseFormats.SHORT.getFormat().equalsIgnoreCase(format)){
   return new HealthCheckResponseDto("OK");
  }
  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error.");
 }

 @PutMapping(value = "/healthcheck")
 public void healthcheckPut() {
  throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
 }

 @PostMapping(value = "/healthcheck")
 public void healthcheckPost() {
  throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
 }


 @DeleteMapping(value = "/healthcheck")
 public void healthcheckDelete() {
  throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
 }

}

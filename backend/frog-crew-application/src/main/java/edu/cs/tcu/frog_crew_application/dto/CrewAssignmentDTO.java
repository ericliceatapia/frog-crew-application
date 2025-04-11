package edu.cs.tcu.frog_crew_application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CrewAssignmentDTO {

    private String sport;
    private String opponent;
    private LocalDate gameDate;
    private LocalTime gameTime;
    private String position;
    private String crewMemberName;
    private LocalTime reportTime;
    private String reportLocation;

    // Constructor
    public CrewAssignmentDTO(String sport, String opponent, LocalDate gameDate, LocalTime gameTime,
                              String position, String crewMemberName, LocalTime reportTime, String reportLocation) {
        this.sport = sport;
        this.opponent = opponent;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.position = position;
        this.crewMemberName = crewMemberName;
        this.reportTime = reportTime;
        this.reportLocation = reportLocation;
    }

    // Getters
    public String getSport() {
        return sport;
    }

    public String getOpponent() {
        return opponent;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public LocalTime getGameTime() {
        return gameTime;
    }

    public String getPosition() {
        return position;
    }

    public String getCrewMemberName() {
        return crewMemberName;
    }

    public LocalTime getReportTime() {
        return reportTime;
    }

    public String getReportLocation() {
        return reportLocation;
    }
}

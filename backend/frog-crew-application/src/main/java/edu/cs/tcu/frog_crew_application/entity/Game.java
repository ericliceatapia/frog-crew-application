package edu.cs.tcu.frog_crew_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameDate;
    private String gameTime;
    private String opponent;
    private String venue;
    private String requiredCrewPositions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRequiredCrewPositions() {
        return requiredCrewPositions;
    }

    public void setRequiredCrewPositions(String requiredCrewPositions) {
        this.requiredCrewPositions = requiredCrewPositions;
    }
}

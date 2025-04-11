package edu.cs.tcu.frog_crew_application.controller;

import edu.cs.tcu.frog_crew_application.dto.CrewAssignmentDTO;
import edu.cs.tcu.frog_crew_application.entity.CrewAssignment;
import edu.cs.tcu.frog_crew_application.service.CrewAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crew-assignments")
public class CrewAssignmentController {

    private final CrewAssignmentService crewAssignmentService;

    public CrewAssignmentController(CrewAssignmentService crewAssignmentService) {
        this.crewAssignmentService = crewAssignmentService;
    }

    @GetMapping
    public ResponseEntity<?> getCrewAssignments(@RequestParam String opponent) {
        List<CrewAssignmentDTO> assignments = crewAssignmentService.getCrewAssignmentsByOpponent(opponent);

        if (assignments.isEmpty()) {
            return ResponseEntity.ok("No crew members found for opponent: " + opponent);
        } else {
            return ResponseEntity.ok(assignments);
        }
    }

    @PostMapping
    public ResponseEntity<CrewAssignmentDTO> createCrewAssignment(@RequestBody CrewAssignment crewAssignment) {
        CrewAssignment savedAssignment = crewAssignmentService.saveCrewAssignment(crewAssignment);
        CrewAssignmentDTO assignmentDTO = new CrewAssignmentDTO(
                savedAssignment.getSport(),
                savedAssignment.getOpponent(),
                savedAssignment.getGameDate(),
                savedAssignment.getGameTime(),
                savedAssignment.getPosition(),
                savedAssignment.getCrewMemberName(),
                savedAssignment.getReportTime(),
                savedAssignment.getReportLocation()
        );
        return ResponseEntity.ok(assignmentDTO);
    }
}

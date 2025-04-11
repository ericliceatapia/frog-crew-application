package edu.cs.tcu.frog_crew_application.service;

import edu.cs.tcu.frog_crew_application.dto.CrewAssignmentDTO;
import edu.cs.tcu.frog_crew_application.entity.CrewAssignment;
import edu.cs.tcu.frog_crew_application.repository.CrewAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewAssignmentService {

    private final CrewAssignmentRepository crewAssignmentRepository;

    public CrewAssignmentService(CrewAssignmentRepository crewAssignmentRepository) {
        this.crewAssignmentRepository = crewAssignmentRepository;
    }

    public List<CrewAssignmentDTO> getCrewAssignmentsByOpponent(String opponent) {
        List<CrewAssignment> assignments = crewAssignmentRepository.findByOpponent(opponent);

        return assignments.stream()
                .map(assignment -> new CrewAssignmentDTO(
                        assignment.getSport(),
                        assignment.getOpponent(),
                        assignment.getGameDate(),
                        assignment.getGameTime(),
                        assignment.getPosition(),
                        assignment.getCrewMemberName(),
                        assignment.getReportTime(),
                        assignment.getReportLocation()
                ))
                .collect(Collectors.toList());
    }

    public CrewAssignment saveCrewAssignment(CrewAssignment crewAssignment) {
        return crewAssignmentRepository.save(crewAssignment);
    }    
}

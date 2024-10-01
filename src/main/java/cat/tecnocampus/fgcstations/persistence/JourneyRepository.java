package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.exception.JourneyDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {
    List<Journey> findAll();
    @Query("SELECT JourneyDTO(j.origin, j.destination) FROM Journey j")
    List<JourneyDTO> findAllDTO();
    Journey findJourneyByOriginNameAndDestinationName(String origin, String destination) throws JourneyDoesNotExistsException;
    JourneyId findJourneyIdByOriginNameAndDestinationName(String originName, String destinationName) throws JourneyDoesNotExistsException;
}

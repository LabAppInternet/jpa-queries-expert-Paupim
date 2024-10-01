package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.exception.StationDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, String> {
    //query to return a dto
    @Query("SELECT StationDTO(s.name, s.longitud, s.latitud) FROM Station s")
    List<StationDTO> findAllDTO();
    List<Station> findAll();

    Station findByName(String name) throws StationDoesNotExistsException;

    @Query("SELECT StationDTO(s.name, s.longitud, s.latitud) FROM Station s WHERE s.name = :name")
    StationDTO findDTObyName(String name) throws StationDoesNotExistsException;


}

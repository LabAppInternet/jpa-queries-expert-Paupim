package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.UserDTOInterface;
import cat.tecnocampus.fgcstations.application.DTOs.UserDTOnoFJ;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFavoriteJourney;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.FavoriteJourney;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username) throws UserDoesNotExistsException;
    UserDTOnoFJ findDTOnoFJByUsername(String username) throws UserDoesNotExistsException;
    UserDTOInterface findDTOInterfaceByUsername(String username) throws UserDoesNotExistsException;
    List<User> findAll();

    @Query (value = "SELECT u.username, u.name, u.secondName, u.email, COUNT(fj.journey_id) as num_fj " +
            "FROM user u " +
            "JOIN favorite_journey fj ON u.username = fj.username " +
            "GROUP BY u.username " +
            "ORDER BY num_fj " +
            "DESC LIMIT 3", nativeQuery = true)
    List<UserTopFavoriteJourney> findTopFavoriteJourneys();
    List<UserDTOInterface> findDTOInterfaceByNameAndSecondName(String name, String secondName);

}

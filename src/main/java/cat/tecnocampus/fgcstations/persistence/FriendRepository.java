package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.FriendId;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
    // TODO 20: find all the friends of a user given her username. You can solve this exercise without any sql query
    List<Friend> findAllFriendsByUser(User user);
    List<Friend> findAll();

    @Query(value = "SELECT u.username, u.name, u.secondName, u.email, COUNT(f.id) as numberOfFriends " +
        "FROM User u JOIN Friend f ON u.username = f.user.username " +
        "GROUP BY u.username " +
        "ORDER BY numberOfFriends DESC LIMIT 3 ")
    List<UserTopFriend> findTop3UsersWithMostFriends();

    List<FriendUserDTO> findByIdUsername(String friendName);
 }

package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Vote;

import java.time.LocalDate;

/**
 * Created by ToliNeTy on 06.03.2017.
 */
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    Vote getByUserIdAndRegistered(int userId, LocalDate registered);

    @Query("SELECT v FROM Vote v WHERE v.user.id = ?1 AND v.registered=?2")
    Vote get(int userId, LocalDate registered);

    int countByLunchId(int lunchId);

    @Query("SELECT COUNT (v) FROM Vote v LEFT JOIN FETCH Restaurant r ON v.lunch.restaurant = r WHERE r.id=?1")
    int countByRestaurant(int restaurantId);
}

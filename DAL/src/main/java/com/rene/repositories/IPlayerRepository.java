
package com.rene.repositories;

import com.rene.models.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("PlayerRepository")
@RepositoryRestResource(collectionResourceRel = "results",path = "players")
public interface IPlayerRepository extends PagingAndSortingRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.username = :username")
    Player getPlayerByUsername(@Param("username") String username);

    @Query("SELECT p FROM Player p WHERE p.username = :username AND p.password = :password")
    Player getPlayerByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

package com.fede.portfolio.repository;


import com.fede.portfolio.model.Info;
import com.fede.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RInfo extends JpaRepository<Info,Long> {
    public Optional<Info> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);

    Optional<Info> findByUser(User user);

    void deleteByUser(User user);
}

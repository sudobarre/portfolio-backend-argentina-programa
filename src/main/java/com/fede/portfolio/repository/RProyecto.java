
package com.fede.portfolio.repository;


import com.fede.portfolio.model.Proyecto;
import com.fede.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Long> {
    public Optional<Proyecto> findByNombreP(String nombreP);
    public boolean existsByNombreP(String nombreP);

    List<Proyecto> findAllByUser(User user);
}

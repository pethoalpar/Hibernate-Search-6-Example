package com.pethoalpar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pethoalpar.entity.Continent;

/**
 * @author alpar.petho
 *
 */
public interface ContinentRepository extends JpaRepository<Continent, Long>{

}

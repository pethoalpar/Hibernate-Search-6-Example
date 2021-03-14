package com.pethoalpar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author alpar.petho
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Indexed
@Entity
@Table(name = "continents")
public class Continent {	
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericField(sortable = Sortable.YES, searchable = Searchable.YES)
	private Long id;
	
	@FullTextField
	private String name;
	
	@GenericField(sortable = Sortable.YES, searchable = Searchable.YES)
	private Integer size;
	
	@OneToMany(targetEntity = Animal.class, mappedBy = "continent", cascade = CascadeType.ALL)
	@IndexedEmbedded
	private List<Animal> animals;
	
	

}

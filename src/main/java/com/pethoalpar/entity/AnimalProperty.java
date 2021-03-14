package com.pethoalpar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Indexed(enabled = false)
@Entity
@Table(name = "animal_properties")
public class AnimalProperty {
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericField(sortable = Sortable.YES, searchable = Searchable.YES)
	private Long id;
	
	@FullTextField
	private String propertyName;
	
	@FullTextField
	private String propertyValue;
	
	@ManyToOne(targetEntity = Animal.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id", referencedColumnName = "ID", nullable = false)
	@JsonIgnore
	private Animal animal;

	

}

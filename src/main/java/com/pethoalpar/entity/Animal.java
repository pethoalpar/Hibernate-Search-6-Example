package com.pethoalpar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.PropertyBinderRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.PropertyBinding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pethoalpar.converters.AnimalPropertyBinder;

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
@Table(name = "animals")
public class Animal {
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericField(sortable = Sortable.YES, searchable = Searchable.YES)
	private Long id;
	
	@FullTextField
	private String name;
	
	@ManyToOne(targetEntity = Continent.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "continent_id", referencedColumnName = "ID", nullable = false)
	@JsonIgnore
	private Continent continent;
	
	@OneToMany(targetEntity = AnimalProperty.class, mappedBy = "animal", cascade = CascadeType.ALL)
	@PropertyBinding(binder = @PropertyBinderRef(type = AnimalPropertyBinder.class))
	private List<AnimalProperty> properties;

}

package com.caglayan.fxmoviedb.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "genres")
public class GenreEntity implements Serializable {
	private static final long serialVersionUID = 8291026257524468948L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NaturalId
	@Column(name = "genre")
	private String genre;
	
	@ManyToMany(mappedBy = "genreSet")
	private Set<MovieEntity> movieSet = new HashSet<MovieEntity>();
	
	public GenreEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<MovieEntity> getMovieSet() {
		return movieSet;
	}

	public void setMovieSet(Set<MovieEntity> movieSet) {
		this.movieSet = movieSet;
	}
	
	public void addMovie(MovieEntity movie) {
		this.movieSet.add(movie);
	}

	@Override
	public int hashCode() {
		return Objects.hash(genre, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreEntity other = (GenreEntity) obj;
		return Objects.equals(genre, other.genre) && id == other.id;
	}
}

package com.caglayan.fxmoviedb.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "movies")
public class MovieEntity implements Serializable {
	private static final long serialVersionUID = -6354461699159439156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "movie_id")
	private long movieId;

	@Column(name = "title")
	private String title;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movieId"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<GenreEntity> genreSet = new HashSet<GenreEntity>();
	
	@Column(name = "imdb_id")
	private long imdbId;
	
	@Column(name = "tmdb_id")
	private long tmdbId;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<RatingEntity> ratings = new HashSet<RatingEntity>();
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TagEntity> tags = new HashSet<TagEntity>();
	
	
	public MovieEntity() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<GenreEntity> getGenreSet() {
		return genreSet;
	}

	public void setGenreSet(Set<GenreEntity> genreSet) {
		this.genreSet = genreSet;
	}

	public long getImdbId() {
		return imdbId;
	}

	public void setImdbId(long imdbId) {
		this.imdbId = imdbId;
	}

	public long getTmdbId() {
		return tmdbId;
	}

	public void setTmdbId(long tmdbId) {
		this.tmdbId = tmdbId;
	}

	public Set<RatingEntity> getRatings() {
		return ratings;
	}

	public void setRatings(Set<RatingEntity> ratings) {
		this.ratings = ratings;
	}

	public Set<TagEntity> getTags() {
		return tags;
	}

	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}

	public void addGenre(GenreEntity genre) {
		this.genreSet.add(genre);
	}
	
	public void addRating(RatingEntity rating) {
		this.ratings.add(rating);
	}
	
	public void addTag(TagEntity tag) {
		this.tags.add(tag);
	}
}

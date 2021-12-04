package com.caglayan.fxmoviedb.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tags")
public class TagEntity implements Serializable {
	private static final long serialVersionUID = 2500967136958848855L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "tag")
	private String tag;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp timeStamp;
	
	@ManyToOne
	@JoinColumn(name = "movieId")
	private MovieEntity movie;
	
	@Transient
	private Long movieId;
	
	public TagEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public MovieEntity getMovie() {
		return movie;
	}

	public void setMovie(MovieEntity movie) {
		this.movie = movie;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tag, timeStamp, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagEntity other = (TagEntity) obj;
		return id == other.id && Objects.equals(tag, other.tag) && Objects.equals(timeStamp, other.timeStamp)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "TagEntity [id=" + id + ", userId=" + userId + ", tag=" + tag + ", timeStamp=" + timeStamp + "]";
	}
	
	
}

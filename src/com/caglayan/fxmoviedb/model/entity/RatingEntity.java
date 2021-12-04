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

@Entity
@Table(name = "ratings")
public class RatingEntity implements Serializable {
	private static final long serialVersionUID = 1436507003890119032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_id")
	private long userId;

	// private long movieId;

	@Column(name = "rating", columnDefinition = "Decimal(2,1)")
	private double rating;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp timeStamp;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private MovieEntity movie;

	public RatingEntity() {
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, rating, timeStamp, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingEntity other = (RatingEntity) obj;
		return id == other.id && Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(timeStamp, other.timeStamp) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "RatingEntity [id=" + id + ", userId=" + userId + ", rating=" + rating + ", timeStamp=" + timeStamp
				+ "]";
	}

	
}

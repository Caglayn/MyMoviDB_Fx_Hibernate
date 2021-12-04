package com.caglayan.fxmoviedb.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.caglayan.fxmoviedb.model.entity.MovieEntity;
import com.caglayan.fxmoviedb.model.entity.RatingEntity;
import com.caglayan.fxmoviedb.model.entity.TagEntity;
import com.caglayan.fxmoviedb.model.enums.EGenre;
import com.caglayan.fxmoviedb.utils.PropertiesUtil;

public class MovieDao {

	public MovieDao() {
		super();
	}

	/**
	 * Reads movie.csv data from file and returns a LinkedList
	 */
	public HashMap<Long, MovieEntity> readMovieDataFromFile() {
		HashMap<Long, MovieEntity> moviesList = new HashMap<Long, MovieEntity>();
		File file = new File(PropertiesUtil.getInstance().getFilesPath() + "movies.csv");

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String lineReaded = "";
			reader.readLine();
			while ((lineReaded = reader.readLine()) != null) { // read movies.csv line by line
				MovieEntity tempMovieEntity = parseMovieLine(lineReaded);
				moviesList.put(tempMovieEntity.getMovieId(), tempMovieEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moviesList;
	}

	private MovieEntity parseMovieLine(String lineReaded) {
		MovieEntity tempMovieEntity = new MovieEntity();
		StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','
		String title = "";
		String genres = "";

		if (lineTokenizer.hasMoreTokens())
			tempMovieEntity.setMovieId(Long.valueOf(lineTokenizer.nextToken()));

		while (lineTokenizer.hasMoreTokens()) {
			String tempString = lineTokenizer.nextToken();

			if (lineTokenizer.hasMoreTokens()) {
				title = title + tempString;
			} else {
				genres = tempString;
			}
		}

		tempMovieEntity.setTitle(title);

		parseGenres(tempMovieEntity, genres);

		return tempMovieEntity;
	}

	private void parseGenres(MovieEntity tempMovieEntity, String genres) {
		StringTokenizer genresTokenizer = new StringTokenizer(genres, "|");
		while (genresTokenizer.hasMoreTokens()) {
			tempMovieEntity.addGenre(EGenre.byName(genresTokenizer.nextToken()));
		}
	}

	/**
	 * Reads links.csv data from file and returns a LinkedList
	 */
	public HashMap<Long, MovieEntity> readLinkDataFromFile(HashMap<Long, MovieEntity> moviesList) {
		String path = PropertiesUtil.getInstance().getFilesPath() + "links.csv";
		File file = new File(path);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String lineReaded = "";
			reader.readLine();
			while ((lineReaded = reader.readLine()) != null) { // read links.csv line by line
				parseLinkLine(moviesList, lineReaded);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moviesList;
	}

	private void parseLinkLine(HashMap<Long, MovieEntity> moviesList, String lineReaded) {
		MovieEntity tempMovieEntity = null;
		StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','
		if (lineTokenizer.hasMoreTokens()) {
			Long movieId = Long.valueOf(lineTokenizer.nextToken());
			tempMovieEntity = moviesList.get(movieId);
		}

		if (lineTokenizer.hasMoreTokens())
			tempMovieEntity.setImdbId(Long.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempMovieEntity.setTmdbId(Long.valueOf(lineTokenizer.nextToken()));
	}

	/**
	 * Reads ratings.csv data from file and returns a LinkedList
	 */
	public HashMap<Long, MovieEntity> readRatingDataFromFile(HashMap<Long, MovieEntity> moviesList) {
		String path = PropertiesUtil.getInstance().getFilesPath() + "ratings.csv";
		File file = new File(path);

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String lineReaded = "";
			reader.readLine();
			while ((lineReaded = reader.readLine()) != null) { // read links.csv line by line
				RatingEntity tempRatingEntity = parseRatingLine(lineReaded);
				MovieEntity tempMovieEntity = moviesList.get(tempRatingEntity.getMovieId());
				tempMovieEntity.addRating(tempRatingEntity);  // Adding relation
				tempRatingEntity.setMovie(tempMovieEntity);	  // Adding relation
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moviesList;
	}

	private RatingEntity parseRatingLine(String lineReaded) {
		RatingEntity tempRatingEntity = new RatingEntity();
		StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','

		if (lineTokenizer.hasMoreTokens())
			tempRatingEntity.setUserId(Long.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempRatingEntity.setMovieId(Long.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempRatingEntity.setRating(Double.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempRatingEntity.setTimeStamp(new Timestamp(Long.valueOf(lineTokenizer.nextToken())));
		
		return tempRatingEntity;
	}
	
	public HashMap<Long, MovieEntity> readTagDataFromFile(HashMap<Long, MovieEntity> moviesList) {
		String path = PropertiesUtil.getInstance().getFilesPath() + "tags.csv";
		File file = new File(path);

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String lineReaded = "";
			reader.readLine();
			while ((lineReaded = reader.readLine()) != null) { // read movies.csv line by line
				TagEntity tempTagEntity = parseTagLine(lineReaded);
				MovieEntity tempMovieEntity = moviesList.get(tempTagEntity.getMovieId());
				tempMovieEntity.addTag(tempTagEntity);		// Adding relation
				tempTagEntity.setMovie(tempMovieEntity);	// Adding relation
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moviesList;
	}

	private TagEntity parseTagLine(String lineReaded) {
		TagEntity tempTagEntity = new TagEntity();
		
		StringTokenizer lineTokenizer = new StringTokenizer(lineReaded, ","); // tokenize every line by ','

		if (lineTokenizer.hasMoreTokens())
			tempTagEntity.setUserId(Long.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempTagEntity.setMovieId(Long.valueOf(lineTokenizer.nextToken()));

		if (lineTokenizer.hasMoreTokens())
			tempTagEntity.setTag(lineTokenizer.nextToken());

		if (lineTokenizer.hasMoreTokens())
			tempTagEntity.setTimeStamp(new Timestamp(Long.valueOf(lineTokenizer.nextToken())));
		
		return tempTagEntity;
	}

	public void truncateAllTables() {
	}
}

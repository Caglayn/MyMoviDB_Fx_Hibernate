package com.caglayan.fxmoviedb.model.enums;

import java.util.HashMap;

public enum EGenre {
	IMAX(1, "IMAX"),
	Crime(2, "Crime"),
	Animation(3, "Animation"),
	Documentary(4, "Documentary"),
	Romance(5, "Romance"),
	Mystery(6, "Mystery"),
	Children(7, "Children"),
	Musical(8, "Musical"),
	FilmNoir(9, "Film-Noir"),
	Fantasy(10, "Fantasy"),
	Horror(11, "Horror"),
	Drama(12, "Drama"),
	Action(13, "Action"),
	Thriller(14, "Thriller"),
	Western(15, "Western"),
	SciFi(16, "Sci-Fi"),
	Comedy(17, "Comedy"),
	Adventure(18, "Adventure"),
	War(19, "War"),
	NO_GENRE(999, "(no genres listed)");
	
	private static final HashMap<String, EGenre> BY_NAME = new HashMap<String, EGenre>();
	
	static {
		for(EGenre genre : values()) {
			BY_NAME.put(genre.name, genre);
		}
	}
	
	public int id;
	public String name;
	
	private EGenre(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public static EGenre byName(String name) {
		return BY_NAME.get(name);
	}
}

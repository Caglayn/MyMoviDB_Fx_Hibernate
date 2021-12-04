module MovieDBFxHibernate {
	requires javafx.controls;
	requires javafx.fxml;
	requires hibernate.core;
	requires hibernate.jpa;
	requires java.sql;
	
	opens com.caglayan.fxmoviedb to javafx.graphics, javafx.fxml;
	opens com.caglayan.fxmoviedb.utils to org.hibernate;
	
	exports com.caglayan.fxmoviedb.controller to javafx.fxml;
	
}

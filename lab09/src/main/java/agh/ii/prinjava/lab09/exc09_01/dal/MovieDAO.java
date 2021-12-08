package agh.ii.prinjava.lab09.exc09_01.dal;

import java.sql.*;
import java.util.*;

/**
 * Movie Data Access Object
 *
 * @see <a href="https://www.oracle.com/java/technologies/dataaccessobject.html">Data Access Object</a>
 */
public class MovieDAO {
    private final String dbURL;

    public MovieDAO(String dbURL) {
        this.dbURL = dbURL;
    }

    /**
     * ex01 - the movies (only titles) directed (or co-directed) by a given director
     * Treeset is a "three"
     * setString is to file the parameter '?' in the movieOfDirectorSQL
     * ResultSet : recover the result of stmt in a SET.
     * executeQuery : to execute the query on the server
     * we loop the set and we add all our titles to the ts.
     */
    public Optional<Set<String>> moviesDirectedBy(String directorName) {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfDirectorSQL)) {
            //TODO complete implementation of ex01
            stmt.setString(1,directorName);
            //throw new RuntimeException("ex01: moviesDirectedBy not implemented");
            ResultSet resultat = stmt.executeQuery();
            SortedSet<String> ts = new TreeSet<String>();
            while (resultat.next()){
                ts.add(resultat.getString("title"));
            }
            // Adding elements into the TreeSet
            // using add()
            return Optional.of(ts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex02 - the movies (only titles) in which an actor played
     * same as the exercice 1
     */
    public Optional<Set<String>> moviesTheActorPlayedIn(String actorName) {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfActorSQL)) {
            stmt.setString(1,actorName);
            ResultSet resultat = stmt.executeQuery();
            SortedSet<String> ts = new TreeSet<String>();
            while (resultat.next()){
                ts.add(resultat.getString("title"));
            }
            // Adding elements into the TreeSet
            // using add()
            return Optional.of(ts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex03 - the number of movies per director (as a map)
     * we doesnt need to give any parameter to the sql function
     * we use a map who will contain String and Long
     * we loop through our result and we get the director and numOfmovies
     */
    public Optional<Map<String, Long>> numberOfMoviesPerDirector() {
        //TODO complete implementation of ex03
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numberOfMoviesPerDirectorSQL)) {
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Long> ts = new TreeMap<>();
            String director;
            Long numOfMovies;
            while (resultat.next()){
                //SELECT d.name AS director, count (*) AS numOfMovies
                director = resultat.getString("director");
                numOfMovies = resultat.getLong("numOfMovies");
                ts.put(director,numOfMovies);
            }
            return Optional.of(ts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex04 - the 10 directors with the most films on the list
     */
    public Optional<Map<String, Long>> numberOfMoviesPerTop10Director() {
        //TODO complete implementation of ex04
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numOfMoviesPerTop10DirectorSQL)) {
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Long> ts = new TreeMap<>();
            String top10director;
            Long numOfMoviesPerDirector;
            while (resultat.next()){
                top10director = resultat.getString("director");
                numOfMoviesPerDirector = resultat.getLong("numOfMovies");
                ts.put(top10director,numOfMoviesPerDirector);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex05 - the movies (only titles) made by each of the 10 directors
     * treeset is used to group all the titles : we ask the map if the treeset is empty
     * if it's empty, we create a new treeset
     */
    public Optional<Map<String, Set<String>>> moviesPerTop10Director() {
        //TODO complete implementation of ex05
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop10DirectorsSQL)){
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Set<String>> ts = new TreeMap<>();
            String director;
            String title;
            Set <String> treeset;
            while (resultat.next()){
                director = resultat.getString("director");
                title = resultat.getString("title");
                treeset = ts.get(director);
                if (treeset == null){
                    treeset = new TreeSet<>();
                }
                treeset.add(title);
                ts.put(director,treeset);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    /**
     * ex06 - the number of movies per actor (as a map)
     */
    public Optional<Map<String, Long>> numberOfMoviesPerActor() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numberOfMoviesPerActorSQL)) {
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Long> ts = new TreeMap<>();
            String actor;
            Long numOfMovies;
            while (resultat.next()){
                //SELECT d.name AS director, count (*) AS numOfMovies
                actor = resultat.getString("actor");
                numOfMovies = resultat.getLong("numOfMovies");
                ts.put(actor,numOfMovies);
            }
            return Optional.of(ts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    /**
     * ex07 - the 9 actors with the most films on the list
     */
    public Optional<Map<String, Long>> numberOfMoviesPerTop9Actor() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.numOfMoviesPerTop9ActorSQL)) {
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Long> ts = new TreeMap<>();
            String top9actor;
            Long numOfMoviesPerActor;
            while (resultat.next()){
                top9actor = resultat.getString("actor");
                numOfMoviesPerActor = resultat.getLong("numOfMovies");
                ts.put(top9actor,numOfMoviesPerActor);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex08 - the movies (only titles) of each of the 9 actors
     */
    public Optional<Map<String, Set<String>>> moviesPerTop9Actor() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop9ActorSQL)){
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Set<String>> ts = new TreeMap<>();
            String actor;
            String title;
            Set <String> treeset;
            while (resultat.next()){
                actor = resultat.getString("actor");
                title = resultat.getString("title");
                treeset = ts.get(actor);
                if (treeset == null){
                    treeset = new TreeSet<>();
                }
                treeset.add(title);
                ts.put(actor,treeset);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * ex09 - the 5 most frequent actor partnerships (i.e., appearing together most often)
     */
    public Optional<Map<String, Long>> top5FrequentActorPartnerships() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.top5ActorPartnershipsSQL)){
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Long> ts = new TreeMap<>();
            String actor1;
            String actor2;
            Long association;
            String concat_actors;
            while (resultat.next()) {
                actor1 = resultat.getString("actor1");
                actor2 = resultat.getString("actor2");
                concat_actors = actor1+ ", " + actor2;
                association = resultat.getLong("cnt");
                ts.put(concat_actors,association);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    /**
     * ex10 - the movies (only titles) of each of the 5 most frequent actor partnerships
     */
    public Optional<Map<String, Set<String>>> moviesPerTop5ActorPartnerships() {
        try (Connection con = DriverManager.getConnection(dbURL);
             PreparedStatement stmt = con.prepareStatement(MovieSQLs.moviesOfTop5ActorPartnershipsSQL)){
            ResultSet resultat = stmt.executeQuery();
            TreeMap<String,Set<String>> ts = new TreeMap<>();
            String actor1,actor2,concat_actors,title;
            Set <String> treeset;
            while (resultat.next()){
                actor1 = resultat.getString("actor1");
                actor2 = resultat.getString("actor2");
                title = resultat.getString("title");
                concat_actors = actor1+ ", " + actor2;
                treeset = ts.get(concat_actors);
                if (treeset == null){
                    treeset = new TreeSet<>();
                }
                treeset.add(title);
                ts.put(concat_actors,treeset);
            }
            return Optional.of(ts);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

class MovieSQLs {
    // ex01
    static final String moviesOfDirectorSQL = """
            SELECT m.title AS title
            FROM movies m, movie_directors md, directors d
            WHERE m.id = md.movie_id AND md.director_id = d.id AND d.name = ?
            """;

    // ex02
    static final String moviesOfActorSQL = """
            SELECT m.title AS title
            FROM movies m, movie_actors ma, actors a
            WHERE m.id = ma.movie_id AND ma.actor_id = a.id AND a.name = ?
            """;

    // ex03
    static final String numberOfMoviesPerDirectorSQL = """
            SELECT d.name AS director, count (*) AS numOfMovies
            FROM directors d, movie_directors md
            WHERE d.id = md.director_id
            GROUP BY d.name
            """;

    // ex04
    static final String numOfMoviesPerTop10DirectorSQL = """
            SELECT d.name AS director, count (*) AS numOfMovies
            FROM directors d, movie_directors md
            WHERE d.id = md.director_id
            GROUP BY d.name
            ORDER BY numOfMovies DESC
            LIMIT 10
            """;

    // ex05
    static final String moviesOfTop10DirectorsSQL = """
            SELECT d.name AS director, m.title AS title,
              (SELECT count(*) FROM movie_directors WHERE director_id = d.id GROUP BY director_id) AS numOfMovies
            FROM directors d, movie_directors md, movies m
            WHERE d.id = md.director_id
            AND md.movie_id = m.id
            AND d.id IN (SELECT d.id
                         FROM directors d, movie_directors md
                         WHERE d.id = md.director_id
                         GROUP BY d.id
                         ORDER BY count (*) DESC
                         LIMIT 10)
            ORDER BY numOfMovies DESC, d.name
            """;

    // ex06
    static final String numberOfMoviesPerActorSQL = """
            SELECT a.name AS actor, count (*) AS numOfMovies
            FROM actors a, movie_actors ma
            WHERE a.id = ma.actor_id
            GROUP BY a.name
            """;

    // ex07
    static final String numOfMoviesPerTop9ActorSQL = """
            SELECT a.name AS actor, count (*) AS numOfMovies
            FROM actors a, movie_actors ma
            WHERE a.id = ma.actor_id
            GROUP BY a.name
            ORDER BY numOfMovies DESC
            LIMIT 9
            """;

    // ex08
    static final String moviesOfTop9ActorSQL = """
            SELECT a.name AS actor, m.title AS title,
              (SELECT count(*) FROM movie_actors WHERE actor_id = a.id GROUP BY actor_id) AS numOfMovies
            FROM actors a, movie_actors ma, movies m
            WHERE a.id = ma.actor_id
            AND ma.movie_id = m.id
            AND a.id IN (SELECT a.id
                         FROM actors a, movie_actors ma
                         WHERE a.id = ma.actor_id
                         GROUP BY a.id
                         ORDER BY count (*) DESC
                         LIMIT 9)
            ORDER BY numOfMovies DESC, a.name
            """;

    // ex09
    static final String top5ActorPartnershipsSQL = """
            SELECT t1.name AS actor1, t2.name AS actor2, count(*) cnt
            FROM (SELECT a.id, a.name, ma.movie_id
                  FROM actors a, movie_actors ma
                  WHERE a.id = ma.actor_id) t1
            JOIN (SELECT a.id, a.name, ma.movie_id
                  FROM actors a, movie_actors ma
                  WHERE a.id = ma.actor_id) t2
            ON t1.id < t2.id AND t1.movie_id = t2.movie_id
            GROUP BY 1,2
            ORDER BY cnt DESC
            LIMIT 5
            """;

    // ex10
    static final String moviesOfTop5ActorPartnershipsSQL = """
            SELECT actor1, actor2, cnt, title
            FROM (SELECT t1.id AS a1_id, t1.name AS actor1, t2.id AS a2_id, t2.name as actor2, count(*) cnt
                  FROM (SELECT a.id, a.name, ma.movie_id
                        FROM actors a, movie_actors ma
                        WHERE a.id = ma.actor_id) t1
                  JOIN (SELECT a.id, a.name, ma.movie_id
                        FROM actors a, movie_actors ma
                        WHERE a.id = ma.actor_id) t2
                  ON t1.id < t2.id AND t1.movie_id = t2.movie_id
                  GROUP BY a1_id, a2_id
                  ORDER BY cnt DESC
                  LIMIT 5)
            JOIN (SELECT t3.title, t3.actor_id AS a3_id, t4.actor_id AS a4_id
                  FROM (SELECT m.title, m.id, ma.actor_id
                        FROM movies m, movie_actors ma
                        WHERE m.id = ma.movie_id) t3
                  JOIN (SELECT m.title, m.id, ma.actor_id
                        FROM movies m, movie_actors ma
                        WHERE m.id = ma.movie_id) t4
                  ON t3.actor_id < t4.actor_id AND t3.title = t4.title)
            ON (a1_id = a3_id AND a2_id = a4_id) OR (a1_id = a4_id AND a2_id = a3_id)
            ORDER BY cnt DESC, actor1, actor2
            """;
}

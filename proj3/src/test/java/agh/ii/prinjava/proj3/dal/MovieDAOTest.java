package agh.ii.prinjava.proj3.dal;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDAOTest {

    private static final String dbURL = "jdbc:sqlite:datasources/imdb_top250.db";
    MovieDAO movieDAO = new MovieDAO(dbURL);

    @Test
    void moviesDirectedBy() {
        assertThat(movieDAO.moviesDirectedBy("Christopher Nolan").orElseThrow())
                .containsExactlyInAnyOrder("The Dark Knight", "Inception", "Interstellar",
                        "The Prestige", "Memento", "The Dark Knight Rises", "Batman Begins");

        assertThat(movieDAO.moviesDirectedBy("Guy Ritchie").orElseThrow())
                .containsExactlyInAnyOrder("Snatch", "Lock, Stock and Two Smoking Barrels");

        assertThat(movieDAO.moviesDirectedBy("Quentin Tarantino").orElseThrow())
                .containsExactlyInAnyOrder("Pulp Fiction", "Django Unchained", "Reservoir Dogs",
                        "Inglourious Basterds", "Kill Bill: Vol. 1", "Sin City");
    }

    @Test
    void moviesTheActorPlayedIn() {
        //TODO complete the test of ex02
        assertThat(movieDAO.moviesTheActorPlayedIn("Tom Hanks").orElseThrow())
                .containsExactlyInAnyOrder("Forrest Gump", "Saving Private Ryan", "The Green Mile",
                        "Toy Story 3", "Toy Story", "Catch Me If You Can");

        assertThat(movieDAO.moviesTheActorPlayedIn("Tom Hardy").orElseThrow())
                .containsExactlyInAnyOrder("Inception", "The Dark Knight Rises",
                        "Warrior", "Mad Max: Fury Road", "The Revenant");

        assertThat(movieDAO.moviesTheActorPlayedIn("Robert De Niro").orElseThrow())
                .containsExactlyInAnyOrder("The Godfather: Part II", "Goodfellas", "Once Upon a Time in America",
                        "Raging Bull", "Heat", "Casino", "The Deer Hunter");
    }
    /**
     * the method that we create in exercice 3 return an Optionnal Map. So we need to capture the value of
     * our Map using .get()
     */
    @Test
    void numberOfMoviesPerDirector() {
        //TODO complete the test of ex03
        Map<String, Long> numOfMoviesPerDirector = movieDAO.numberOfMoviesPerDirector().get();

        assertThat(numOfMoviesPerDirector.get("Alfred Hitchcock")).isEqualTo(9);
        assertThat(numOfMoviesPerDirector).containsAllEntriesOf(Map.of(
                "Tim Burton", 1L,
                "Guy Ritchie", 2L,
                "Wes Anderson", 1L,
                "Christopher Nolan", 7L,
                "Quentin Tarantino", 6L,
                "Alfred Hitchcock", 9L));
    }

    @Test
    void numberOfMoviesPerTop10Director() {
        //TODO complete the test of ex04
        Map<String, Long> tenMostFreqDirectors = movieDAO.numberOfMoviesPerTop10Director().get();

        assertThat(tenMostFreqDirectors).containsAllEntriesOf(Map.of(
                "Alfred Hitchcock", 9L,
                "Stanley Kubrick", 8L,
                "Martin Scorsese", 7L,
                "Steven Spielberg", 7L,
                "Christopher Nolan", 7L,
                "Billy Wilder", 7L,
                "Quentin Tarantino", 6L,
                "Charles Chaplin", 5L,
                "Ridley Scott", 4L,
                "Frank Capra", 4L));
    }


    @Test
    void moviesPerTop10Director() {
        //TODO complete the test of ex05
        Map<String, Set<String>> moviesPerDirector = movieDAO.moviesPerTop10Director().get();

        assertThat(moviesPerDirector.keySet()).containsExactlyInAnyOrder(
                "Alfred Hitchcock", "Stanley Kubrick", "Martin Scorsese", "Steven Spielberg",
                "Christopher Nolan", "Billy Wilder", "Quentin Tarantino", "Charles Chaplin",
                "Ridley Scott", "Frank Capra");

        assertThat(moviesPerDirector.get("Christopher Nolan")).containsExactlyInAnyOrder(
                "The Dark Knight", "Inception", "Interstellar", "The Prestige", "Memento",
                "The Dark Knight Rises", "Batman Begins"
        );

        assertThat(moviesPerDirector.get("Quentin Tarantino")).containsExactlyInAnyOrder(
                "Pulp Fiction", "Django Unchained", "Reservoir Dogs", "Inglourious Basterds",
                "Kill Bill: Vol. 1", "Sin City"
        );

        assertThat(moviesPerDirector.get("Ridley Scott")).containsExactlyInAnyOrder(
                "Gladiator", "Alien", "Blade Runner", "The Martian"
        );
    }



    @Test
    void numberOfMoviesPerActor() {
        //TODO complete the test of ex06
        Map<String, Long> numOfMoviesPerActor = movieDAO.numberOfMoviesPerActor().get();

        assertThat(numOfMoviesPerActor.size()).isEqualTo(773);
        assertThat(numOfMoviesPerActor).containsAllEntriesOf(Map.of(
                "Tom Hardy", 5L,
                "Derek Jacobi", 1L));
    }

    @Test
    void numberOfMoviesPerTop9Actor() {
        //TODO complete the test of ex07
        Map <String, Long> nineMostFreqActors = movieDAO.numberOfMoviesPerTop9Actor().get();

        assertThat(nineMostFreqActors).containsAllEntriesOf(Map.of(
                "Leonardo DiCaprio", 8L,
                "Harrison Ford", 7L,
                "James Stewart", 7L,
                "Robert De Niro", 7L,
                "Tom Hanks", 6L,
                "William Holden", 5L,
                "Paul Newman", 5L,
                "Tom Hardy", 5L,
                "Cary Grant", 5L));
    }

    @Test
    void moviesPerTop9Actor() {
        //TODO complete the test of ex08
        Map<String, Set<String>> moviesPerActor = movieDAO.moviesPerTop9Actor().get();

        assertThat(moviesPerActor.keySet()).containsExactlyInAnyOrder(
                "Leonardo DiCaprio", "Harrison Ford", "James Stewart", "Robert De Niro",
                "Tom Hanks", "William Holden", "Paul Newman", "Tom Hardy", "Cary Grant");

        assertThat(moviesPerActor.get("Leonardo DiCaprio")).containsExactlyInAnyOrder(
                "Inception", "The Departed", "Django Unchained", "The Wolf of Wall Street",
                "Shutter Island", "Catch Me If You Can", "Blood Diamond", "The Revenant");

        assertThat(moviesPerActor.get("Tom Hanks")).containsExactlyInAnyOrder(
                "Forrest Gump", "Saving Private Ryan", "The Green Mile", "Toy Story 3",
                "Toy Story", "Catch Me If You Can");

        assertThat(moviesPerActor.get("Tom Hardy")).containsExactlyInAnyOrder(
                "Inception", "The Dark Knight Rises", "Warrior", "Mad Max: Fury Road",
                "The Revenant");
    }

    @Test
    void top5FrequentActorPartnerships() {
        Map<String, Long> fiveMostFreqActorDuos = movieDAO.top5FrequentActorPartnerships().get();

        assertThat(fiveMostFreqActorDuos).containsAllEntriesOf(Map.of(
                "Carrie Fisher, Mark Hamill", 4L,
                "Joe Pesci, Robert De Niro", 4L,
                "Carrie Fisher, Harrison Ford", 4L,
                "Harrison Ford, Mark Hamill", 4L,
                "Christian Bale, Michael Caine", 3L));
    }

    @Test
    void moviesPerTop5ActorPartnerships() {
        Map<String, Set<String>> moviesPerActorDuo = movieDAO.moviesPerTop5ActorPartnerships().get();

        assertThat(moviesPerActorDuo.keySet()).containsExactlyInAnyOrder(
                "Carrie Fisher, Mark Hamill",
                "Joe Pesci, Robert De Niro",
                "Carrie Fisher, Harrison Ford",
                "Harrison Ford, Mark Hamill",
                "Christian Bale, Michael Caine");

        assertThat(moviesPerActorDuo.get("Carrie Fisher, Mark Hamill")).containsExactlyInAnyOrder(
                "Star Wars: Episode V - The Empire Strikes Back",
                "Star Wars: Episode IV - A New Hope",
                "Star Wars: Episode VI - Return of the Jedi",
                "Star Wars: The Force Awakens");

        assertThat(moviesPerActorDuo.get("Joe Pesci, Robert De Niro")).containsExactlyInAnyOrder(
                "Goodfellas", "Once Upon a Time in America", "Raging Bull", "Casino");

        assertThat(moviesPerActorDuo.get("Christian Bale, Michael Caine")).containsExactlyInAnyOrder(
                "The Dark Knight", "The Prestige", "Batman Begins");
    }
}
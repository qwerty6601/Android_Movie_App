package com.example.demo.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;

/***
 * Main
 * Hash map which maps players (key) —> hash map
 * Map<Player, Map<Movie,Double>> (double for counter)
 * - Counter is just value of player
 *
 * Map<Movie, Double> totalCounter
 */

// 1. delete all movies in db w/ "not given" attributes
@Service
@RestController
public class Main {

    // Linkedlist ist schneller für den Anwendungsfall
    List<Movie> movies = new LinkedList<>();
    List<Counter> counters = new LinkedList<>();
    ArrayList<Double> probArray = new ArrayList<>();

    private final MovieRepository movieRepository;
    private final CountersRepository countersRepository;
    static ArrayList<Question> questionArray = new ArrayList<>(); // array which keeps track which (n how many) questions asked
    Answer a = new Answer(0, true, 0);

    @Autowired
    public Main(MovieRepository movieRepository, CountersRepository countersRepository) {
        this.movieRepository = movieRepository;
        this.countersRepository = countersRepository;
    }

    public List<Movie> getAllMovies() {
        System.out.println("getMovies");
        Iterable<Movie> movieIterable = movieRepository.findAll();
        movieIterable.forEach(movies::add);
        return movies;
    }

    // e.g. every time backend gets info from frontend w/ gameID, for every player, do gameIDArray.add(gameID)
    ArrayList<Integer> gameIDArray = new ArrayList<>();
    //maps movieCol -> (candidate -> counter)
    Map<String, Map<String, Counter>> maleCounters = new HashMap<>();
    Map<String, Map<String, Counter>> femaleCounters = new HashMap<>();
    Map<String, Map<String, Counter>> otherCounters = new HashMap<>();

    /***Counter: col --> cand, counter (for male bzw. female)
     * movieAttributes: movieCol --> function (movie, candidate)
     */

    public Map<String, Map<String, Counter>> getCounterMapForGender(int gender) {
        Map<String, Map<String, Counter>> countersMap;
        if (gender == 0) {
            countersMap = maleCounters;
            maleCounters.clear();
            for (Counter c : countersRepository.findByGender(gender)) {
                Map<String, Counter> map1 = new HashMap<>();
                map1.put(c.getColcand(), c);
                countersMap.put(c.getColname(), map1);
            }
        } else if (gender == 1) {
            countersMap = femaleCounters;
            femaleCounters.clear();
            for (Counter c : countersRepository.findByGender(gender)) {
                Map<String, Counter> map1 = new HashMap<>();
                map1.put(c.getColcand(), c);
                countersMap.put(c.getColname(), map1);
            }
        } else {
            countersMap = otherCounters;
            otherCounters.clear();
            for (Counter c : countersRepository.findByGender(gender)) {
                Map<String, Counter> map1 = new HashMap<>();
                map1.put(c.getColcand(), c);
                countersMap.put(c.getColname(), map1);
            }
        }
        return countersMap;
    }

    @PostMapping("/newmovie")
    public void askUserForData(String n) {
        MovieService s = new MovieService(movieRepository);
        List<Movie> myMovies = getAllMovies();
        for (Movie m : myMovies) {
            if (m.getMovie_title().equals(n)) {
                movieRepository.increaseCounterValue(m.getMovie_title());
                return;
            }
        }
        Movie m = new Movie(n, "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "2");
        movieRepository.save(m);
    }


    /***
     * probability of the movies
     * @param movies
     * @param counterMap col --> cand, counter
     * @return
     */
    public HashMap<Movie, Double> movieProb(List<Movie> movies, Map<String, Map<String, Counter>> counterMap, int gender) {
        HashMap<Movie, Double> movieProbabilities = new HashMap<>(); // m={genre:action, duration:90}--> P(m) = 3/5 * 4/5
        HashMap<String, Double> colToCounterSum = new HashMap<>();

        // to find sum_x(counter(col, all attributes of col)
        for (String col : Movie.movieAttributes.keySet()) { // for all cols of movieAttributes
            double counterSum = 0;
            for (Counter c : counterMap.get(col).values()) {
                counterSum += c.value;
            }
            colToCounterSum.put(col, counterSum);
        }

        for (Movie m : movies) {
            double prob = 1;
            //for (String col : m.movieAttributes.keySet()) {
            //  String cand = m.movieAttributes.get(col).apply(m);
            // counterMap: col --> cand, counter
            for (String col : counterMap.keySet()) {
                for (Map.Entry<String, Counter> attrEntry : counterMap.get(col).entrySet()) {
                    String cand = attrEntry.getKey();
                    try {
                        Counter c = counterMap.get(col).get(cand);
                        double count = c.value;
                        prob *= count / colToCounterSum.get(col);
                    } catch (NullPointerException e) {
                        System.out.println("Eintrag nicht gefunden -> Eintrag hinzugefuegt");
                        countersRepository.save(new Counter(gender, col, cand, 1));
                    }
                }
                movieProbabilities.put(m, prob);
            }
        }
        return movieProbabilities;
    }

    // 1st to call
    // frontend fills parameters in getAnswer(...) so that backend can get it
    @PostMapping("/answerX") // App: when user answers yes/no --> /questionX// backend calls tpsose.../answerX
    public Answer getAnswer(int qId, boolean yesOrNo, int pId, int gender) {

        //try {
        // Hashmap: Integer --> ArrayList<Question> questions
        ArrayList<Question> questions = multiplayerAsked.get(pId);
        Question q2 = questions.get(questions.size() - 1);
        //Question q1 = asked.get(qId);
        if (yesOrNo) {
            //askedTrue.add(q1)
            ArrayList<Question> questionsTrue = multiplayerAskedTrue.get(pId);
            questionsTrue.add(q2);
            multiplayerAskedTrue.put(pId, questionsTrue);
        }


        System.out.println("Antwort: " + qId + yesOrNo + pId + gender);

        a = new Answer(qId, yesOrNo, pId);

        if (qId == 999999) {
            System.out.println("c1");
            compareGuessWithAnswer(multiplayerGuess.get(pId), askedTrue, gender);
        }

        // Look up wich question was, get the answer asked and remove movies
        for (Question i : multiplayerAsked.get(pId)) {
            if (i.id == qId) {
                refineMovies(i, yesOrNo, pId);
                System.out.println(multiplayerMovies.get(pId).size());
                if (multiplayerMovies.get(pId).size() < 10) {
                    for (Movie m : multiplayerMovies.get(pId)) {
                        System.out.println(m.getMovie_title());
                    }
                }
                return a;
            }
        }
        return a;
    }

    public String questionOutput(Question q, int pId) {
        if (multiplayerMovies.get(pId).size() == 0) {
            return "Sorry we could not find your movie";
        }
        switch (q.movieCol) {
            case "movie_title":
                return "Is " + q.crit + " your movie?";
            case "duration":
                return "Is your movie shorter than " + q.crit + " minutes?";
            case "genre":
                return "Does one of the genres " + q.crit + " describes your movie?";
            case "actor_1_name":
            case "actor_3_name":
            case "actor_2_name":
                return "Does " + q.crit + " plays in your movie?";
            case "director_name":
                return "Is " + q.crit + " the director of your movie?";
            case "plot_keywords":
                return "Does one of the following keywords describes your movie? " + q.crit;
            case "m_language":
                return "Is " + q.crit + " the original language of your movie?";
            case "country":
                return "Is " + q.crit + " the country of production of your movie?";
            case "content_rating":
                return "Has your movie a " + q.crit + " content rating? (R=FSK18, PG13=FSK12, PG=FSK6, G=FSK0)";
            case "title_year":
                return "Was your movie published before " + q.crit + "?";
            case "imdb_score":
                return "Is the Imdb-Score of your movie " + q.crit + "?";
        }
        return "Is " + q.movieCol + " " + q.crit + "?";
    }

    /// the integer is the gameId,
    // every game will have its own list with movies
    HashMap<Integer, List<Movie>> multiplayerMovies = new HashMap<>();

    // the integer is the gameId,
    // every game will have its own list with questions
    HashMap<Integer, List<Question>> multiplayerQuestions = new HashMap<>();


    // all questions we already asked
    LinkedList<Question> asked = new LinkedList<>();
    LinkedList<Question> askedTrue = new LinkedList<>();
    HashMap<Integer, ArrayList<Question>> multiplayerAsked = new HashMap<>();
    HashMap<Integer, ArrayList<Question>> multiplayerAskedTrue = new HashMap<>();
    HashMap <Integer, List<String>> lastCrit = new HashMap<>();

    HashMap<Integer, String> multiplayerGuess = new HashMap<>();
    // all generated questions
    List<Question> questions;
    String guess = "c1";

    /**
     * Maps movie column name -> (attribute value -> List of of movies that have that value for that attribute )
     */
    HashMap<String, HashMap<String, List<Movie>>> attributeLookup = new HashMap<>();

    public void buildAttributeLookup(List<Movie> movies) {
        attributeLookup.clear();
        for (Map.Entry<String, Function<Movie, String>> attrEntry : Movie.movieAttributes.entrySet()) {
            HashMap<String, List<Movie>> attributeToMovie = new HashMap<>();
            Function<Movie, String> fktn = attrEntry.getValue();

            for (Movie m : movies) {
                String cand = fktn.apply(m);
                List<Movie> moviesList = attributeToMovie.get(cand);
                if (moviesList == null) {
                    moviesList = new ArrayList<>();
                    attributeToMovie.put(cand, moviesList);
                }
                moviesList.add(m);
            }
            attributeLookup.put(attrEntry.getKey(), attributeToMovie);
        }
    }

    /**
     * RequestMapping statt GetMapping bc frontend calls it
     * every time a user clicks yes/no) call this method
     *
     * @param g      how many questions we asked already
     * @param pId    id of the current game - we can play many games at the same time
     * @param gender gender of player
     */
    @RequestMapping("/questionX") // App: user gets question --> /questionX
    public QuestionText callOnQuestionAnswer(int g, int pId, int gender) {
        if (g == 0) {
            //getAllMovies();
            multiplayerMovies.put(pId, getAllMovies());
            multiplayerAsked.put(pId, new ArrayList<Question>());
            multiplayerAskedTrue.put(pId, new ArrayList<Question>());
            lastCrit.put(pId, new ArrayList<String>());
        }

        if (g > 0) multiplayerQuestions.remove(pId);
        multiplayerQuestions.put(pId, generateQuestions(multiplayerMovies.get(pId), pId));


        Map<String, Map<String, Counter>> countersMap = getCounterMapForGender(gender);
        System.out.println("Mapsize: " + countersMap.size());

        HashMap<Movie, Double> movieToProb = movieProb(multiplayerMovies.get(pId), countersMap, gender);
        double totalProb = totalProb(movieToProb);

        buildAttributeLookup(multiplayerMovies.get(pId));

        double[] probabilities = new double[multiplayerQuestions.get(pId).size()];

        for (int i = 0; i < multiplayerQuestions.get(pId).size(); i++) {
            if (multiplayerMovies.get(pId).size() > 0) {
                double prob = multiplayerQuestions.get(pId).get(i).yesProbability(movieToProb, attributeLookup, totalProb);
                probabilities[i] = prob;
            }
        }

        Question q = findClosestToHalfProb(multiplayerQuestions.get(pId), probabilities);

        String questionAsText = questionOutput(q, pId);
        ArrayList<Question> questions = multiplayerAsked.get(pId);
        questions.add(q);
        multiplayerAsked.put(pId, questions);

        System.out.println("Aktuelle Frage: " + q.id);

        String movieTitle = multiplayerMovies.get(pId).get(0).getMovie_title();
        String movieAsText = "Is " + movieTitle + " your movie?";
        QuestionText text;


        if (multiplayerMovies.get(pId).size() > 1 && g < 14) { //14 weil wir bei 0 anfangen
            //refineMovies(q, ja);
            text = new QuestionText(q.id, questionAsText);
            return text; // frontend recieves this
            //} else if (movies.size() > 1 && g == 15) {
        } else if (multiplayerMovies.get(pId).size() > 1 && g == 14) { //14 weil wir bei 0 anfangen
            guess = movieTitle;
            multiplayerGuess.put(pId, guess);
            text = new QuestionText(999999, movieAsText);
            //isYourMovie.put(pId, yourMovies.get(pId).get(0));
            return text; // frontend recieves this
            //} else if (movies.size() == 1) {
        } else if (multiplayerMovies.get(pId).size() == 1) {
            guess = movieTitle;
            multiplayerGuess.put(pId, guess);
            text = new QuestionText(999999, movieAsText);
            //isYourMovie.put(pId, yourMovies.get(pId).get(0));
            return text; // frontend recieves this
            //} else if (movies.size() == 0) {
        } else if (multiplayerMovies.get(pId).size() == 0) {
            text = new QuestionText(q.id, questionAsText);
            return text;
        }
        text = new QuestionText(q.id, "Error");
        //getAnswer(8641, true, pId, gender, q);
        return text;
    }


    public double totalProb(HashMap<Movie, Double> movieProbabilities) {
        double totalProb = 0;
        for (double prob : movieProbabilities.values()) {
            totalProb += prob;
        }
        return totalProb;
    }

    /***
     * Increse gender and movie counter if guess was correct
     * @param movieTitle correct movie title
     * @param gender player's gender
     */

    public void compareGuessWithAnswer(String movieTitle, List<Question> asked, int gender) {
        //Map <String, Map <String, Counter>> counterMap = getCounterMapForGender(gender);
        //if (movieTitle.equals(movies.get(0).getMovie_title()))
        //  System.out.println(movies.get(0).getMovie_title());
        System.out.println("Fange mit den countern an");
        // GENDER COUNTER
        for (Question q : asked) {
            System.out.println("Mache gender counter");
            // increase hashmap counter for col and cand (question)
            String col = q.movieCol;
            String cand = q.crit;
            // GenderCounters erhoehen
            try {
                List<Counter> c = countersRepository.findCounterEntryByColAndCan(col, cand);
                for (Counter x : c) {
                    countersRepository.increaseCounterValue(x.getId(), gender);
                    System.out.println(x.value);
                }
            } catch (NullPointerException e) {
                System.out.println("Eintrag zum Counter hinzugefuegt");
                Counter c1 = new Counter(gender, col, cand, 2);
                countersRepository.save(c1);
                countersRepository.increaseCounterValue(c1.getId(), gender);
            }
        }

        //MOVIE COUNTER
        System.out.println("Mache movie counter");
        // MovieCounter erhoehen
        try {
            Optional<Movie> m = movieRepository.findMovieByMovieTitle(movieTitle);
            movieRepository.increaseCounterValue(m.get().getMovie_title());
        } catch (NullPointerException e) {
            System.out.println("Movie nicht in der Datenbank");
        }
        System.out.println("Geschafft");
    }


    int id = 2;

    public List<Question> generateQuestions(Collection<Movie> movies, int pId) {
        ArrayList<Question> questions = new ArrayList<>();
        for (String col : Movie.movieAttributes.keySet()) {
            //if (col.equals("imdb_score")) continue;
            HashSet<String> candidates = new HashSet<>();
            Function<Movie, String> getCandFromMovie = Movie.movieAttributes.get(col);
            int i = 0;
            for (Movie m : movies) {
                //i++;
                candidates.add(getCandFromMovie.apply(m));
            }

            if (candidates.size() >= 1)
                for (String cand : candidates) {
                    // System.out.println("setQuestions");
                    //counters.add(new Counter(0, col, cand, 1));
                    //counters.add(new Counter(1, col, cand, 1));
                    //counters.add(new Counter(2, col, cand, 1));
                    if (!(cand.equals("not_given") ||
                            cand.equals("1") ||
                            cand.equals("0") ||
                            cand.equals("unknown") ||
                            cand.equals("Unknown") ||
                            cand.equals("Unrated") ||
                            cand.equals("Not Rated") ||
                            cand.equals("Approved") ||
                            cand.equals("TV-14") ||
                            cand.equals("TV-MA") ||
                            cand.equals("TV-Y") ||
                            cand.equals("X") ||
                            cand.equals("GP") ||
                            cand.equals("NC-17") ||
                            cand.equals("TV-Y7") ||
                            cand.equals("TV-PG") ||
                            cand.equals("not given"))) {
                        Question q = new Question(col, cand, id);
                        id++;
                        if (!(lastCrit.get(pId).contains(cand))) {
                            questions.add(q);
                        }
                    }
                }
        }
        return questions;
    }

    /***
     * find the question w/ probability closest to .5
     */
    public Question findClosestToHalfProb(List<Question> questions, double[] probArray) {
        double bestDiffFromHalf = 1;
        //int indexOfBest=0;
        int indexOfBest = -1;
        for (int i = 0; i < probArray.length; i++) {
            double difference = Math.abs(.5 - probArray[i]);
            if (difference < bestDiffFromHalf) {
                bestDiffFromHalf = difference;
                indexOfBest = i;
            }
        }
        if (indexOfBest == -1) indexOfBest = 0;
        return questions.get(indexOfBest); // // --> send to frontend/server, which incorporates this keyword int
    }

    // inputCrit sent from Player

    /***
     * either movies (list) will be updated (refined) OR if movie.size()==1 ,... then movie will be returned
     * @param ja whether user answered question with yes or no
     * @return
     */
    public List<Movie> refineMovies(Question q, boolean ja, int pId) {
        List<String> crits = lastCrit.get(pId);
        lastCrit.remove(pId);
        if (ja) // wenn yes geantwortet wird, entferne falls answerNo true
            // year Ausnahme
            if (q.getMovieCol().equals("title_year")) {
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        crits.add(q.crit);
                        int yearInt = Integer.parseInt(q.crit);
                        List<Movie> myMovies = multiplayerMovies.get(pId);
                        List<Movie> remover = new ArrayList<>();
                        for (Movie m : multiplayerMovies.get(pId)) {
                            if (!(m.title_year.equals("not_given") || m.title_year.equals("not given"))) {
                                int yearM = Integer.parseInt(m.title_year);
                                if (yearM > yearInt) {
                                    remover.add(m);
                                }
                            }
                        }
                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit year-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit dem title_year");
                }

                // duration Ausnahme
            } else if (q.getMovieCol().equals("duration")) {
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        int durationInt = Integer.parseInt(q.crit);
                        for (int i = durationInt - 5; i < durationInt + 5; i++) {
                            crits.add(String.valueOf(i));
                        }
                        List<Movie> myMovies = multiplayerMovies.get(pId);
                        List<Movie> remover = new ArrayList<>();
                        for (Movie m : multiplayerMovies.get(pId)) {
                            if (!(m.getDuration().equals("1"))) {
                                int durationM = Integer.parseInt(m.getDuration());
                                if (durationM > durationInt) {
                                    remover.add(m);
                                }
                            }
                        }
                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit duration-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit der duration");
                }
                // actor Ausnahme
            } else if (q.getMovieCol().equals("actor_1_name") ||
                    q.getMovieCol().equals("actor_2_name") ||
                    q.getMovieCol().equals("actor_3_name")) {
                List<Movie> myMovies = multiplayerMovies.get(pId);
                ArrayList<Movie> remover = new ArrayList<>();
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        for (Movie m : myMovies) {
                            if (!(q.crit.equals(m.getActor_1_name()) ||
                                    q.crit.equals(m.getActor_2_name()) ||
                                    q.crit.equals(m.getActor_3_name()))) {
                                remover.add(m);
                            }
                        }

                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit actor-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit dem actor");
                }
            } else {
                List<Movie> myMovies = multiplayerMovies.get(pId);
                myMovies.removeIf(q::answersNo);
                multiplayerMovies.remove(pId);
                multiplayerMovies.put(pId, myMovies);
                //multiplayerMovies.get(pId).removeIf(q::answersNo);
            }


        if (!ja) // wenn no geantwortet wird, entferne falls answerYes true
            // year Ausnahme
            if (q.getMovieCol().equals("title_year")) {
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        crits.add(q.crit);
                        int yearInt = Integer.parseInt(q.crit);
                        List<Movie> myMovies = multiplayerMovies.get(pId);
                        List<Movie> remover = new ArrayList<>();
                        for (Movie m : multiplayerMovies.get(pId)) {
                            if (!(m.title_year.equals("not_given") || m.title_year.equals("not given"))) {
                                int yearM = Integer.parseInt(m.title_year);
                                if (yearM < yearInt) {
                                    remover.add(m);
                                }
                            }
                        }
                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit year-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit dem title_year");
                }

                // duration Ausnahme
            } else if (q.getMovieCol().equals("duration")) {
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        int durationInt = Integer.parseInt(q.crit);
                        for (int i = durationInt - 5; i < durationInt + 5; i++) {
                            crits.add(String.valueOf(i));
                        }
                        List<Movie> myMovies = multiplayerMovies.get(pId);
                        List<Movie> remover = new ArrayList<>();
                        for (Movie m : multiplayerMovies.get(pId)) {
                            if (!(m.getDuration().equals("1"))) {
                                int durationM = Integer.parseInt(m.getDuration());
                                if (durationM < durationInt) {
                                    remover.add(m);
                                }
                            }
                        }
                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit duration-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit der duration");
                }
                //actor Ausnahme
            } else if (q.getMovieCol().equals("actor_1_name") ||
                    q.getMovieCol().equals("actor_2_name") ||
                    q.getMovieCol().equals("actor_3_name")) {
                List<Movie> myMovies = multiplayerMovies.get(pId);
                ArrayList<Movie> remover = new ArrayList<>();
                try {
                    if (!(q.crit.equals("not_given") || q.crit.equals("not given"))) {
                        for (Movie m : myMovies) {
                            if ((q.crit.equals(m.getActor_1_name()) ||
                                    q.crit.equals(m.getActor_2_name()) ||
                                    q.crit.equals(m.getActor_3_name()))) {
                                remover.add(m);
                            }
                        }

                        try {
                            myMovies.removeAll(remover);
                            multiplayerMovies.remove(pId);
                            multiplayerMovies.put(pId, myMovies);
                        } catch (Exception e) {
                            System.out.println("Problem mit actor-Remover");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Problem mit dem actor");
                }
            }else if (q.getMovieCol().equals("genres")) {
                for (Movie m : multiplayerMovies.get(pId)) {
                    if (!(m.getGenres().equals("not_given") || m.getGenres().equals("not given"))) {
                        if (m.getGenres().contains(q.crit)) {
                            crits.add(m.getGenres());
                        }
                    }
                }
            } else {
                List<Movie> myMovies = multiplayerMovies.get(pId);
                myMovies.removeIf(q::answersYes);
                multiplayerMovies.remove(pId);
                multiplayerMovies.put(pId, myMovies);
                //multiplayerMovies.get(pId).removeIf(q::answersYes);
            }
        lastCrit.put(pId, crits);
        return multiplayerMovies.get(pId);
    }
}

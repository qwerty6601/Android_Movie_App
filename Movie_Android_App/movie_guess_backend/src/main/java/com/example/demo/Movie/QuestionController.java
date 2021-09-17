//
//package com.example.demo.Movie;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//import com.example.demo.*;
//
//
//@RestController
//public class QuestionController{
//    public Question1 error1 = new Question1(404, 0.0, "Error");
//
//
//    ArrayList<Question1> allQuestions = new ArrayList<>();
//    ArrayList<Answer> allAnswers = new ArrayList<>();
//
//    public int year = 2000;
//
//    MovieController c = new MovieController(new Main());
//    List<Movie> m = (List<Movie>) c.getMovie(); // why not call from MovieService?
//
//    public ArrayList<Movie> updatedList = new ArrayList<>();
//    HashMap<Movie, Integer> movieCounter = new HashMap<Movie, Integer>();
//    HashMap<Movie, Integer> movieCounterM = new HashMap<Movie, Integer>();
//    HashMap<Movie, Integer> movieCounterF = new HashMap<Movie, Integer>();
//    HashMap<Movie, Integer> movieCounterO = new HashMap<Movie, Integer>();
//    HashMap<Movie, Integer> movieCounterN = new HashMap<Movie, Integer>();
//    PlayerController pc = new PlayerController();
//
//
//    public Map<Movie, Integer> setCounter() {
//        for (Movie x : m) {
//            movieCounter.put(x, 0);
//            movieCounterM.put(x, 0);
//            movieCounterF.put(x, 0);
//            movieCounterO.put(x, 0);
//            movieCounterN.put(x, 0);
//        }
//        return movieCounter;
//    }
//
//    public String listOutput(List<Movie> m) {
//        //if (m.size() == 0) return "Sorry, there is no Movie in the List";
//
//        try {
//            String output = "[";
//            for (Movie d : m) {
//                output = output + d.getMovie_title() + ", ";
//            }
//            output = output + "]";
//            System.out.println(output);
//            return output;
//        } catch (NullPointerException e) {
//            System.out.println("UpdatedList is null");
//            return "null";
//        }
//    }
//
//    public void updateCounter(Movie i, int value) {
//        movieCounter.put(i, value);
//        if (pc.playerNow.sex == 0) {
//            movieCounterM.put(i, value);
//        } else if(pc.playerNow.sex == 1) {
//            movieCounterF.put(i, value);
//        } else if(pc.playerNow.sex == 2) {
//            movieCounterO.put(i, value);
//        } else if(pc.playerNow.sex == 3) {
//            movieCounterN.put(i, value);
//        }
//    }
//
//
//    public void updateList(int id, String a) {
//        if (movieCounter.size() == 0) {
//            setCounter();
//        }
//
//        ArrayList<Movie> remover = new ArrayList<>();
//        remover.clear();
//
//        // FSK
//        if (id == 2) {
//            if (a.equals("true")) {
//                for (Movie x: m) {
//                    if(!x.getContent_rating().contains("PG-13") || !x.getContent_rating().contains("R")) {
//                        updatedList.add(x);
//                        int value = movieCounter.get(x) + 1;
//                        updateCounter(x, value);
//                    }
//                }
//            } else {
//                for (Movie x : m) {
//                    if(x.getContent_rating().contains("PG-13") || x.getContent_rating().contains("R")) {
//                        updatedList.add(x);
//                        int value = movieCounter.get(x) + 1;
//                        updateCounter(x, value);
//                    }
//                }
//            }
//        //GENRES
//        } else if (id == 3) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList)  {
//                    if (i.getGenres().contains("Animation")
//                            || i.getGenres().contains("Comedy")
//                            || i.getGenres().contains("Familiy")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getGenres().contains("Animation")
//                        || i.getGenres().contains("Comedy")
//                        || i.getGenres().contains("Familiy"));
//            }
//        // GENRES2
//        } else if (id == 4) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getGenres().contains("Action")
//                            || i.getGenres().contains("Horror")
//                            || i.getGenres().contains("War")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getGenres().contains("Action")
//                        || i.getGenres().contains("Horror")
//                        || i.getGenres().contains("War"));
//            }
//        // GENRES3
//        } else if (id == 5) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getGenres().contains("Music")
//                            || i.getGenres().contains("Documentary")
//                            || i.getGenres().contains("History")
//                            || i.getGenres().contains("Romance")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getGenres().contains("Music")
//                        || i.getGenres().contains("Documentary")
//                        || i.getGenres().contains("History")
//                        || i.getGenres().contains("Romance"));
//            }
//        // GENRES4
//        } else if (id == 6) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getGenres().contains("Thriller")
//                            || i.getGenres().contains("Crime")
//                            || i.getGenres().contains("Drama")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getGenres().contains("Thriller")
//                        || i.getGenres().contains("Crime")
//                        || i.getGenres().contains("Drama"));
//            }
//        /* FUNKTIONIERT AKTUELL NICHT
//        // Year
//        } else if (id == 7 || id == 8) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                   int movieYear = Integer.parseInt(i.getTitle_year());
//                    int movieYear = 2005;
//                    if (movieYear <= year) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                for (Movie i : updatedList) {
//                    int movieYear = Integer.parseInt(i.getTitle_year());
//                    if (movieYear > year) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            }
//
//        // DURATION
//        } else if (id == 9) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    int movieDuration = Integer.parseInt(i.getDuration());
//                    if (movieDuration <= year) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.clear();
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                for (Movie i : updatedList) {
//                    int movieDuration = Integer.parseInt(i.getDuration());
//                    if (movieDuration > year) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            }
//
//         */
//        // ENGLISH?
//        } else if (id == 10) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getM_language().contains("English")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getM_language().contains("English"));
//            }
//        // US?
//        } else if (id == 11) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getCountry().contains("USA")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getCountry().contains("USA"));
//            }
//        // GERMAN, FRENCH, SPANISH
//        } else if (id == 12) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getM_language().contains("German")
//                        || i.getM_language().contains("French")
//                        || i.getM_language().contains("Spanish")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getM_language().contains("German")
//                        || i.getM_language().contains("French")
//                        || i.getM_language().contains("Spanish"));
//            }
//        // STUDIO1
//        //} else if (id == 13) {
//
//
//        // GB?
//        } else if (id == 14) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getCountry().contains("UK")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getCountry().contains("UK"));
//            }
//
//        // STUDO2
//        //} else if (id == 15) {
//
//        // ACTORS1
//        } else if (id == 16) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getActor_1_name().contains("Tom Hanks")
//                        || i.getActor_2_name().contains("Tom Hanks")
//                        || i.getActor_3_name().contains("Tom Hanks")
//
//                        || i.getActor_1_name().contains("Tom Cruise")
//                        || i.getActor_2_name().contains("Tom Cruise")
//                        || i.getActor_3_name().contains("Tom Cruise")) {
//
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getActor_1_name().contains("Tom Hanks")
//                        || i.getActor_2_name().contains("Tom Hanks")
//                        || i.getActor_3_name().contains("Tom Hanks")
//
//                        || i.getActor_1_name().contains("Tom Cruise")
//                        || i.getActor_2_name().contains("Tom Cruise")
//                        || i.getActor_3_name().contains("Tom Cruise"));
//            }
//        // ACTORS2
//        } else if (id == 17) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getActor_1_name().contains("Bruce Willis")
//                            || i.getActor_2_name().contains("Bruce Willis")
//                            || i.getActor_3_name().contains("Bruce Willis")
//
//                            || i.getActor_1_name().contains("Johnny Depp")
//                            || i.getActor_2_name().contains("Johnny Depp")
//                            || i.getActor_3_name().contains("Johnny Depp")) {
//
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getActor_1_name().contains("Bruce Willis")
//                        || i.getActor_2_name().contains("Bruce Willis")
//                        || i.getActor_3_name().contains("Bruce Willis")
//
//                        || i.getActor_1_name().contains("Johnny Depp")
//                        || i.getActor_2_name().contains("Johnny Depp")
//                        || i.getActor_3_name().contains("Johnny Depp"));
//            }
//        // ACTORS3
//        } else if (id == 18) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getActor_1_name().contains("Emma Stone")
//                            || i.getActor_2_name().contains("Emma Stone")
//                            || i.getActor_3_name().contains("Emma Stone")
//
//                            || i.getActor_1_name().contains("Jennifer Lawrence")
//                            || i.getActor_2_name().contains("Jennifer Lawrence")
//                            || i.getActor_3_name().contains("Jennifer Lawrence")) {
//
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getActor_1_name().contains("Emma Stone")
//                        || i.getActor_2_name().contains("Emma Stone")
//                        || i.getActor_3_name().contains("Emma Stone")
//
//                        || i.getActor_1_name().contains("Jennifer Lawrence")
//                        || i.getActor_2_name().contains("Jennifer Lawrence")
//                        || i.getActor_3_name().contains("Jennifer Lawrence"));
//            }
//        // DIRECTOR
//        } else if (id == 19) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getDirector_name().contains("Michael Bay") || i.getDirector_name().contains("Steven Spielberg")) {
//
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getDirector_name().contains("Michael Bay") || i.getDirector_name().contains("Steven Spielberg"));
//            }
//
//        // KEYWORDS
//        } else if (id == 20) {
//            if (a.equals("true")) {
//                for (Movie i : updatedList) {
//                    if (i.getPlot_keywords().contains("book")
//                            || i.getPlot_keywords().contains("novel")
//                            || i.getPlot_keywords().contains("cartoon")
//                            || i.getPlot_keywords().contains("true")
//                            || i.getPlot_keywords().contains("manga")
//                            || i.getPlot_keywords().contains("bible")
//                            || i.getPlot_keywords().contains("video game")) {
//                        int value = movieCounter.get(i) + 1;
//                        updateCounter(i, value);
//                    } else {
//                        remover.add(i);
//                    }
//                }
//                updatedList.removeAll(remover);
//                remover.clear();
//            } else {
//                updatedList.removeIf(i -> i.getPlot_keywords().contains("book")
//                        || i.getPlot_keywords().contains("novel")
//                        || i.getPlot_keywords().contains("cartoon")
//                        || i.getPlot_keywords().contains("true")
//                        || i.getPlot_keywords().contains("manga")
//                        || i.getPlot_keywords().contains("bible")
//                        || i.getPlot_keywords().contains("video game"));
//            }
//
//        // FINAL
//        } else if (id == 21) {
//
//        }
//    }
//
//
//
//
//    // Erste Frage kann hart gecoded sein
//    public Question1 fsk = new Question1(
//            //UUID.randomUUID().toString(),
//            2,
//            100.0,
//            "Is your movie suitable for people under 13 years old?"
//    );
//
//    /*
//        Fantasy, Sci-Fi, Musical, Western, Sport, Mystery werden bisher noch nicht verwendet
//        Selten: Short, Film-Noir
//     */
//    // Zweite Frage
//    public Question1 fskTrue = new Question1(
//            //UUID.randomUUID().toString(),
//            3,
//            100.0,
//            // "Does your movie fit in one or more of these genres?" + calculateList(allAnswers)
//            "Does your movie fit in one or more of these genres? -> animation, comedy, family"
//    );
//    public Question1 fskFalse = new Question1(
//        //UUID.randomUUID().toString(),
//        4,
//        100.0,
//        //"Does your movie fit in one or more of these genres?" + calculateList(allAnswers)
//            "Does your movie fit in one or more of these genres? -> action, horror, war"
//    );
//
//
//    // Dritte Frage
//    public Question1 genreTrue = new Question1(
//        //UUID.randomUUID().toString(),
//        5,
//        100.0,
//        //"Does one or more of the following genres describe your movie?" + calculateList(allAnswers)
//            "Does your movie fit in one or more of these genres? -> music, documentary, history, romance"
//    );
//    public Question1 genreFalse = new Question1(
//            //UUID.randomUUID().toString(),
//            6,
//            100.0,
//            //"Does one or more of the following genres describe your movie?" + calculateList(allAnswers)
//            "Does your movie fit in one or more of these genres? -> thriller, crime, drama"
//    );
//
//
//    // Vierte Frage
//    public Question1 yearQuestionOne = new Question1(
//            //UUID.randomUUID().toString(),
//            7,
//            100.0,
//            "Was your movie published before/in " + year + " ?"
//    );
//
//    // Fünfte Frage
//    public Question1 yearQuestionTwo = new Question1(
//            //UUID.randomUUID().toString(),
//            8,
//            100.0,
//            "Was your movie published before " + year + " ?"
//    );
//
//    // Sechste Frage
//    public Question1 runtimeQuestion = new Question1(
//            //UUID.randomUUID().toString(),
//            9,
//            100.0,
//            //"Is the duration of your movie longer than " + calculateRuntime(allAnswers) + " minutes?"
//            "Is the duration of your movie longer than 100 minutes?"
//
//    );
//
//    // Siebte Frage
//    public Question1 languageQuestion = new Question1(
//            //UUID.randomUUID().toString(),
//            10,
//            100.0,
//            "Is English the original language of your movie?"
//    );
//
//    // Achte Frage
//    public Question1 englishTrue = new Question1(
//            //UUID.randomUUID().toString(),
//            11,
//            100.0,
//            "Was your movie produced in the United States?"
//    );
//    public Question1 englishFalse = new Question1(
//            //UUID.randomUUID().toString(),
//            12,
//            100.0,
//            //"Is either one of these languages the original language of your movie? " + calculateList(allAnswers)
//            "Is German, French or Spanish the original language of your movie?"
//    );
//
//    // Frage wird nicht gestellt -> Studios nicht abrufbar
//    public Question1 studioUsTrue = new Question1(
//            //UUID.randomUUID().toString(),
//            13,
//            0,
//            "Was your movie produced by 'Warner Bros' or '20th Century' Fox?"
//    );
//
//    // Neunte Frage
//    public Question1 studioUsFalse = new Question1(
//            //UUID.randomUUID().toString(),
//            14,
//            100.0,
//            "Was your movie produced in UK?"
//    );
//
//    // Frage wird nicht gestellt -> Studios nicht abrufbar
//    public Question1 studioGbTrue = new Question1(
//            //UUID.randomUUID().toString(),
//            15,
//            100.0,
//            //"Was your movie produced by either one of these studios? " + calculateList(allAnswers)
//            "Was your movie produced by '3 Mills' or 'Warner Bros'? "
//    );
//
//    // Zehnte Frage
//    public Question1 actorQuestion = new Question1(
//            //UUID.randomUUID().toString(),
//            16,
//            100.0,
//            //"Does one or more of these actors play in your movie? " + calculateList(allAnswers) // 2 Actors
//            "Does one or more of these actors play in your movie? -> Tom Hanks, Tom Cruise"
//    );
//
//    // Elfte Frage
//    public Question1 actorQuestion2 = new Question1(
//            //UUID.randomUUID().toString(),
//            17,
//            100.0,
//            //"Does one or more of these actors play in your movie? " + calculateList(allAnswers) // 2 Actors
//            "Does one or more of these actors play in your movie? -> Bruce Willis, Johnny Depp"
//    );
//
//    // Zwölfte Frage
//    public Question1 actorQuestion3 = new Question1(
//            //UUID.randomUUID().toString(),
//            18,
//            100.0,
//            //"Does one or more of these actors play in your movie? " + actor.getName() // 2 Actors
//            "Does one or more of these actors play in your movie? -> Emma Stone, Jennifer Lawrence"
//    );
//
//    // Dreitzehnte Frage
//    public Question1 directorQuestion = new Question1(
//            //UUID.randomUUID().toString(),
//            19,
//            100.0,
//            //"Does "  + calculateList(allAnswers) + " directed your movie?"
//            "Does one or more of these director play in your movie? -> Steven Spielberg, Michael Bay"
//    );
//
//    // Vierzehnte Frage
//    public Question1 bookQuestion = new Question1(
//            //UUID.randomUUID().toString(),
//            20,
//            100.0,
//            "Is your movie based on a book / true story / video game?"
//    );
//
//
//    // Fünfzehnte Frage
//    public Question1 finish = new Question1(
//            //UUID.randomUUID().toString(),
//            21,
//            100.0,
//            "Is one of the following movies your movie? " + listOutput(updatedList)
//    );
//
//    public void adder() {
//        allQuestions.clear();
//        allQuestions.add(error1);
//        allQuestions.add(error1);
//        allQuestions.add(fsk);
//        allQuestions.add(fskTrue);
//        allQuestions.add(fskFalse);
//        allQuestions.add(genreTrue);
//        allQuestions.add(genreFalse);
//        allQuestions.add(yearQuestionOne);
//        allQuestions.add(yearQuestionTwo);
//        allQuestions.add(runtimeQuestion);
//        allQuestions.add(languageQuestion);
//        allQuestions.add(englishTrue);
//        allQuestions.add(englishFalse);
//        allQuestions.add(studioUsTrue);
//        allQuestions.add(studioUsFalse);
//        allQuestions.add(studioGbTrue);
//        allQuestions.add(actorQuestion);
//        allQuestions.add(actorQuestion2);
//        allQuestions.add(actorQuestion3);
//        allQuestions.add(directorQuestion);
//        allQuestions.add(bookQuestion);
//        allQuestions.add(finish);
//    }
//
//    // Not Found
//    public Question1 notFound = new Question1(100,0,"Sorry, we couldn't find your movie");
//
//    Question1 questionToAsk = fsk;
//    int showId = -1;
//
//    @PostMapping("/answer")
//    public Question1 getAnswer(String id, String a, int key) {
//        //Schlüssel für Eingriff des Objekts
//        if(key == 31415926) {
//
//            int id2 = Integer.parseInt(id);
//            adder();
//            updateList(id2, a);
//
//            // Zurücksetzen mit Homebutton
//            if (id2 == 0) {
//                updatedList.clear();
//                questionToAsk = fsk;
//                return questionToAsk;
//            }
//
//            // Falls kein Film mehr in der Liste ist, wird der Algorithmus abgebrochen
//            if (updatedList.size() == 0 && id2 > 2) questionToAsk = notFound;
//            if (updatedList.size() == 1 && id2 > 2) questionToAsk = new Question1(200,100,"Is your movie: " + listOutput(updatedList));
//
//            if (a.equals("true") && id2 == 200) questionToAsk = new Question1(300,100,"Hurra!");
//
//            if (updatedList.size() <= 3 && id2 > 2) {
//               if (showId < updatedList.size()) {
//                   showId++;
//                   questionToAsk = new Question1(300,100,"Is your movie: " + updatedList.get(showId).getMovie_title());
//               } else {
//                  questionToAsk = notFound;
//               }
//            } else
//
//            // Algorithmus fuer die ersten Spiele
//            if (a.equals("true")) {
//                Answer answerTheQuestion = new Answer(id2, "true", "0");
//                allAnswers.add(answerTheQuestion);
//                if (id2 == 7) year = 1995;
//                // Nächste Frage anhand ihrer id auswählen
//                if (id2 == 3 || id2 == 5 || id2 == 14) {
//                    id2++;
//                } else if (id2 == 11) {
//                    id2 = id2 + 4;
//                } else if (id2 == 12) {
//                    id2 = id2 + 3;
//                }
//                // Frage setzen
//                questionToAsk = allQuestions.get(id2 + 1);
//            } else if (a.equals("false")) {
//                Answer answerTheQuestion = new Answer(id2, "false", "0");
//                allAnswers.add(answerTheQuestion);
//                if (id2 == 7) year = 1995;
//                // Nächste Frage anhand ihrer id auswählen
//                if (id2 == 2 || id2 == 4 || id2 == 5 || id2 == 10 || id2 == 14) {
//                    id2++;
//                } else if (id2 == 3) {
//                    id2 = id2 + 2;
//                } else if (id2 == 11) {
//                    id2 = id2 + 4;
//                } else if (id2 == 12) {
//                    id2 = id2 + 3;
//                }
//                // Frage setzen
//                questionToAsk = allQuestions.get(id2 + 1);
//            } else {
//                questionToAsk = error1;
//            }
//            return questionToAsk;
//        }
//        else{
//            return null;
//        }
//    }
//
//    @RequestMapping("/question" )
//    public Question1 sendQuestion() {
//        //System.out.println(listOutput(updatedList));
//        System.out.println(updatedList.size());
//        return questionToAsk;
//    }
//}

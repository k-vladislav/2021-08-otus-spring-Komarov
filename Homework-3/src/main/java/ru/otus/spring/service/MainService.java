package ru.otus.spring.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    private final User user;
    private final QuizService quizService;
    private final AppConfig.Settings appConfigSettings;

    public MainService(User user, QuizServiceCSV quizService, AppConfig appConfig) {
        this.user = user;
        this.quizService = quizService;
        this.appConfigSettings = appConfig.getSettings();
    }

    public void start() {
        Optional<List<CSVRecord>> quizRawData = quizService.getQuizRawData();
        if (quizRawData.isPresent()) {
            List<CSVRecord> csvRecords = quizRawData.get();
            Quiz quiz = QuizBuilderCSV.getBuilder(csvRecords).buildQuiz();
            int requiredscore = appConfigSettings.getRequiredscore();
            QuizPlayer quizPlayer = QuizPlayerCSV.create(user, quiz,requiredscore);
            quizPlayer.play();
        } else {
            System.out.println("No CSV records!");
        }
    }
}

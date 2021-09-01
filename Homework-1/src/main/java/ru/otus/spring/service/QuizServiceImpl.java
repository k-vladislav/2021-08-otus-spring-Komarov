package ru.otus.spring.service;

import org.apache.commons.csv.*;
import ru.otus.spring.dao.QuizDao;
import ru.otus.spring.domain.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;

    public QuizServiceImpl(QuizDao dao) {
        this.dao = dao;
    }

    @Override
    public Quiz getQuiz() throws IOException {
        File quizFile = dao.getQuizFile();
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        String question;
        CSVParser csvRecords = CSVParser.parse(new FileReader(quizFile), CSVFormat.EXCEL.builder().setHeader().build());
        for (CSVRecord csvRecord : csvRecords) {
            answers.clear();
            question=csvRecord.get("Question");
            String[] questionAnswers = csvRecord.get("Answers").split("/");
            for (String questionAnswer : questionAnswers) {
                answers.add(new Answer(questionAnswer));
            }
            questions.add(new Question(question,answers));
        }

        return new Quiz(questions);
    }
}

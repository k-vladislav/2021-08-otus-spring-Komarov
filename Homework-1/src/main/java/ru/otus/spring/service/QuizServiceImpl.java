package ru.otus.spring.service;

import org.apache.commons.csv.*;
import ru.otus.spring.dao.QuizDao;
import ru.otus.spring.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;

    public QuizServiceImpl(QuizDao dao) {
        this.dao = dao;
    }

    @Deprecated
    public Quiz getQuizOld() throws IOException {
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

    @Override
    public Quiz getQuiz() throws IOException {
        Quiz quiz = new Quiz();
        File quizFile = dao.getQuizFile();
        CSVParser csvRecords = CSVParser.parse(new FileReader(quizFile), CSVFormat.EXCEL.builder().setHeader().build());
        for (CSVRecord csvRecord : csvRecords) {
            Question question = new Question(csvRecord.get("Question"));
            String[] csvAnswers = csvRecord.get("Answers").split("/");
            for (String csvAnswer : csvAnswers) {
                question.addAnswer(new Answer(csvAnswer));
            }
            quiz.addQuestion(question);
        }
        return quiz;
    }

    @Override
    public Quiz getQuizAsInputStream() throws IOException {
        Quiz quiz = new Quiz();
        InputStream quizInputStream = dao.getQuizInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(quizInputStream));
        CSVParser csvRecords = CSVParser.parse(bufferedReader, CSVFormat.EXCEL.builder().setHeader().build());
        for (CSVRecord csvRecord : csvRecords) {Question question = new Question(csvRecord.get("Question"));
            String[] csvAnswers = csvRecord.get("Answers").split("/");
            for (String csvAnswer : csvAnswers) {
                question.addAnswer(new Answer(csvAnswer));
            }
            quiz.addQuestion(question);
        }
        return quiz;
    }
}

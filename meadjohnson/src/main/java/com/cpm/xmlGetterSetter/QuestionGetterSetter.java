package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 25-01-2016.
 */
public class QuestionGetterSetter {

    String table_question_today;

    ArrayList<String> question_cd = new ArrayList<String>();
    ArrayList<String> question = new ArrayList<String>();
    ArrayList<String> answer_cd = new ArrayList<String>();
    ArrayList<String> answer = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    ArrayList<String> right_answer = new ArrayList<String>();

    public String getTable_question_today() {
        return table_question_today;
    }

    public void setTable_question_today(String table_question_today) {
        this.table_question_today = table_question_today;
    }

    public ArrayList<String> getQuestion_cd() {
        return question_cd;
    }

    public void setQuestion_cd(String question_cd) {
        this.question_cd.add(question_cd);
    }

    public ArrayList<String> getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.add(question);
    }

    public ArrayList<String> getAnswer_cd() {
        return answer_cd;
    }

    public void setAnswer_cd(String answer_cd) {
        this.answer_cd.add(answer_cd);
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.add(answer);
    }

    public void setQuestion_cd(ArrayList<String> question_cd) {
        this.question_cd = question_cd;
    }

    public void setQuestion(ArrayList<String> question) {
        this.question = question;
    }

    public void setAnswer_cd(ArrayList<String> answer_cd) {
        this.answer_cd = answer_cd;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public ArrayList<String> getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status.add(status);
    }

    public ArrayList<String> getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer.add(right_answer);
    }
}

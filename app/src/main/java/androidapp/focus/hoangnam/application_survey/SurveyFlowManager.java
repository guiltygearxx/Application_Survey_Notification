package androidapp.focus.hoangnam.application_survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidapp.focus.hoangnam.application_survey.activity.BaseQuestionActivity;
import androidapp.focus.hoangnam.application_survey.activity.CheckboxActivity;
import androidapp.focus.hoangnam.application_survey.activity.InputTextActivity;
import androidapp.focus.hoangnam.application_survey.activity.MainActivity;
import androidapp.focus.hoangnam.application_survey.activity.ResultActivity;
import androidapp.focus.hoangnam.application_survey.model.Answer;
import androidapp.focus.hoangnam.application_survey.model.InformationAnswer;
import androidapp.focus.hoangnam.application_survey.model.MultipleChoiceAnswer;
import androidapp.focus.hoangnam.application_survey.model.Question;
import androidapp.focus.hoangnam.application_survey.model.SingleChoiceAnswer;

/**
 * Created by huy.nquoc on 12/23/2017.
 * class quan li luong cua survey;
 */

public class SurveyFlowManager implements Serializable {

    public static SurveyFlowManager instance;

    private List<Question> questions;

    private List<Answer> answers;

    private Integer questionIndex;

    public List<Answer> getAnswers() {

        return answers;
    }

    private void goToQuestionActitivy(AppCompatActivity activity, Question question) {

        Intent intent = new Intent(activity, this.getQuestionActivityClass(question));

        activity.startActivity(intent);
    }

    /**
     * lay class activity tuong ung voi question;
     *
     * @param question
     * @return
     */
    public Class getQuestionActivityClass(Question question) {

        switch (question.type) {

            case Question.TYPE_INFORMATION:
                return InputTextActivity.class;

            case Question.TYPE_MULTIPLE:
                return CheckboxActivity.class;

            case Question.TYPE_SINGLE:
                return MainActivity.class;

            default:
                throw new RuntimeException("question.unknownType: " + question.type);
        }
    }

    /**
     * hien thi activity tuong ung voi quest tiep theo
     *
     * @param activity
     * @param answer   answer cua buoc hien tai
     */
    public void goToNextQuestion(BaseQuestionActivity activity, Answer answer) {

        if (questionIndex == answers.size() - 1) {

            this.answers.set(questionIndex, answer);
        } else {

            this.answers.add(answer);
        }

        if (this.isLastQuestion()) {

            viewSurveyResult(activity);
        } else {

            Question question = this.questions.get(++this.questionIndex);

            goToQuestionActitivy(activity, question);
        }
    }

    public void goToFirstQuestion(AppCompatActivity activity) {

        Question question = this.questions.get(this.questionIndex = 0);

        Intent intent = new Intent(activity, this.getQuestionActivityClass(question));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        activity.startActivity(intent);
    }

    public void init(List<Question> questions) {

        this.questions = questions;

        this.answers = new ArrayList<>(this.questions.size());
    }

    public Answer initAnswer(Question question) {

        switch (question.type) {

            case Question.TYPE_INFORMATION:
                return new InformationAnswer(question);

            case Question.TYPE_MULTIPLE:
                return new MultipleChoiceAnswer(question);

            case Question.TYPE_SINGLE:
                return new SingleChoiceAnswer(question);

            default:
                throw new RuntimeException("question.unknownType: " + question.type);
        }
    }

    public Question getCurrentQuestion() {

        return questionIndex == null || questions == null || questions.isEmpty() ? null : questions.get(this.questionIndex);
    }

    public void viewSurveyResult(BaseQuestionActivity activity) {

        Intent intent = new Intent(activity, ResultActivity.class);

        activity.startActivity(intent);
    }

    public boolean isLastQuestion() {

        return this.questionIndex == this.questions.size() - 1;
    }

    public void goBackPrevQuestion(Answer answer) {

        this.answers.remove(answer);

        this.questionIndex--;
    }
}

package androidapp.focus.hoangnam.application_survey.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import androidapp.focus.hoangnam.application_survey.R;
import androidapp.focus.hoangnam.application_survey.SurveyFlowManager;
import androidapp.focus.hoangnam.application_survey.model.ApplicationContext;
import androidapp.focus.hoangnam.application_survey.model.Question;

public class StartPointActivity extends AppCompatActivity {

    private SurveyFlowManager surveyFlowManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_point);
    }

    @Override
    protected void onStart() {

        super.onStart();

        this.init();
    }

    @Override
    protected void onResume() {

        super.onResume();

        this.goToFirstQuestion();
    }

    private void init() {

        SurveyFlowManager.instance = surveyFlowManage = new SurveyFlowManager();

        ApplicationContext.instance = new ApplicationContext();

        List<Question> questions = loadQuestions();

        surveyFlowManage.init(questions);
    }

    private void goToFirstQuestion() {

        surveyFlowManage.goToFirstQuestion(this);
    }

    private List<Question> loadQuestions() {

        return Arrays.asList(
                new Question(Question.TYPE_SINGLE),
                new Question(Question.TYPE_MULTIPLE),
                new Question(Question.TYPE_INFORMATION)
        );
    }
}

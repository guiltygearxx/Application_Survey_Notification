package androidapp.focus.hoangnam.application_survey.activity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidapp.focus.hoangnam.application_survey.R;
import androidapp.focus.hoangnam.application_survey.SurveyFlowManager;
import androidapp.focus.hoangnam.application_survey.model.Answer;
import androidapp.focus.hoangnam.application_survey.model.InformationAnswer;
import androidapp.focus.hoangnam.application_survey.model.MultipleChoiceAnswer;
import androidapp.focus.hoangnam.application_survey.model.SingleChoiceAnswer;

/**
 * Created by HoangNam on 12/23/2017.
 */

public class ResultActivity extends AppCompatActivity {

    private TextView txtResult;

    private SurveyFlowManager surveyFlowManager;

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        surveyFlowManager = SurveyFlowManager.instance;

        txtResult = findViewById(R.id.txt_view_result);

        this.buildResult();
    }

    @Override
    protected void onStart() {

        super.onStart();

        this.buildResult();

        txtResult.setText(this.result);

        this.notifySurveyResult();
    }

    private void notifySurveyResult() {

        NotificationCompat.Builder builder =
                new android.support.v7.app.NotificationCompat.Builder(this);

        builder.setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("Congratulation, you just have finish our survey. Thank u!")
                .setSmallIcon(R.drawable.ic_launcher_background);

        int mNotificationId = 001;

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId, builder.build());
    }

    /**
     * build ket qua hien thi
     */
    private void buildResult() {

        List<String> results = new ArrayList<>();

        List<Answer> answers = surveyFlowManager.getAnswers();

        int answersSize = answers.size();

        for (int index = 0; index < answersSize; index++) {

            String result = this.getResult(answers.get(index));

            results.add("Câu " + (index + 1) + ": " + (result == null ? "<Không trả lời>" : result));
        }

        this.result = TextUtils.join("\n", results.toArray());
    }

    /**
     * build message du vao du lieu answer;
     *
     * @param answer
     * @return
     */
    private String getResult(Answer answer) {

        if (answer.getData() == null) return null;

        if (answer instanceof SingleChoiceAnswer) {

            return ((SingleChoiceAnswer) answer).getData();

        } else if (answer instanceof MultipleChoiceAnswer) {

            List<String> data = ((MultipleChoiceAnswer) answer).getData();

            if (data.isEmpty()) return null;

            return TextUtils.join(",", data.toArray());

        } else if (answer instanceof InformationAnswer) {

            Map<String, String> data = ((InformationAnswer) answer).getData();

            Set<String> keys = data.keySet();

            if (keys.isEmpty()) return null;

            List<String> value = new ArrayList<>();

            for (String key : keys) {

                value.add(TextUtils.join(": ", new String[]{key, data.get(key)}));
            }

            return TextUtils.join(". ", value.toArray());
        }

        return null;
    }
}
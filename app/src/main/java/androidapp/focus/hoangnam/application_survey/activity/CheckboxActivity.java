package androidapp.focus.hoangnam.application_survey.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidapp.focus.hoangnam.application_survey.R;

/**
 * Created by HoangNam on 12/23/2017.
 */

public class CheckboxActivity extends BaseQuestionActivity {

    List<CheckBox> checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkbox_survey);

        this.checkBoxes = Arrays.asList(
                (CheckBox) findViewById(R.id.cb_visa),
                (CheckBox) findViewById(R.id.cb_club),
                (CheckBox) findViewById(R.id.cb_discover),
                (CheckBox) findViewById(R.id.cb_express),
                (CheckBox) findViewById(R.id.cb_master)
        );
    }

    @Override
    public void next(View view) {

        super.next(view);
    }

    @Override
    protected Object getAnswerData() {

        List<String> answers = new ArrayList<>();

        for (CheckBox checkBox : checkBoxes) {

            if (checkBox.isChecked()) answers.add(checkBox.getText().toString());
        }

        return answers;
    }

}
package androidapp.focus.hoangnam.application_survey.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioButton;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidapp.focus.hoangnam.application_survey.R;


public class MainActivity extends BaseQuestionActivity {

    List<RadioButton> radioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        radioButtons = Arrays.asList(
                (RadioButton) findViewById(R.id.radioButton),
                (RadioButton) findViewById(R.id.radioButton2),
                (RadioButton) findViewById(R.id.radioButton3),
                (RadioButton) findViewById(R.id.radioButton4),
                (RadioButton) findViewById(R.id.radioButton5)
        );
    }

    @Override
    protected Object getAnswerData() {

        for (RadioButton radioButton : radioButtons) {

            if (radioButton.isChecked()) return radioButton.getText();
        }

        return null;
    }

}

package androidapp.focus.hoangnam.application_survey.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import androidapp.focus.hoangnam.application_survey.R;

/**
 * Created by HoangNam on 12/23/2017.
 */

public class InputTextActivity extends BaseQuestionActivity {

    Map<String, EditText> editTextMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inputtext_survey);

        editTextMap = new HashMap<>();

        editTextMap.put("Name", (EditText) findViewById(R.id.ed_name));
        editTextMap.put("Phone", (EditText) findViewById(R.id.ed_phone));
        editTextMap.put("Email", (EditText) findViewById(R.id.ed_email));
    }

    @Override
    protected Object getAnswerData() {

        Map<String, String> data = new HashMap<>();

        for (String key : editTextMap.keySet()) {

            String enteredText = editTextMap.get(key).getText().toString();

            if (enteredText != null && !enteredText.isEmpty()) data.put(key, enteredText);
        }

        return data;
    }
}
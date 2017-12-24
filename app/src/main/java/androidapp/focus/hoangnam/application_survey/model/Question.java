package androidapp.focus.hoangnam.application_survey.model;

/**
 * Created by huy.nquoc on 12/23/2017.
 */

public class Question {

    public static final String TYPE_MULTIPLE = "multiple";
    public static final String TYPE_SINGLE = "single";
    public static final String TYPE_INFORMATION = "information";

    public String type; //multiple/single/information

    public Question(String type) {

        this.type = type;
    }
}

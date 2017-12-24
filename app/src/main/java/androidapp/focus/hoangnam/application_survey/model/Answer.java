package androidapp.focus.hoangnam.application_survey.model;

/**
 * Created by huy.nquoc on 12/23/2017.
 */

public class Answer<DataClass> {

    private Question question;

    private DataClass data;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public DataClass getData() {
        return data;
    }

    public void setData(DataClass data) {
        this.data = data;
    }

    public Answer(Question question) {

        this.question = question;
    }
}

package ou.phamquangtinh.controller.config.DTO;

import java.util.Date;

public class Question {
    private String questionMessage;
    private Date date;

    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String questionMessage) {
        this.questionMessage = questionMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

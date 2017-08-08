package com.marcisz.patryk.integration.model;

public class Question {

    String question;
    String answer;
    String additionalLink;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if(this.answer == null){
            this.answer = answer;
        } else {
            this.answer += " " + answer;
        }
        this.answer = this.answer.replaceAll("more details\\.\\.\\.","");
    }

    public String getAdditionalLink() {
        return additionalLink;
    }

    public void setAdditionalLink(String additionalLink) {
        this.additionalLink = additionalLink;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Question{");
        sb.append("question='").append(question).append('\'');
        sb.append(", answer='").append(answer).append('\'');
        sb.append(", additionalLink='").append(additionalLink).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String print(){
        final StringBuffer sb = new StringBuffer();
        appendIfnotEmptyNotNull(question, sb);
        appendIfnotEmptyNotNull(answer, sb);
        appendIfnotEmptyNotNull(additionalLink, sb);
        return sb.toString();
    }

    private void appendIfnotEmptyNotNull(String value, StringBuffer buff){
        if(value!=null && !value.isEmpty()){
            buff.append(value).append("\n");
        }
    }
}

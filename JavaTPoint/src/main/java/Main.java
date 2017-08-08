import com.marcisz.patryk.integration.model.Question;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<Question> questionList = new ArrayList<Question>();

    public static void main(String[] args) throws IOException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String urls[] = {
                "https://www.javatpoint.com/corejava-interview-questions",
                "https://www.javatpoint.com/corejava-interview-questions-2",
                "https://www.javatpoint.com/corejava-interview-questions-3",
                "https://www.javatpoint.com/corejava-interview-questions-4",
                "https://www.javatpoint.com/corejava-interview-questions-5",
                "https://www.javatpoint.com/java-multithreading-interview-questions",
                "https://www.javatpoint.com/java-collections-interview-questions",
                "https://www.javatpoint.com/jdbc-interview-questions"
        };
        for(String url : urls) {
            parseUrl(url);
        }


        for(Question question : questionList){
            System.out.println(question.print());
        }
    }

    private static void parseUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("td");
        Question actualQuestion = new Question();
        for(Element el : elements){
            for(Element child : el.children()) {
                actualizeQuestion(actualQuestion, child);
                if(child.tagName().equals("hr")){
                    questionList.add(actualQuestion);
                    actualQuestion = new Question();
                }
            }
        }
    }

    static void actualizeQuestion(Question question, Element element){
        switch(element.tagName()){
            case "h3":
                question.setQuestion(element.text());
                for (Element el : element.children()) {
                    actualizeQuestion(question, el);
                }
                break;
            case "table":
                question.setAnswer(element.text());
                break;
            case "div":
            case "ul":
            case "ol":
                if(element.classNames().contains("codeblock")){
                    question.setAnswer("\n" + element.text());
                    break;
                } else {
                    for (Element el : element.children()) {
                        actualizeQuestion(question, el);
                    }
                    break;
                }
            case "li":
                question.setAnswer("* ");
            case "h4":
            case "p":
                question.setAnswer(element.text());
                if(element.text().contains("more details...")) {
                    for (Element el : element.getElementsByTag("a")) {
                        question.setAdditionalLink(el.attr("abs:href"));
                    }
                }
            break;
            case "a":
                question.setAdditionalLink(element.attr("abs:href"));
                break;
        }
    }



}
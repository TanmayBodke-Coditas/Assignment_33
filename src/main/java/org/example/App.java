package org.example;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory factory = cfg.buildSessionFactory();


        Question q1 = new Question();
        q1.setQuestionId(1212);
        q1.setQuestion("What is Java?");

        Answer answer = new Answer();
        answer.setAnswerId(343);
        answer.setAnswer("Java is prg lang");
        answer.setQuestion(q1);

        Answer answer1 = new Answer();
        answer1.setAnswerId(33);
        answer1.setAnswer("we can create softwares");
        answer1.setQuestion(q1);

        Answer answer2 = new Answer();
        answer2.setAnswerId(363);
        answer2.setAnswer("java has frameworks");
        answer2.setQuestion(q1);

        List<Answer> list = new ArrayList<Answer>();
        list.add(answer);
        list.add(answer1);
        list.add(answer2);
        q1.setAnswers(list);








        Session s = factory.openSession();

        Transaction tx = s.beginTransaction();
//
        s.save(q1);
        s.save(answer);
        s.save(answer1);
        s.save(answer2);




        tx.commit();
        //one to one
//        Question newQ = (Question) s.get(Question.class, 242);
//        System.out.println(newQ.getQuestion());
//        System.out.println(newQ.getAnswer().getAnswer());

        //one to many
        Question q = (Question) s.get(Question.class,1212);
        System.out.println(q.getQuestion());
        for (Answer a:q.getAnswers()) {
            System.out.println(a.getAnswer());
        }

        s.close();

        factory.close();
    }
}

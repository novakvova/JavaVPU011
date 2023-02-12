package program;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Привіт Java!");
        //insertRole();
        //showRoles();
        //addQuestion();
        //showListQuestions();
        //addUserAndRole();
        try (Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

        }
        AddCategoryProduct();
    }
    private static void AddCategoryProduct() {
        try (Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            Category category = new Category("Ноутбуки","notiebook.jpg",
                    new Date(), false);
            context.save(category);
            Product p = new Product("HP ZBook 15g8 Power",20834,"Для роботи",
                    new Date(),false, category);
            context.save(p);
            ProductImage pi = new ProductImage("1.jpg", 1,
                    new Date(), false,p);
            context.save(pi);
            ProductImage pi2 = new ProductImage("2.jpg", 2,
                    new Date(), false,p);
            context.save(pi2);
            tx.commit();
        }
    }

    private static void addUserAndRole()
    {
        try (Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            //var role = context.get(Role.class, 1);
            var role = new Role();
            role.setName("aladin");
            context.save(role);
            var user = new User("Юхим","Болотний",
                    "uxum@gmail.com","+38096 67 38 432","123456");
            context.save(user);
            var ur = new UserRole();
            ur.setUser(user);
            ur.setRole(role);
            context.save(ur);
            tx.commit();
        }
    }

    private static void insertRole() {
        Scanner in = new Scanner(System.in);
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Role role = new Role();
        System.out.println("Вкажіть назву ролі: ");
        String name = in.nextLine();
        role.setName(name);
        context.save(role);
        context.close();
    }

    private static void showRoles() {
        Scanner in = new Scanner(System.in);
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Role");
        List<Role> roles = query.list();
        for (Role role : roles)
            System.out.println(role);
        context.close();
    }

    private static void addQuestion() {
        Scanner in = new Scanner(System.in);
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        System.out.println("Вкажіть питання");
        String questionText = in.nextLine();
        Question q = new Question();
        q.setName(questionText);
        context.save(q);
        String action = "";
        do {
            System.out.println("Варіанти відповіді:");
            String text = in.nextLine();
            System.out.println("Правильно 1, невірно 0");
            boolean isTrue = Byte.parseByte(in.nextLine()) == 1 ? true : false;
            QuestionItem qi = new QuestionItem();
            qi.setText(text);
            qi.setTrue(isTrue);
            qi.setQuestion(q);
            context.save(qi);
            System.out.println("0. Вихід");
            System.out.println("1. Наступний варіант відповіді");
            System.out.print("->_");
            action = in.nextLine();
        }
        while (!action.equals("0"));
        tx.commit();
        context.close();
    }

    private static void showListQuestions() {
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Question");
        List<Question> questions = query.list();
        //questions.size()
        for (Question q : questions) {
            System.out.println(q);
            //System.out.println(q.getName());
        }
        context.close();
    }
}

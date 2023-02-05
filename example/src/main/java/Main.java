import dto.CategoryInsert;
import dto.CategoryItem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //String, int, double, float, boolean, byte, char, short, long
        //if, else - так як в с#
        //for, for()
        //testInput();
        //System.out.println(getRnadomNumber(2,10));
        //ArrayExample();
        //ArrayObject();
        //----------------Working mysql
//        String connection = "jdbc:mariadb://localhost:3306/java_vpu021";
//        String user ="root";
//        String password="";
        //----------------Working postgres
        String connection = "jdbc:postgresql://localhost:5432/java_vpu021";
        String user ="postgres";
        String password="123456";
        CategoryService categoryService = new CategoryService(connection, user, password);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Category name");
        String name = input.nextLine();
        CategoryInsert cat = new CategoryInsert(name);
        categoryService.Insert(cat);

        List<CategoryItem> items = categoryService.GetList();
        for(CategoryItem item : items)
            System.out.println(item);
    }

    static void ArrayObject() {
        Person [] persons = {
          new Person("Іван", "Рябко"),
          new Person("Оксана", "Рябко"),
          new Person("Артур", "Рябко"),
          new Person("Олег", "Шлунок"),
          new Person("Василь", "Марко")
        };
        for (Person p : persons)
            System.out.println(p);
        Arrays.sort(persons/*, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getLastName().compareTo(p2.getLastName());
            }
        }*/);
        System.out.println("----Сортований масив-----");

        for (Person p : persons)
            System.out.println(p);
    }
    static void ArrayExample()
    {
        final int n = 10;
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = getRnadomNumber(-10, 10);
        }
        for(int item : numbers) {
            System.out.printf("%d\t", item);
        }
        System.out.println();
        int count = 0;
        for(int item : numbers) {
            if(item>=0)
                count++;
        }
        System.out.println("Додатніх числе у масиві = "+ count);

        Arrays.sort(numbers);
        System.out.println("Сортований масив");
        for(int item : numbers) {
            System.out.printf("%d\t", item);
        }
    }

    static void testInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("a = ");
        int a = input.nextInt();
        System.out.println("Ви ввели значення = " + a);
    }

    public static int getRnadomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

package models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="tbl_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="text", nullable = false, length = 500)
    private String name;
    @OneToMany(mappedBy="question")
    private List<QuestionItem> questionItems;

    public Question() {
        questionItems = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (QuestionItem qi : questionItems) {
            sb.append(qi.getText()).append(", ");
        }
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionItems=" + sb.toString() +
                '}';
    }
}

package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_question_items")
public class QuestionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 500)
    private String text;
    @Column(name="is_true")
    private boolean isTrue;
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
}

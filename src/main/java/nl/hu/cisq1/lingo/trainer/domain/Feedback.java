package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Feedback")
@EqualsAndHashCode
@NoArgsConstructor
public class Feedback implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Getter
    private String guess;

    @ElementCollection
    @Getter
    private List<Mark> markPerLetter;

    public Feedback(String guess, List<Mark> markPerLetter) {
        this.guess = guess;
        this.markPerLetter = markPerLetter;
    }

    public static String createFirstHint(String word) {
        return word.charAt(0) +
                ".".repeat(word.length() - 1);
    }

    //return true when all marks in markPerLetter are correct else false
    public boolean isWordGuessed() {
        return !this.markPerLetter.contains(Mark.PRESENT) && !this.markPerLetter.contains(Mark.ABSENT);
    }

    //character is appended to hint if the corresponding index in markPerLetter is Mark.CORRECT
    //  or if the index already has a letter in the lastHint,
    //else(absent) "." is appended
    public String giveHint(String lastHint) {
        StringBuilder hint = new StringBuilder();
        for (int i = 0; i < this.guess.length(); i++) {
            if (this.markPerLetter.get(i).equals(Mark.CORRECT))
                hint.append(this.guess.charAt(i));
            else if (lastHint.charAt(i) != '.') {
                hint.append(lastHint.charAt(i));
            } else {
                hint.append('.');
            }
        }
        return hint.toString();
    }
}

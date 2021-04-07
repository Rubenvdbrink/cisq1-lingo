package nl.hu.cisq1.lingo.trainer.domain;

import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ConvertGuessToMarks {
    public static List<Mark> converter(String wordToGuess, String guess) {
        if (wordToGuess.length() != guess.length())
            throw new InvalidFeedbackException("⏺ ⏺ ⏺ ⏺ Length is of the guess is not the same as the word to guess ⏺ ⏺ ⏺ ⏺");
        List<Mark> allMarks = new ArrayList<>();
        List<Character> nonCorrectLetters = new ArrayList<>();

        boolean correctAndAbsentConverted = false;
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char wordToGuessChar = wordToGuess.charAt(i);

            if (!correctAndAbsentConverted) {
                if (wordToGuessChar == guessChar) {
                    allMarks.add(Mark.CORRECT);
                    continue;
                }
                allMarks.add(Mark.ABSENT);
                nonCorrectLetters.add(wordToGuessChar);
                if (i + 1 == guess.length()) {
                    correctAndAbsentConverted = true;
                    i = 0;
                }
            }
            if (nonCorrectLetters.contains(guessChar) && allMarks.get(i) == Mark.ABSENT) {
                nonCorrectLetters.remove((Character) guess.charAt(i));
                allMarks.set(i, Mark.PRESENT);
            }
        }
        return allMarks;
    }
}

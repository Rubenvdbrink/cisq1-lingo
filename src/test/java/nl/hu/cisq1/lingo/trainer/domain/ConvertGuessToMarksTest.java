package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertGuessToMarksTest {

    static Stream<Arguments> provideMarksExamples() {
        return Stream.of(
                Arguments.of("banana", "banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("banana", "spores", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)),
                Arguments.of("banana", "ananab", List.of(Mark.PRESENT, Mark.PRESENT, Mark.PRESENT, Mark.PRESENT, Mark.PRESENT, Mark.PRESENT)),
                Arguments.of("banana", "banned", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.ABSENT))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMarksExamples")
    @DisplayName("Convert guess to marks succesfully")
    void convertToMarksSuccesful(String wordToGuess, String guess, List<Mark> marks) {
        assertEquals(marks, new ConvertGuessToMarks().converter(wordToGuess, guess));
    }
}
package nl.hu.cisq1.lingo.trainer.presentation;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.application.LingoService;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import nl.hu.cisq1.lingo.trainer.presentation.dto.HintDTO;
import nl.hu.cisq1.lingo.trainer.presentation.dto.LingoDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lingo")
@RequiredArgsConstructor
public class GameController {
    private final LingoService lingoService;

    @PostMapping("startgame")
    public LingoDTO startGame() {
        return new LingoDTO(this.lingoService.startGame());
    }

    @PostMapping("guess")
    public HintDTO makeGuess(@RequestBody GuessDTO guess, @RequestParam Long gameId) {
        return createHintDTO(this.lingoService.makeGuess(guess.guess, gameId));
    }

    private HintDTO createHintDTO(LingoGame game) {
        return new HintDTO(
                game.getScore(),
                game.getLastRound().getTries(),
                game.getLastRound().getAllFeedback(),
                getHint(game.getLastRound())
        );
    }

    private String getHint(Round round) {
        if(round.getRoundState().equals(RoundState.LOST)) {
            return round.getWordToGuess();
        } else {
            return round.getLastHint();
        }
    }
}

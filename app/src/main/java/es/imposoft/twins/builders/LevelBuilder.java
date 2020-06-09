package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;

public interface LevelBuilder {
    void setChronometer(int chronometerSeconds, Chronometer chronometerType);
    void setMinScore(int minScore);
    void setCardAmount(int cardAmount);
    void setRevealTime(int revealTime);
    void setCardTheme(DeckTheme deckTheme);
    void setGameMode(GameMode gameMode);
    void setId(int levelId);
    void setSong(int song);
    void setMaxFails(int maxFails);
}

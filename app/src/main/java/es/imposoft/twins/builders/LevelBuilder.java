package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.gametypes.Game;

public interface LevelBuilder {
    void setChronometerParameters(int chronometerSeconds, Chronometer chronometerType);

    void setScoreParameters(int minScore, int maxFails);

    void setTableParameters(int cardAmount, int revealTime, DeckTheme deckTheme);

    void setGameParameters(GameMode gameMode, int levelId);

    void setSong(int song);
}

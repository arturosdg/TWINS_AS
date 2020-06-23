package es.imposoft.twins.logic.builders;

import es.imposoft.twins.logic.components.DeckSize;
import es.imposoft.twins.logic.components.DeckTheme;
import es.imposoft.twins.logic.components.Chronometer;
import es.imposoft.twins.logic.components.GameDuration;
import es.imposoft.twins.logic.components.GameMode;
import es.imposoft.twins.logic.components.MaxFails;
import es.imposoft.twins.logic.components.MinScore;
import es.imposoft.twins.logic.components.RevealTime;

public interface LevelBuilder {
    void setChronometerParameters(GameDuration gameDuration, Chronometer chronometerType);

    void setScoreParameters(MinScore minScore, MaxFails maxFails);

    void setTableParameters(DeckSize deckSize, RevealTime revealTime, DeckTheme deckTheme);

    void setGameParameters(GameMode gameMode, int levelId);

    void setSong(int song);
}

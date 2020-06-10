package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckSize;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameDuration;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.components.MaxFails;
import es.imposoft.twins.components.MinScore;
import es.imposoft.twins.components.RevealTime;
import es.imposoft.twins.gametypes.Game;

public interface LevelBuilder {
    void setChronometerParameters(GameDuration gameDuration, Chronometer chronometerType);

    void setScoreParameters(MinScore minScore, MaxFails maxFails);

    void setTableParameters(DeckSize deckSize, RevealTime revealTime, DeckTheme deckTheme);

    void setGameParameters(GameMode gameMode, int levelId);

    void setSong(int song);
}

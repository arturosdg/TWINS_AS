package es.imposoft.twins.logic.strategies;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;

import es.imposoft.twins.logic.card.Card;
import es.imposoft.twins.logic.components.DeckTheme;
import es.imposoft.twins.logic.gametypes.Game;

public interface Dealer {
    void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context, String email);
}

package es.imposoft.twins.strategies;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.gametypes.Game;

public interface Dealer {
    void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context, String email);
}

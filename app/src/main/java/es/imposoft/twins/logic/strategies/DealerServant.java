package es.imposoft.twins.logic.strategies;

import java.util.ArrayList;

class DealerServant {
    private static final int MAX_CARD_DESIGNS = 12;

    static ArrayList<Integer> randomList(int totalCards) {
        ArrayList<Integer> cardNumbers = new ArrayList<>();
        if (totalCards == 24) {
            for (int i = 0; i < totalCards / 2; i++)
                cardNumbers.add(i);
        } else {
            while (cardNumbers.size() <= totalCards / 2) {
                int random = (int) (Math.random() * MAX_CARD_DESIGNS);
                if (!cardNumbers.contains(random)) {
                    cardNumbers.add(random);
                }
            }
        }
        return cardNumbers;
    }
}

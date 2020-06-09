package es.imposoft.twins.strategies;

import java.util.ArrayList;

public class DealerServant {
    static int MAX_CARD_DESIGNS = 12;
    public static ArrayList<Integer> randomList(int totalCards) {
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

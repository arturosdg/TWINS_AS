package es.imposoft.twins;

public class CardImageController {

    static int[] matchPositions;  //Method to generate a random number in a range

    private static int randomPosition(int maxPos) {
        return (int) (Math.random() * (maxPos));
    }

    //Method
    public int[] matchingCards(int maxPos) {
        fillArray(maxPos);
        //Posiciones en el array de la pareja
        int pos1 = -1, pos2 = -1;
        for (int i = maxPos / 2; i > 0; i--) {
            do {
                pos1 = randomPosition(maxPos);
                matchPositions[pos1] = i;
                pos2 = randomPosition(maxPos);
                matchPositions[pos2] = i;
            } while (!positionExists(matchPositions[pos1], -1) || !positionExists(matchPositions[pos2], -1));
        }
        return matchPositions;
    }

    public static boolean positionExists(int pos, int aux) {
        return aux == pos;
    }

    private static void fillArray(int maxPos) {
        matchPositions = new int[maxPos];
        for (int i = 0; i < maxPos; i++) matchPositions[i] = -1;
    }
}

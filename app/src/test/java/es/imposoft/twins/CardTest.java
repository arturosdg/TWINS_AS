package es.imposoft.twins;

import android.content.Context;

import org.junit.Test;

import es.imposoft.twins.card.ConcreteCard;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void testPoints(){
        ConcreteCard concreteCard = new ConcreteCard();
        assertEquals("Points are not calculated correctly",2,concreteCard.getPoints());
    }

    @Test
    public void cardsAreEquals(){
        ConcreteCard concreteCard = new ConcreteCard();
        concreteCard.setFrontName(123);
        ConcreteCard concreteCard2 = new ConcreteCard();
        concreteCard2.setFrontName(123);
        assertTrue("Cards should be equal but they are not",concreteCard.equals(concreteCard2));
    }

    @Test
    public void cardsAreNotEquals(){
        ConcreteCard concreteCard = new ConcreteCard();
        concreteCard.setFrontName(1234);
        ConcreteCard concreteCard2 = new ConcreteCard();
        concreteCard2.setFrontName(123);
        assertFalse("Cards should be equal but they are not", concreteCard.equals(concreteCard2));
    }
}

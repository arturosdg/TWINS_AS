package es.imposoft.twins;

import android.content.Context;

import org.junit.Test;

import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.card.SpecialCardDecorator1;
import es.imposoft.twins.card.SpecialCardDecorator2;
import es.imposoft.twins.card.SpecialCardDecorator3;

import static org.junit.Assert.*;

public class DecoratedCardTest {
    @Test
    public void testRightPointsDecoratedCard1(){
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);
        assertEquals("Points are not calculated correctly",5,decoratedCard.getPoints());
    }

    @Test
    public void testRightPointsDecoratedCard2(){
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator2 decoratedCard = new SpecialCardDecorator2(concreteCard);
        assertEquals("Points are not calculated correctly",7,decoratedCard.getPoints());
    }

    @Test
    public void testRightPointsDecoratedCard3(){
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator3 decoratedCard = new SpecialCardDecorator3(concreteCard);
        assertEquals("Points are not calculated correctly",9,decoratedCard.getPoints());
    }

    @Test
    public void cardsAreEqualsDecoratedCard1(){
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);
        SpecialCardDecorator1 decoratedCard2 = new SpecialCardDecorator1(concreteCard2);

        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreEqualsDecoratedCard2(){
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator2 decoratedCard = new SpecialCardDecorator2(concreteCard);
        SpecialCardDecorator2 decoratedCard2 = new SpecialCardDecorator2(concreteCard2);

        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreEqualsDecoratedCard3(){
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator3 decoratedCard = new SpecialCardDecorator3(concreteCard);
        SpecialCardDecorator3 decoratedCard2 = new SpecialCardDecorator3(concreteCard2);

        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreNotEquals(){
        ConcreteCard concreteCard = new ConcreteCard();
        concreteCard.setFrontName(1234);
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);
        ConcreteCard concreteCard2 = new ConcreteCard();
        concreteCard2.setFrontName(123);
        assertFalse("Cards should be equal but they are not", decoratedCard.equals(concreteCard2));
    }
}

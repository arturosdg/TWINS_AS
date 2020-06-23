package es.imposoft.twins;

import org.junit.Test;

import es.imposoft.twins.logic.card.ConcreteCard;
import es.imposoft.twins.logic.card.SpecialCardDecorator1;
import es.imposoft.twins.logic.card.SpecialCardDecorator2;
import es.imposoft.twins.logic.card.SpecialCardDecorator3;

import static org.junit.Assert.*;

public class DecoratedCardTest {
    @Test
    public void testRightPointsDecoratedCard1(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);

        //Assert
        assertEquals("Points are not calculated correctly",5,decoratedCard.getPoints());
    }

    @Test
    public void testRightPointsDecoratedCard2(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator2 decoratedCard = new SpecialCardDecorator2(concreteCard);

        //Assert
        assertEquals("Points are not calculated correctly",7,decoratedCard.getPoints());
    }

    @Test
    public void testRightPointsDecoratedCard3(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator3 decoratedCard = new SpecialCardDecorator3(concreteCard);

        //Assert
        assertEquals("Points are not calculated correctly",9,decoratedCard.getPoints());
    }

    @Test
    public void cardsAreEqualsDecoratedCard1(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);
        SpecialCardDecorator1 decoratedCard2 = new SpecialCardDecorator1(concreteCard2);

        //Act
        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        //Assert
        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreEqualsDecoratedCard2(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator2 decoratedCard = new SpecialCardDecorator2(concreteCard);
        SpecialCardDecorator2 decoratedCard2 = new SpecialCardDecorator2(concreteCard2);

        //Act
        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        //Assert
        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreEqualsDecoratedCard3(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();
        SpecialCardDecorator3 decoratedCard = new SpecialCardDecorator3(concreteCard);
        SpecialCardDecorator3 decoratedCard2 = new SpecialCardDecorator3(concreteCard2);

        //Act
        decoratedCard.setFrontName(123);
        decoratedCard2.setFrontName(123);

        //Assert
        assertTrue("Cards should be equal but they are not", decoratedCard.equals(decoratedCard2));
    }

    @Test
    public void cardsAreNotEquals(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator1 decoratedCard = new SpecialCardDecorator1(concreteCard);
        ConcreteCard concreteCard2 = new ConcreteCard();

        //Act
        concreteCard.setFrontName(1234);
        concreteCard2.setFrontName(123);

        //Assert
        assertFalse("Cards should be equal but they are not", decoratedCard.equals(concreteCard2));
    }
}

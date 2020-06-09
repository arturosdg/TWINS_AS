package es.imposoft.twins;

import android.content.Context;

import org.junit.Test;

import es.imposoft.twins.card.ConcreteCard;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void testPoints(){
        //Arrange/Act
        ConcreteCard concreteCard = new ConcreteCard();

        //Assert
        assertEquals("Points are not calculated correctly",2,concreteCard.getPoints());
    }

    @Test
    public void cardsAreEquals(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();

        //Act
        concreteCard.setFrontName(123);
        concreteCard2.setFrontName(123);

        //Assert
        assertTrue("Cards should be equal but they are not",concreteCard.equals(concreteCard2));
    }

    @Test
    public void cardsAreNotEquals(){
        //Arrange
        ConcreteCard concreteCard = new ConcreteCard();
        ConcreteCard concreteCard2 = new ConcreteCard();

        //Act
        concreteCard.setFrontName(1234);
        concreteCard2.setFrontName(123);

        //Assert
        assertFalse("Cards shouldn't be equal but they are", concreteCard.equals(concreteCard2));
    }
}

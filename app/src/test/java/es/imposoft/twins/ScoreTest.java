package es.imposoft.twins;

import org.junit.Test;
import es.imposoft.twins.card.*;
import es.imposoft.twins.prototype.*;
import static org.junit.Assert.*;

public class ScoreTest {
    @Test
    public void testPointsNormal(){
        //Arrange/Act
        ConcreteCard concreteCard = new ConcreteCard();
        AbstractScore scoreCasual = new ScoreCasual();
        AbstractScore scoreStandar = new ScoreStandard();
        AbstractScore scoreLevels = new ScoreLevels();

        //Assert
        assertEquals("Points are not calculated correctly",10,scoreCasual.updateScore(true, concreteCard));
        assertEquals("Points are not calculated correctly",6,scoreCasual.updateScore(false, concreteCard));

        assertEquals("Points are not calculated correctly",6,scoreStandar.updateScore(true, concreteCard));
        assertEquals("Points are not calculated correctly",1,scoreStandar.updateScore(false, concreteCard));

        assertEquals("Points are not calculated correctly",8,scoreLevels.updateScore(true, concreteCard));
        assertEquals("Points are not calculated correctly",0,scoreLevels.updateScore(false, concreteCard));
    }

    @Test
    public void testPointsSpecial1(){
        //Arrange/Act
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator1 specialCardDecorator1 = new SpecialCardDecorator1(concreteCard);
        AbstractScore scoreStandard = new ScoreStandard();
        AbstractScore scoreLevels = new ScoreLevels();
        //Assert
        assertEquals("Points are not calculated correctly",15,scoreStandard.updateScore(true, specialCardDecorator1));
        assertEquals("Points are not calculated correctly",10,scoreStandard.updateScore(false, specialCardDecorator1));

        assertEquals("Points are not calculated correctly",20,scoreLevels.updateScore(true, specialCardDecorator1));
        assertEquals("Points are not calculated correctly",8,scoreLevels.updateScore(false, specialCardDecorator1));
    }

    @Test
    public void testPointsSpecial2(){
        //Arrange/Act
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator2 specialCardDecorator2 = new SpecialCardDecorator2(concreteCard);
        AbstractScore scoreStandard = new ScoreStandard();
        AbstractScore scoreLevels = new ScoreLevels();

        //Assert
        assertEquals("Points are not calculated correctly",21,scoreStandard.updateScore(true, specialCardDecorator2));
        assertEquals("Points are not calculated correctly",16,scoreStandard.updateScore(false, specialCardDecorator2));

        assertEquals("Points are not calculated correctly",28,scoreLevels.updateScore(true, specialCardDecorator2));
        assertEquals("Points are not calculated correctly",16,scoreLevels.updateScore(false, specialCardDecorator2));
    }

    @Test
    public void testPointsSpecial3(){
        //Arrange/Act
        ConcreteCard concreteCard = new ConcreteCard();
        SpecialCardDecorator3 specialCardDecorator3 = new SpecialCardDecorator3(concreteCard);
        AbstractScore scoreStandard = new ScoreStandard();
        AbstractScore scoreLevels = new ScoreLevels();

        //Assert
        assertEquals("Points are not calculated correctly",27,scoreStandard.updateScore(true, specialCardDecorator3));
        assertEquals("Points are not calculated correctly",22,scoreStandard.updateScore(false, specialCardDecorator3));

        assertEquals("Points are not calculated correctly",36,scoreLevels.updateScore(true, specialCardDecorator3));
        assertEquals("Points are not calculated correctly",24,scoreLevels.updateScore(false, specialCardDecorator3));
    }


}

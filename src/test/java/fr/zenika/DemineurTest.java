package fr.zenika;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Unit test for simple Demineur.
 */
public class DemineurTest {

    @Test
    public void testOneCellBoard() {
        String[] input = {"*"};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*");
    }

    @Test(expected = BoardCannotBeNullException.class)
    public void testBoardNotNull() {
        Demineur demineur = new Demineur();
        demineur.resolve(null);
    }

    @Test(expected = BoardMalformedException.class)
    public void testMalformedBoards() {
        String[] input = {"*.","..."};
        Demineur demineur = new Demineur();
        demineur.resolve(input);
    }

    @Test
    public void testOneCellWithDotBoard() {
        String[] input = {"."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("0");
    }

    @Test
    public void testTwoCellWithDotBoard() {
        String[] input = {".."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("00");
    }

    @Test
    public void testTwoCellWithDotStarBoard() {
        String[] input = {".*"};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("1*");
    }

    @Test
    public void testTwoCellWithStarDotBoard() {
        String[] input = {"*."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*1");
    }

    @Test
    public void testTenCellsOneLineComplicated() {
        String[] input = {"..*.***.."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("01*2***10");
    }

    @Test
    public void testTwoLineDotMine() {
        String[] input = {".","*"};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("1","*");
    }

    @Test
    public void testTwoLinesMineDot() {
        String[] input = {"*","."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*","1");
    }

    @Test
    public void testThreeLinesMineDot() {
        String[] input = {"*",".","."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*","1","0");
    }

}


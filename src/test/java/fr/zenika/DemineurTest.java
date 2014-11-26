package fr.zenika;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Unit test for simple Demineur.
 */
public class DemineurTest {

    @Test
    public void testOneMine() {
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
        String[] input = {"*.", "..."};
        Demineur demineur = new Demineur();
        demineur.resolve(input);
    }

    @Test
    public void testOneDot() {
        String[] input = {"."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("0");
    }

    @Test
    public void testLineDotDot() {
        String[] input = {".."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("00");
    }

    @Test
    public void testLineDotMine() {
        String[] input = {".*"};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("1*");
    }

    @Test
    public void testLineMineDot() {
        String[] input = {"*."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*1");
    }

    @Test
    public void testLineTenCellsComplicated() {
        String[] input = {"..*.***.."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("01*2***10");
    }

    @Test
    public void testColumnDotMine() {
        String[] input = {".", "*"};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("1", "*");
    }

    @Test
    public void testColumnMineDot() {
        String[] input = {"*", "."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*", "1");
    }

    @Test
    public void testColumnMineDotDot() {
        String[] input = {"*", ".", "."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("*", "1", "0");
    }

    @Test
    public void testLineAndColumnDotMine_MineDot() {
        String[] input = {".*", "*."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("2*", "*2");
    }

}


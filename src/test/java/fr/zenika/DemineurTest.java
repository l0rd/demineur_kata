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

    /**
     * Même test compliqué qu'on a fait pour la ligne, mais sur une colonne.
     * Ce test passe déjà... Je le laisse juste pour te le montrer, mais tu peux le retirer du coup puisqu'on est pas
     * sensé rajouter des tests qui ne servent à rien... Ceci dit perso, je trouve qu'il ne fait de mal à personne
     * ici... Et je pense que mon CPU et ma RAM peuvent supporter cette surcharge.
     * Ce commentaire s'autodétruira dans 5 secondes.
     */
    @Test
    public void testColumnTenCellsComplicated() {
        String[] input = {".", ".", "*", ".", "*", "*", "*", ".", "."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("0", "1", "*", "2", "*", "*", "*", "1", "0");
    }

    @Test
    public void testLineAndColumnDotMine_MineDot() {
        String[] input = {".*", "*."};
        Demineur demineur = new Demineur();
        String[] output = demineur.resolve(input);
        Assertions.assertThat(output).containsExactly("2*", "*2");
    }

}


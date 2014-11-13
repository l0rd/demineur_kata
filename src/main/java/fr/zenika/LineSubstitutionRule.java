package fr.zenika;

/**
 * Created by polux on 11/5/14.
 */
public class LineSubstitutionRule {
    String regex;
    String substitute;

    public LineSubstitutionRule(String regex, String substitute) {
        this.regex = regex;
        this.substitute = substitute;
    }

    public String apply(String line) {
        return line.replaceAll(regex, substitute);
    }

}

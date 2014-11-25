package fr.zenika;

public class SubstitutionRule {
    String regex;
    String substitute;

    public SubstitutionRule(String regex, String substitute) {
        this.regex = regex;
        this.substitute = substitute;
    }

    public String apply(String line) {
        return line.replaceAll(regex, substitute);
    }

}

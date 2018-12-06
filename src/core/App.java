package core;

import ai.GenderDetector;

public class App {
    public String detectar(final String nomeCompleto) {
        switch (new GenderDetector().detect(nomeCompleto)) {
            case 0:
                return "Indefinido";
            case 1:
                return "Feminino";
            case 2:
                return "Masculino";
            default:
                return null;
        }
    }
}

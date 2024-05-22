package cr.ac.ucr.progra2.paraiso.prograproject.common;

import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatronesProtocol {
    private List<DesignPattern> patterns;

    public PatronesProtocol(List<DesignPattern> patterns) {
        this.patterns = patterns;
    }

    public PatronesProtocol() throws IOException {
        patterns = new ArrayList<>();
        patterns.add(new DesignPattern("Context One", "Problem One", "Solution One", "Example One", "Creational", "Image", 111));
        patterns.add(new DesignPattern("Context Two", "Problem Two", "Solution Two", "Example Two", "Structural", "Image",222));
        patterns.add(new DesignPattern("Context Three", "Problem Three", "Solution Three", "Example Three", "Behavioral", "Image",333));
    }

    public String procesarEntrada(String entrada) {
        if (entrada == null || entrada.isEmpty()) {
            return "Ingrese 'pattern one', 'pattern two' o 'pattern three' para recibir información sobre un patrón en particular.";
        }

        switch (entrada.toLowerCase()) {
            case "pattern one":
                return patterns.get(0).toString();
            case "pattern two":
                return patterns.get(1).toString();
            case "pattern three":
                return patterns.get(2).toString();
            default:
                return "Opción no válida. Por favor, ingrese 'pattern one', 'pattern two' o 'pattern three'.";
        }
    }
}


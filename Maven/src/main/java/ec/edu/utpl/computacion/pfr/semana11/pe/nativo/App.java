package ec.edu.utpl.computacion.pfr.semana11.pe.nativo;
import java.lang.ref.SoftReference;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.function.Predicate;


public class App {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> valuesPublisher = new SubmissionPublisher<>();

        // Función mapear de texto a valor real

        Function<String, Double> mapValue = mas -> Double.valueOf(mas.split(":")[1]);

        // Función filtrar valores mayores a 21.0
        Predicate<Double> filterValue = text -> text > 21.0;

        // Para mapear
        MapProcessor<String, Double> mapValueProcessor = new MapProcessor<>(mapValue);

        // Para filtrar
        FilterProcessor<Double, Double> filterValueProcessor = new FilterProcessor<>(filterValue);

        // Subscriber para imprimir
        PrinterSubscriber<Double> printerValueSubscriber = new PrinterSubscriber<>();

        // Flujo
        valuesPublisher.subscribe(mapValueProcessor);
        mapValueProcessor.subscribe(filterValueProcessor);
        filterValueProcessor.subscribe(printerValueSubscriber);

        List<String> values = List.of("TERM_1:22", "TERM_2:36", "TERM_3:19.7", "TERM_4:28.1", "TERM_5:22");
        // Enviar los datos a los suscritores
        values.forEach(valuesPublisher::submit);

        Thread.sleep(1 * 1000);

        valuesPublisher.close();
    }
}

package ec.edu.utpl.computacion.pfr.semana11.pe.rx;
import io.reactivex.rxjava3.core.Observable;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {
        List<String> items = List.of("TERM_1:22", "TERM_2:36", "TERM_3:19.7", "TERM_4:28.1", "TERM_5:22");
        Observable.fromIterable(items)

                .map(text -> text.split(".")[1] )
                .map(Double:: valueOf )
                .filter(x->x < 21.0)
                .subscribe(System.out::println);

        Thread.sleep(1 * 1000);
    }
}

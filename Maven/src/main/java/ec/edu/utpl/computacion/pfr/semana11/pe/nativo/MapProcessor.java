package ec.edu.utpl.computacion.pfr.semana11.pe.nativo;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
public class MapProcessor <T, R> extends SubmissionPublisher<R>
        implements Flow.Processor<T, R> {

    private Flow.Subscription subscription;
    private Function function;

    public MapProcessor(Function<? super T, ? extends R> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit((R) function.apply(item));
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() { close(); }
}

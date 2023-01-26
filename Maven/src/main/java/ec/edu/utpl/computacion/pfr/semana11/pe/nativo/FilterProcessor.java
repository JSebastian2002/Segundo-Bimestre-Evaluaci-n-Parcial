package ec.edu.utpl.computacion.pfr.semana11.pe.nativo;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Predicate;

public class FilterProcessor <T, R> extends SubmissionPublisher<R>
        implements Flow.Processor<T, R> {

    private Predicate<T> predicate;
    private Flow.Subscription subscription;

    public FilterProcessor(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        if(predicate.test(item)) {
            submit((R) item);
        }
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}

package ec.edu.utpl.computacion.pfr.semana11.pe.nativo;

import java.util.concurrent.Flow;

public class PrinterSubscriber <T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Suscrito");
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.printf("Valor: %s\n", item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Todo procesado");
    }
}

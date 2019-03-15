package org.enercoop.exo;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide log directory");
        }

        IdCounter counter = new IdCounter.Builder()
                .withProvider(UrlProvider.getFor(args[0]))
                .build();
        System.out.println(counter.getTop(5));
    }
}

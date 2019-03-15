package org.enercoop.exo;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage : java -jar jarname fileOrDirectoryName");
        }
        UrlProvider provider = UrlProvider.getFor(args[0]);
        IdCounter counter = new IdCounter.Builder().withProvider(provider).build();
        counter.getTop(5).forEach(System.out::println);
    }
}

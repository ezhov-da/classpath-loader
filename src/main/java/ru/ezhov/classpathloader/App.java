package ru.ezhov.classpathloader;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Use arguments:");
            System.out.println("1. Path to classpath folder with jar");
            System.out.println("2. recursive (boolean) [optional] default false");
            return;
        }

        String pathToFolderClassPath = args[0];
        boolean recursive = false;

        if (args.length == 2) {
            recursive = Boolean.parseBoolean(args[1]);
        }

        ClassPathLoader classPathLoader = new ClassPathLoader(pathToFolderClassPath, recursive);
        classPathLoader.load();
    }
}

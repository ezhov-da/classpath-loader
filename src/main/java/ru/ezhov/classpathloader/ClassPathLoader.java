package ru.ezhov.classpathloader;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassPathLoader {

    private String pathToFolderClassPath;
    private boolean recursive;

    public ClassPathLoader(String pathToFolderClassPath, boolean recursive) {
        this.pathToFolderClassPath = pathToFolderClassPath;
        this.recursive = recursive;
    }

    public void load() {

        List<File> files;
        List<String> path = getPathList(pathToFolderClassPath);

        if (recursive) {
            files = processRecursive(path);
        } else {
            files = processNotRecursive(path);
        }

        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private List<File> processRecursive(List<String> path) {
        List<File> files = new ArrayList<File>();
        for (String s : path) {
            getRecursiveFilesJar(s, files);
        }

        return files;
    }

    private List<File> processNotRecursive(List<String> path) {
        List<File> files = new ArrayList<File>();
        for (String s : path) {
            List<File> filesFromPath = getFilesJarFromFolder(s);

            files.addAll(filesFromPath);
        }

        return files;
    }

    private List<String> getPathList(String path) {
        String[] strings = path.split(";");
        return Arrays.asList(strings);
    }

    private void getRecursiveFilesJar(String path, List<File> filesJarForFill) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getRecursiveFilesJar(file.getAbsolutePath(), filesJarForFill);
            } else {
                if (isJarFile(file)) {
                    filesJarForFill.add(file);
                }
            }
        }
    }

    private List<File> getFilesJarFromFolder(String path) {
        File file = new File(path);
        File[] files = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return isJarFile(pathname);
            }
        });

        if (files.length == 0) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(files);
        }
    }

    private boolean isJarFile(File file) {
        return file.getAbsolutePath().endsWith(".jar");
    }
}

package hpu.liyy.classloader;

public class T01_ClassLoaderScope {
    public static void main(String[] args) {
        System.out.println("----------pathBoot--------------");
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));

        System.out.println();
        System.out.println("----------pathExt---------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));

        System.out.println();
        System.out.println("----------pathApp---------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }
}

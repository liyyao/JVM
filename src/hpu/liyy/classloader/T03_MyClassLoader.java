package hpu.liyy.classloader;


import java.io.*;
import java.nio.file.Files;

/**
 * 自定义ClassLoader
 */
public class T03_MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("d:/test/", name.replace('.', '/') + ".class");
        try {
            InputStream fis = Files.newInputStream(f.toPath());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != 0) {
                os.write(b);
            }
            byte[] bytes = os.toByteArray();
            os.close();
            fis.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader l = new T03_MyClassLoader();
        Class<?> clazz = l.loadClass("hpu.liyy.classloader.T01_ClassLoaderScope");
        T01_ClassLoaderScope t = (T01_ClassLoaderScope) clazz.newInstance();
        t.main(args);

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
    }
}

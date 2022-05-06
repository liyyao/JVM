package hpu.liyy.classloader;


import java.io.*;
import java.nio.file.Files;

/**
 * 自定义ClassLoader 对class文件进行加密
 */
public class T04_MyClassLoaderEncription extends ClassLoader {

    public static int seed = 0B10110110;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("d:/test/", name.replace('.', '/').concat(".lyclass"));
        try {
            InputStream fis = Files.newInputStream(f.toPath());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != 0) {
                os.write(b ^ seed);
            }
            byte[] bytes = os.toByteArray();
            os.close();
            fis.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void encFile(String name) {
        File f = new File("d:/test/", name.replace('.', '/').concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(new File("d:/test/", name.replaceAll(".", "/").concat(".lyclass")));
            int b = 0;
            while ((b = fis.read()) != -1) {
                fos.write(b ^ seed);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        encFile("hpu.liyy.classloader.T01_ClassLoaderScope");
        ClassLoader l = new T04_MyClassLoaderEncription();
        Class<?> clazz = l.loadClass("hpu.liyy.classloader.T01_ClassLoaderScope");
        T01_ClassLoaderScope t = (T01_ClassLoaderScope) clazz.newInstance();
        t.main(args);

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
    }
}

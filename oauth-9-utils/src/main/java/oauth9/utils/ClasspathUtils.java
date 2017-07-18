package oauth9.utils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class ClasspathUtils {

    public static String resourceToString(Class<?> klass, String resourcePath) throws RuntimeException {
        try {
            Reader reader = resourceReader(klass, resourcePath);
            return IOUtils.toString(reader);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load classpath resource " + resourcePath + ": " + ex, ex);
        }
    }

    public static String resourceToString(String resourcePath) throws RuntimeException {
        return resourceToString(ClasspathUtils.class, resourcePath);
    }

    public static String textFileNextToClass(Class<?> klass, String fileName) throws RuntimeException {
        String resourcePath = resourcePathNextToClass(klass, fileName);
        return resourceToString(klass, resourcePath);
    }

    private static Reader resourceReader(Class<?> klass, String resourcePath) throws UnsupportedEncodingException {
        InputStream inputStream = klass.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new RuntimeException("Resource path not found: " + resourcePath);
        }
        return new InputStreamReader(inputStream, "UTF-8");
    }

    public static String resourcePathNextToClass(Class<?> klass, String fileName) {
        String path = "/" + klass.getPackage().getName().replace(".", "/") + "/" + fileName;
        int byteCodeManipulatorPostfix = path.indexOf("$$");
        if (byteCodeManipulatorPostfix > 0) {
            path = path.substring(0, byteCodeManipulatorPostfix);
        }
        return path;
    }
}

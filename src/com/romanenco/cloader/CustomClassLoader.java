package com.romanenco.cloader;

import com.romanenco.cloader.file.ByteFileReader;

/**
 * Our custom classloader. At creation time, owner has to set valid class path
 */
public class CustomClassLoader extends ClassLoader {

    private final ByteCodeReader reader;

    public CustomClassLoader(final ClassLoader classLoader,
            final String classPath) {
        super(classLoader);
        this.reader = new ByteFileReader(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (clazz == null) {
            final byte[] bytes = reader.read(name);
            clazz = defineClass(name, bytes, 0, bytes.length);
            resolveClass(clazz);
        }
        return clazz;
    }


}

package com.romanenco.cloader.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.romanenco.cloader.ByteCodeReader;

/**
 * Actual read of the bytecode from class file in configured classpath
 */
public class ByteFileReader implements ByteCodeReader {

    private final File classPathRoot;

    public ByteFileReader(String classPath) {
        this.classPathRoot = new File(classPath);
    }

    @Override
    public byte[] read(String fullyQualifiedClassName) {
        final String path = fullyQualifiedClassName.replace(".", "/") + ".class";
        final File file = new File(classPathRoot, path);
        try (final InputStream stream = new FileInputStream(file);
             final ByteArrayOutputStream bos =
                     new ByteArrayOutputStream((int)file.length());
                ) {
            int len;
            final byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error reading bytecode", e);
        }
    }

}

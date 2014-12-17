package com.romanenco.cloader;

/**
 * Reading bytecode
 */
public interface ByteCodeReader {

    byte[] read(String fullyQualifiedClassName);

}

package com.romanenco.cloader;

/**
 * Dynamic class loading.
 * For this example we have two different versions of dynamic.Test class,
 * compiled into different locations (dynamic_classes_1 and dynamic_classes_2).
 *
 * By creating one classloader per a classpath we can load both versions of
 * dynamic class and call interface methods on those instances.
 */
public class Example {

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        for (final String classPath: new String[] {
                "./dynamic_classes_1",
                "./dynamic_classes_2"
                }) {
            final Action action = getActionFromClassPath(classPath);
            action.execute();
        }
    }

    private static Action getActionFromClassPath(String classPath)
            throws ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        final ClassLoader loader = new CustomClassLoader(
                Example.class.getClassLoader(),
                classPath
                );
        final Class<?> clazz = loader.loadClass("dynamic.DynamicClass");
        return (Action)clazz.newInstance();
    }

}

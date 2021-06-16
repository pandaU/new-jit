package com.example.jit.utils;


import com.example.jit.config.PathConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>
 * The type Web api class loader.
 *
 * @author XieXiongXiong
 * @date 2021 -06-15
 */
public class WebApiClassLoader extends ClassLoader {
    /**
     * Web api class loader
     *
     * @param parent parent
     */
    public WebApiClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String path){
        byte[] bytes = loadBytes(path);
        return defineClass(path,bytes,0,bytes.length);
    }

    /**
     * Load bytes byte [ ].
     *
     * @param path the path
     * @return the byte [ ]
     * @author XieXiongXiong
     * @date 2021 -06-15 10:17:52
     */
    public static byte[] loadBytes(String path){
        byte[] bytes = new byte[0];
        try {
            String[] split = path.split("\\.");
            String abPath = PathConfig.EXT_JAVA_DIR +split[split.length-1] + ".class";
            bytes = Files.readAllBytes(Paths.get(abPath));
        } catch (IOException e) {
        }
        return  bytes;
    }

    /**
     * Read first line string.
     *
     * @param path the path
     * @return the string
     * @author XieXiongXiong
     * @date 2021 -06-15 10:17:52
     */
    public static String readFirstLine(String path){
        String firstLine = null;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            firstLine = bf.readLine();
            bf.close();
        } catch (IOException e) {
        }
        return firstLine;
    }
    public static String getName(String path){
        String name = path;
        if (path.contains(PathConfig.EXT_JAVA_DIR)) {
            String line = readFirstLine(path);
            line = line.trim();
            String separator = System.getProperty("os.name").toLowerCase().contains("win") ? "\\\\" : "/";
            final String[] split = path.split(separator);
            String last = split[split.length - 1];
            String[] split1 = last.split("\\.");
            String suffix = split1[0];
            if (line == null || line.isEmpty() || !line.startsWith(PathConfig.PACKAGE)){
                name = suffix;
            }else {
                String prefix = line.replace(PathConfig.PACKAGE,"").replace(";","").trim();
                name = prefix + "." + suffix;
            }

        }
        return name;
    }

}

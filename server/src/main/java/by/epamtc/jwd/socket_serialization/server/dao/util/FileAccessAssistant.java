package by.epamtc.jwd.socket_serialization.server.dao.util;

import by.epamtc.jwd.socket_serialization.server.dao.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class FileAccessAssistant {
    private Logger logger = LoggerFactory.getLogger(FileAccessAssistant.class);
    private static final FileAccessAssistant instance = new FileAccessAssistant();

    private String sourceFilesPath = null;

    public static FileAccessAssistant getInstance() {
        return instance;
    }

    public String formTextFilePath(String textName) throws DaoException {
        if (sourceFilesPath == null) {
            initializeSourceFilesPath();
        }
        return sourceFilesPath + File.separator + textName;
        // previous variant
        // return System.getProperty("java.class.path") + File.separator + textName;
    }

    private void initializeSourceFilesPath() throws DaoException {
        try {
            Enumeration<URL> resources = Thread.currentThread()
                    .getContextClassLoader().getResources(".");
            sourceFilesPath = new File(resources.nextElement().toURI())
                    .getAbsolutePath();
        } catch (IOException | URISyntaxException e) {
            logger.error("ERROR CAUGHT WHILE FINDING A SOURCES FILES PATH", e);
            throw new DaoException("ERROR CAUGHT WHILE FINDING A SOURCES FILES" +
                    "PATH", e);
        }
    }
}

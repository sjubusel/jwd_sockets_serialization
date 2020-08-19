package by.epamtc.jwd.socket_serialization.server.dao.impl;

import by.epamtc.jwd.socket_serialization.model.response.Text;
import by.epamtc.jwd.socket_serialization.model.response.composite.TextComposite;
import by.epamtc.jwd.socket_serialization.server.dao.TextDao;
import by.epamtc.jwd.socket_serialization.server.dao.exception.DaoException;
import by.epamtc.jwd.socket_serialization.server.dao.util.FileAccessAssistant;
import by.epamtc.jwd.socket_serialization.server.dao.util.ParserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import static by.epamtc.jwd.socket_serialization.model.response.RegExPattern.BLANK_SPACE;
import static by.epamtc.jwd.socket_serialization.model.response.RegExPattern.REPEATING_WHITESPACES;

public class FileTextDao implements TextDao {
    private Logger logger = LoggerFactory.getLogger(FileTextDao.class);
    private ParserProvider parserProvider = new ParserProvider();
    private FileAccessAssistant fileAssistant = FileAccessAssistant.getInstance();

    private StringBuilder codeBlockBuilder = new StringBuilder();
    private StringBuilder nonCodeBlockBuilder = new StringBuilder();
    private StringBuilder currentBuilder = nonCodeBlockBuilder;

    @Override
    public Text find(String textName) throws DaoException {
        Text text = new TextComposite();
        String filePath = fileAssistant.formTextFilePath(textName);

        try (FileReader in = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(in)) {

            Deque<String> curlyBracketsStack = new LinkedList<>();
            while (bufferedReader.ready()) {
                String line = cleanLine(bufferedReader.readLine());
                boolean isCodelineable = parserProvider.isCodeLine(line,
                        curlyBracketsStack);

                if (!curlyBracketsStack.isEmpty()) {
                    if (currentBuilder == nonCodeBlockBuilder) {
                        forwardToParsing(currentBuilder, text);
                        changeCurrentBuilder(codeBlockBuilder);
                    }
                    currentBuilder.append(line).append('\n');
                    continue;
                }

                if (!isCodelineable) {
                    if (currentBuilder == codeBlockBuilder) {
                        forwardToParsing(currentBuilder, text);
                        changeCurrentBuilder(nonCodeBlockBuilder);
                    }
                } else {
                    if (currentBuilder == nonCodeBlockBuilder) {
                        forwardToParsing(currentBuilder, text);
                        changeCurrentBuilder(codeBlockBuilder);
                    }
                }
                currentBuilder.append(line).append('\n');
            }
        } catch (FileNotFoundException e) {
            logger.error("FILE WITH NAME \"{}\" NOT FOUND", textName, e);
            throw new DaoException("FILE NOT FOUND", e);
        } catch (IOException e) {
            logger.error("OTHER DAO IO ERROR", e);
            throw new DaoException("OTHER DAO IO ERROR", e);
        }

        forwardToParsing(currentBuilder, text);
        currentBuilder.delete(0, currentBuilder.length());

        return text;
    }

    private String cleanLine(String line) {
        line = line.replaceAll(REPEATING_WHITESPACES, BLANK_SPACE);
        return line.trim();
    }

    private void forwardToParsing(StringBuilder currentBuilder, Text text) {
        String parsableBlock = new String(currentBuilder);
        parserProvider.parseAndUpdate(parsableBlock, text);
    }

    private void changeCurrentBuilder(StringBuilder codeBlockBuilder) {
        currentBuilder.delete(0, currentBuilder.length());
        currentBuilder = codeBlockBuilder;
    }
}

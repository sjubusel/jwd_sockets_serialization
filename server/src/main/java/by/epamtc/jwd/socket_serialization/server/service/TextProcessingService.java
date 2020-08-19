package by.epamtc.jwd.socket_serialization.server.service;

import by.epamtc.jwd.socket_serialization.model.response.Text;

public interface TextProcessingService {
    void deleteWordsOfLengthIfFirstLetterIsConsonant(Text text, int length);

    void deleteCoincidencesOfFirstLetterOfEachWord(Text text);

    void replaceWordsOfLengthWithSubstring(Text text, int length,
            String substring);
}

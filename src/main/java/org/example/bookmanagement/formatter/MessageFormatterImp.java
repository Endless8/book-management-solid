package org.example.bookmanagement.formatter;

import java.util.List;

import static org.example.bookmanagement.constants.Formats.LIST_FORMAT;

public class MessageFormatterImp implements MessageFormatter {

    public <T> String formatList(List<T> objects) {
        int counter = 1;
        StringBuilder result = new StringBuilder();

        for (T object : objects)
            result.append(String.format(LIST_FORMAT, counter++, object.toString()));

        return result.toString();
    }

    public String formatErrorMessage(String message, String value) {
        return String.format(message, value);
    }
}

package org.example.bookmanagement.formatter;

import java.util.List;

public interface MessageFormatter {

    <T> String formatList(List<T> objects);
    String formatErrorMessage(String message, String value);
}

package ru.job4j.ood.isp;

public interface FileManager {
    void readFile(String fileName);
    void writeFile(String fileName, String content);
    void deleteFile(String fileName);
    void moveFile(String source, String destination);
}

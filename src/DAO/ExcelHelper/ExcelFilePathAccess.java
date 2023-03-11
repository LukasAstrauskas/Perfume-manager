package DAO.ExcelHelper;

import java.io.*;


public class ExcelFilePathAccess {

    private final File file = new File("ExcelFilePath.txt");


    public boolean checkIfFileExists() {
            return file.exists();
    }

    public void writeData(String info) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readData() {
        StringBuilder builder = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String s = buffer.readLine();
            builder.append(s);
            buffer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public void createFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

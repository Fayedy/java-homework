package nju.java;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperation {
    private static File readFile = null;
    private static FileReader fileReader = null;
    private static BufferedReader bufferedReader = null;

    private static File writeFile = null;
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;


    public static File getReadFile() {
        return readFile;
    }

    public static void setReadFile(File temp) {
        readFile = temp;

        try {
            fileReader = new FileReader(readFile);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void newWriteFile() {
        writeFile = null;
    }

    public static boolean ifexistWriteFile() {
        return writeFile != null;
    }

    public static synchronized String getNextString(){
        if( readFile == null )
            return null;

        String str = null;
        try {
            str = bufferedReader.readLine();


            if (str == null) {

                bufferedReader.close();
                fileReader.close();
            }
        }
        catch (IOException e){

        }
        return str;
    }

    public static synchronized void writeFile(ArrayList world, ArrayList deadworld) throws IOException{
        if (writeFile == null) {
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            File directory = new File("save");
            if( ! directory.exists() ){
                directory.mkdir();
            }

            String str = "save" + File.separator + simpleDateFormat.format(now) + ".record";
            writeFile = new File(str);

        }

        try {
            fileWriter = new FileWriter(writeFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            ArrayList<String> str = new ArrayList<String>();

            for (int i = 0; i < world.size(); i++) {
                Position p = (Position) world.get(i);
                str.add(p.getHolder().toString() + " " + p.getX() + " " + p.getY() + " " + 1);
            }
            for (int i = 0; i < deadworld.size(); i++) {
                Position p = (Position) deadworld.get(i);
                str.add(p.getHolder().toString() + " " + p.getX() + " " + p.getY() + " " + 0);
            }

            for (String s : str) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }

            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }
}

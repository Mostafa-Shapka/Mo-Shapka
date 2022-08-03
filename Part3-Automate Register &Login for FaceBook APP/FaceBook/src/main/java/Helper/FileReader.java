package Helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class FileReader {

    public JsonObject readDate(String Path) throws FileNotFoundException {


    BufferedReader bufferReader = new BufferedReader(new java.io.FileReader(Path));
    Gson gson = new Gson();
    return gson.fromJson (bufferReader,JsonObject.class);



}



}

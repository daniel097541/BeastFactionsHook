package info.beastsoftware.hookcore.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface JSON {

    default String toJSON(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}

package info.beastsoftware.hookcore.entity;

import com.google.gson.Gson;

public interface JSON {

    default String toJSON(){
        return new Gson().toJson(this);
    }

}

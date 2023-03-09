package model.persistence;

import org.json.JSONObject;

// This interface indicates that something is serializable into a JSONObject
public interface Writable {
    JSONObject toJson();
}

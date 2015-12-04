package main.tasks;

import org.jdom2.Element;

public interface XMLSeriazable {
    
    public static final String NAME = "name";
    public static final String KEY = "key";
    public static final String TIME_LIMIT = "time-limit";
    public static final String TYPE = "type";
    public static final String ANSWER = "answer";
    
    public Element getXMLElement();
    
}

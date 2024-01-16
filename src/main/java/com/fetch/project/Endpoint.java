import java.util.Map;

public class Endpoint {
    private Map<String, String> headers;
    private String body;
    private String method;
    private String name;
    private String url;


    // Getters and setters for each field
    public Map<String, String> getHeaders(){
        return headers;
    }
    public void setHeaders(Map<String, String> headers){
        this.headers = headers;
    }
    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body = body;
    }
    public String getMethod() {
        return method != null ? method : "GET"; // Default method is GET
    }
    public void setMethod(String method){
        this.method = method;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String toString(){
        return "*********************\n"+headers+"\n"+body+"\n"+method+"\n"+name+"\n"+url+"\n*********************";
    }
}
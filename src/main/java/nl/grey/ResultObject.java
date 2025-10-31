package nl.grey;

public class ResultObject {
    int resultCode;
    String body;

    public ResultObject(int resultCode, String body) {
        this.resultCode = resultCode;
        this.body = body;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "resultCode=" + resultCode +
                ", body='" + body + '\'' +
                '}';
    }
}

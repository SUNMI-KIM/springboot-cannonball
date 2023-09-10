package cannonball.cannonball.DTO;

public class ResponseList {
    private String message;
    private Object data;
    private int count;

    public ResponseList(String message, Object data, int count) {
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

package cn.njyazheng.core.auth;

public class UnAuthorized<T> {
    private T content;
    public UnAuthorized() {
    
    }
    public UnAuthorized(T content) {
        this.content = content;
    }
    
    public T  getContent() {
        return content;
    }
    
    public void setContent(T  content) {
        this.content = content;
    }
}

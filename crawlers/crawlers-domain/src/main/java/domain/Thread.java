package domain;

public class Thread {

    private String score;
    private String title;
    private String commentsUrl;
    private String threadUrl;

    public void setScore(String score) {
        this.score = score;
    }

    public void setCommentsUrl(String commentsUrl){
        this.commentsUrl = commentsUrl;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setThreadUrl(String threadUrl){
        this.threadUrl = threadUrl;
    }

    public String getScore(){
        return "Score: " + this.score;
    }

    public String getTitle(){
        return "Título da Thread: " + this.title;
    }

    public String getCommentsUrl(){
        return "URL dos comentários: " + this.commentsUrl;
    }

    public String getThreadUrl(){
        return "URL da Thread: "+ this.threadUrl;
    }
}

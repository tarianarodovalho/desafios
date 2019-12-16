package domain;

import java.util.List;

public class SubReddit {

    private String title;
    private List<Thread> threads;

    public void setTitle(String title){
        this.title = title;
    }

    public void setThreads(List<Thread> threads){
        this.threads = threads;
    }

    public String getTitle(){
        return "Título: " + this.title;
    }

    public List<Thread> getThreads(){
        return this.threads;
    }
}

package model;

public class Task {
    private String title;    //标题
    private int id;          //排序
    private String content;  //内容说明
    private String time;     //保留最后一次修改的时间
    private int clock;       //番茄钟的数量

    public Task(String title,int id,String content,String time,int clock){
        this.title=title;
        this.id=id;
        this.content=content;
        this.time=time;
        this.clock=clock;
    }
    public Task(int id,String title,String time){
        this.id=id;
        this.title=title;
        this.time=time;
    }
    public Task(String title,String time){
        this.title=title;
        this.time=time;
    }
    public  Task(String title,String content,String time){
        this.title=title;
        this.content=content;
        this.time=time;
    }
    public Task(String title,int id,String content,String time){
        this.title=title;
        this.time=time;
        this.content=content;
        this.id=id;
    }
    public Task(int id,String title,String time,int clock){
        this.id=id;
        this.time=time;
        this.title=title;
        this.clock=clock;
    }
    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public int getClock() {
        return clock;
    }
}

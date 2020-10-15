package DTOs;

import doiframework.core.annotations.DataConstructor;
import doiframework.core.annotations.DataObject;

@DataObject
public class News{
    public String author;
    public String content;
    public String date;
    public int id;
    public double month;
    public String publication;
    public String retrieved;
    public String title;
    public String url;
    public double year;

    @DataConstructor
    public News(String author, String content, String date, int id, double month, String publication, String retrieved, String title, String url, double year) {
        this.author = author;
        this.content = content;
        this.date = date;
        this.id = id;
        this.month = month;
        this.publication = publication;
        this.retrieved = retrieved;
        this.title = title;
        this.url = url;
        this.year = year;
    }

    @Override
    public String toString() {
        return "News{" +
                "author='" + author + "'\n" +
                ", content='" + content + "'\n" +
                ", date='" + date + "'\n" +
                ", id=" + id + "\n" +
                ", month=" + month + "\n" +
                ", publication='" + publication + "'\n" +
                ", retrieved='" + retrieved + "'\n" +
                ", title='" + title + "'\n" +
                ", url='" + url + "'\n" +
                ", year=" + year + "'\n" +
                '}';
    }
}

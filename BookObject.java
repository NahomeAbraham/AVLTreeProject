public class BookObject {
    public String isbn;
    public String title;
    public String authorName;

    public BookObject(String key, String tit, String name)  {
        isbn = key;
        title = tit;
        authorName = name;
    }

    public int compareTo(String element) {
        return isbn.compareTo(element);
    }
}

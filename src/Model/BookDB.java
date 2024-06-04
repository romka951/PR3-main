package Model;

import java.sql.*;
import java.util.ArrayList;

public class BookDB {
    static final String url = "jdbc:postgresql://localhost:5433/pr2";
    static final String username = "postgres";
    static final String pass = "Abdfik45";

    public static ArrayList<Book> select() {

        ArrayList<Book> books = new ArrayList<Book>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, pass)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    Book book = new Book(id, title, author);
                    books.add(book);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return books;
    }

    public static int insert(Book book) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, pass)){

                String paste = "INSERT INTO books (title, author) Values (?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(paste)){
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static Book selectOne(int id) {

        Book book = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, pass)){

                String sql = "SELECT * FROM books WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int bookId = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String author = resultSet.getString(3);
                        book = new Book(bookId, title, author);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return book;
    }

    public static int update(Book book) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, pass)){

                String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());
                    preparedStatement.setInt(3, book.getId());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int delete(int id) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, pass)){

                String sql = "DELETE FROM books WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futuredesignpatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/**
 *
 * @author mhady
 */


public class Library {
    private List<Member> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private ExecutorService executor = Executors.newFixedThreadPool(2); // Thread pool size

    // Fungsi CRUD untuk anggota
    public void addMember(String name, boolean isMember) {
        members.add(new Member(name, isMember));
        System.out.println("Member added: " + name);
    }

    public void removeMember(String name) {
        members.removeIf(member -> member.getName().equals(name));
        System.out.println("Member removed: " + name);
    }

    // Fungsi CRUD untuk buku
    public void addBook(String title) {
        books.add(new Book(title));
        System.out.println("Book added: " + title);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
        System.out.println("Book removed: " + title);
    }

    // Fungsi untuk meminjam buku (dengan pola Future)
    public Future<String> borrowBook(String bookTitle, String memberName) {
        Callable<String> borrowTask = () -> {
            boolean isMember = members.stream().anyMatch(member -> member.getName().equals(memberName));
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                String borrowDuration = isMember ? "30 days" : "14 days";
                return memberName + " is borrowing the book: " + bookTitle + " for " + borrowDuration;
            } else {
                return "Book not found: " + bookTitle;
            }
        };

        return executor.submit(borrowTask);
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void shutdown() {
        executor.shutdown();
    }
}


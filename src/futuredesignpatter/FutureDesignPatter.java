/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package futuredesignpatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/**
 *
 * @author mhady
 */
public class FutureDesignPatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Library library = new Library();

        // Adding members and books
        library.addMember("Abi", true);
        library.addMember("Alice", false);

        library.addBook("Java Programming");
        library.addBook("Design Patterns");

        // Borrowing books using Future
        Future<String> futureBorrow1 = library.borrowBook("Java Programming", "Abi");
        Future<String> futureBorrow2 = library.borrowBook("Design Patterns", "Alice");

        // Simulating other activities while waiting
        doSomethingElse();

        // Getting borrow results from Future
        try {
            System.out.println(futureBorrow1.get());
            System.out.println(futureBorrow2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Removing members and books
        library.removeMember("Abi");
        library.removeBook("Java Programming");

        library.shutdown();
    }

    private static void doSomethingElse() {
        System.out.println("Mohon Menunggu");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}

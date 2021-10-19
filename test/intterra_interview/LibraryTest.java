package intterra_interview;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void mostPopularAuthor() {

        String testAuthor = "Tolstoy";
        List<Book> books = new ArrayList<>();
        books.add(new Book(Collections.singletonList(testAuthor)));
        books.add(new Book(Collections.singletonList(testAuthor)));
        books.add(new Book(Collections.singletonList(testAuthor)));

        Library library = new Library();
        Optional<String> popularAuthor = library.mostPopularAuthor(books);

        assertTrue(popularAuthor.isPresent());
        assertEquals(testAuthor, popularAuthor.get());
    }


}

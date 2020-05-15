package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub1 = new Publisher();
        pub1.setCity("Lublin");
        pub1.setAddress("Piłsudskiego 15");
        pub1.setState("Lubelskie");
        pub1.setZip("20-406");
        pub1.setName("Conan");

        publisherRepository.save(pub1);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Book is good", "124");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(pub1);
        pub1.getBooksPublished().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pub1);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book ("J2EE", "34234");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(pub1);
        pub1.getBooksPublished().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pub1);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publishers: " + publisherRepository.count() + "\n" +
                ("Publisher published: " + pub1.getBooksPublished().size() + " of them"));

    }
}

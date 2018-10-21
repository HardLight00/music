package com.example.demo.model;

import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final AlbumRepository albumRepository;
    private final CompositionRepository compositionRepository;
    private final PerformerRepository performerRepository;
    private final PersonRepository personRepository;
    private final SongRepository songRepository;

    @Autowired
    public DataLoader(AlbumRepository albumRepository,
                      CompositionRepository compositionRepository,
                      PerformerRepository performerRepository,
                      PersonRepository personRepository,
                      SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.compositionRepository = compositionRepository;
        this.performerRepository = performerRepository;
        this.personRepository = personRepository;
        this.songRepository = songRepository;
    }

    // fill the DB
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 100; i++) {
            personRepository.saveAndFlush(new Person("Name" + i, "Surname" + i));
            songRepository.saveAndFlush(new Song("Title" + i, "Lyrics"));
        }

        for (int i = 0; i < 30; i++) {
            Performer performer = new Performer("GroupName" + i);
            performerRepository.saveAndFlush(performer);

            Album album = new Album("Title" + i, performer.getId(), 2000);
            albumRepository.saveAndFlush(album);

            Person author = personRepository.getFirstByFirstNameAndLastName("Name" + i, "Surname" + i);
            Person compositor = personRepository.getFirstByFirstNameAndLastName("Name" + i * 2, "Surname" + i * 2);

            Song song1 = songRepository.getFirstByTitle("Title" + i);
            Song song2 = songRepository.getFirstByTitle("Title" + i * 2);

            Composition composition1 = new Composition(author.getId(), compositor.getId(), album.getId(), song1.getId(), performer);
            Composition composition2 = new Composition(author.getId(), compositor.getId(), album.getId(), song2.getId(), performer);

            compositionRepository.saveAndFlush(composition1);
            compositionRepository.saveAndFlush(composition2);

            performer.getCompositions().add(composition1);
            performer.getCompositions().add(composition2);

            Person person1 = personRepository.getFirstByFirstNameAndLastName("Name" + i, "Surname" + i);
            Person person2 = personRepository.getFirstByFirstNameAndLastName("Name" + i * 2, "Surname" + i * 2);
            Person person3 = personRepository.getFirstByFirstNameAndLastName("Name" + i * 3, "Surname" + i * 3);

            performer.getPersons().add(person1);
            performer.getPersons().add(person2);
            performer.getPersons().add(person3);

            performerRepository.saveAndFlush(performer);
        }
    }
}
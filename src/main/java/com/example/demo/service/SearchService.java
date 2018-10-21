package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SearchService {
    private final AlbumRepository albumRepository;
    private final CompositionRepository compositionRepository;
    private final PerformerRepository performerRepository;
    private final PersonRepository personRepository;
    private final SongRepository songRepository;

    public SearchService(AlbumRepository albumRepository,
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

    /**
     * function to search for albums by key value
     *
     * @param key the base value for searching suggestions
     * @return list of albums which contains key value
     */
    public List<Album> getAlbumSuggeston(String key) {
        List<Album> suggestions = new ArrayList<>();

        for (Album album : albumRepository.findAll()) {
            if (isSuggest(album.getTitle(), key)) {
                suggestions.add(album);
            }
        }

        return suggestions;
    }

    /**
     * function to search for compositions by key value
     *
     * @param key the base value for searching suggestions
     * @return list of compositions which contains key value
     */
    public List<Composition> getCompositionSuggestion(String key) {
        List<Composition> suggestions = new ArrayList<>();

        long songId;
        Song song;
        for (Composition composition : compositionRepository.findAll()) {
            songId = composition.getSongId();
            song = songRepository.findById(songId).orElse(null);

            assert song != null;
            if (isSuggest(song.getTitle(), key)) {
                suggestions.add(composition);
            }
        }

        return suggestions;
    }

    /**
     * function to search for performers by key value
     *
     * @param key the base value for searching suggestions
     * @return list of performers which contains key value
     */
    public List<Performer> getPerformerSuggestion(String key) {
        List<Performer> suggestion = new ArrayList<>();

        boolean isGroup;
        boolean isArtistFromGroup = false;
        Set<Person> artistsFromGroup;
        for (Performer performer : performerRepository.findAll()) {
            isGroup = isSuggest(performer.getGroupName(), key);

            artistsFromGroup = performer.getPersons();
            String fullArtistName;
            for (Person artist : artistsFromGroup) {
                fullArtistName = artist.getFirstName() + " " + artist.getLastName();
                isArtistFromGroup = isSuggest(fullArtistName, key);
            }

            if (isGroup || isArtistFromGroup) {
                suggestion.add(performer);
            }
        }

        return suggestion;
    }

    /**
     * function to search for authors by key value
     *
     * @param key the base value for searching suggestions
     * @return list of authors which contains key value
     */
    public List<Person> getAuthorSuggestion(String key) {
        List<Person> suggestions = new ArrayList<>();

        long authorId;
        Person author;
        String fullAuthorName;
        for (Composition composition : compositionRepository.findAll()) {
            authorId = composition.getAuthorId();
            author = personRepository.findById(authorId).orElse(null);

            assert author != null;
            fullAuthorName = author.getFirstName() + " " + author.getLastName();
            if (isSuggest(fullAuthorName, key)) {
                suggestions.add(author);
            }
        }

        return suggestions;
    }

    /**
     * function to search for composers by key value
     *
     * @param key the base value for searching suggestions
     * @return list of composers which contains key value
     */
    public List<Person> getComposerSuggestion(String key) {
        List<Person> suggestions = new ArrayList<>();

        long composerId;
        Person composer;
        String fullcomposerName;
        for (Composition composition : compositionRepository.findAll()) {
            composerId = composition.getComposerId();
            composer = personRepository.findById(composerId).orElse(null);

            assert composer != null;
            fullcomposerName = composer.getFirstName() + " " + composer.getLastName();
            if (isSuggest(fullcomposerName, key)) {
                suggestions.add(composer);
            }
        }

        return suggestions;
    }

    /**
     * function determines the corresponding key values
     *
     * @param value compared input
     * @param key   the base for searching suggestions
     * @return true if @param value corresponding to @param key
     * return false otherwise
     */
    private boolean isSuggest(String value, String key) {
        return value.contains(key);
    }
}

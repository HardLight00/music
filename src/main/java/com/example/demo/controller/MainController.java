package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.model.Album;
import com.example.demo.model.Composition;
import com.example.demo.model.Performer;
import com.example.demo.model.Person;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {
    private final AlbumRepository albumRepository;
    private final CompositionRepository compositionRepository;
    private final PerformerRepository performerRepository;
    private final PersonRepository personRepository;
    private final SongRepository songRepository;
    private final SearchService searchService;

    @Autowired
    public MainController(AlbumRepository albumRepository,
                          CompositionRepository compositionRepository,
                          PerformerRepository performerRepository,
                          PersonRepository personRepository,
                          SongRepository songRepository,
                          SearchService searchService) {
        this.albumRepository = albumRepository;
        this.compositionRepository = compositionRepository;
        this.performerRepository = performerRepository;
        this.personRepository = personRepository;
        this.songRepository = songRepository;
        this.searchService = searchService;
    }

    @GetMapping(path = "/search_performers-by-composition-title")
    public ModelAndView searchPerformersByCompositionTitle(HttpServletRequest request) {
        String compositionTitle = (String) request.getSession().getAttribute("composition_title");
        List<Composition> compositionList = searchService.getCompositionSuggestion(compositionTitle);

        List<Performer> performerList = new LinkedList<>();
        Performer performer;
        for (Composition composition : compositionList) {
            performer = performerRepository.getByCompositionsContains(composition);
            if (performer != null) performerList.add(performer);
        }
        return new ModelAndView().addObject("performers_list", performerList);
    }

    @GetMapping(path = "/search_performers-by-person")
    public ModelAndView searchPerformersByPerson(HttpServletRequest request) {
        String personName = (String) request.getSession().getAttribute("person_name");
        List<Performer> performerList = searchService.getPerformerSuggestion(personName);
        return new ModelAndView().addObject("performers_list", performerList);
    }


    @GetMapping(path = "/search_albums-by-composition-title")
    public ModelAndView searchAlbumsByCompositionTitle(HttpServletRequest request) {
        String compositionTitle = (String) request.getSession().getAttribute("composition_title");
        List<Composition> compositionList = searchService.getCompositionSuggestion(compositionTitle);

        List<Album> albumList = new LinkedList<>();
        Album album;
        for (Composition composition : compositionList) {
            album = albumRepository.getOne(composition.getAlbumId());
            if (album != null) albumList.add(album);
        }
        return new ModelAndView().addObject("albums_list", albumList);
    }

    @GetMapping(path = "/search_compositions-by-compositor")
    public ModelAndView searchCompositionsByCompositor(HttpServletRequest request) {
        String composerName = (String) request.getSession().getAttribute("compositor_name");
        List<Person> composersList = searchService.getComposerSuggestion(composerName);

        List<Composition> compositionList = new LinkedList<>();
        List<Composition> compositionsByComposer;
        for (Person composer : composersList) {
            compositionsByComposer = compositionRepository.getByComposerId(composer.getId());
            compositionList.addAll(compositionsByComposer);
        }
        return new ModelAndView().addObject("compositions_list", compositionList);
    }

    @GetMapping(path = "/search_compositions-by-author")
    public ModelAndView searchCompositionsByAuthor(HttpServletRequest request) {
        String authorName = (String) request.getSession().getAttribute("author_name");
        List<Person> authorList = searchService.getAuthorSuggestion(authorName);

        List<Composition> compositionList = new LinkedList<>();
        List<Composition> compositionsByAuthor;
        for (Person author : authorList) {
            compositionsByAuthor = compositionRepository.getByAuthorId(author.getId());
            compositionList.addAll(compositionsByAuthor);
        }
        return new ModelAndView().addObject("compositions_list", compositionList);
    }

    @GetMapping(path = "/search_compositions-by-performer")
    public ModelAndView searchCompositionsByPerformer(HttpServletRequest request) {
        String performerName = (String) request.getSession().getAttribute("performer_name");
        List<Performer> performerList = searchService.getPerformerSuggestion(performerName);

        List<Composition> compositionList = new LinkedList<>();
        List<Composition> compositionsByPerformer;
        for (Performer performer : performerList) {
            compositionsByPerformer = compositionRepository.getByPerformer(performer);
            compositionList.addAll(compositionsByPerformer);
        }
        return new ModelAndView().addObject("compositions_list", compositionList);
    }


}
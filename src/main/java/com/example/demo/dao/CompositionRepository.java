package com.example.demo.dao;

import com.example.demo.model.Composition;
import com.example.demo.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {
    List<Composition> getByComposerId(long compositorId);

    List<Composition> getByAuthorId(long authorId);

    List<Composition> getByPerformer(Performer performer);
}

package com.example.demo.dao;

import com.example.demo.model.Composition;
import com.example.demo.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
    Performer getFirstByGroupName(String groupName);

    List<Performer> getByGroupName(String groupName);

    Performer getFirstByCompositionsContaining(Composition composition);

    Performer getByCompositionsContains(Composition composition);
}

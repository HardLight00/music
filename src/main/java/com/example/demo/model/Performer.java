package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "performers")
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(max = 129)
    @Column(name = "group_name")
    private String groupName;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JoinTable(name = "performers_persons",
            joinColumns = {@JoinColumn(name = "performer_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private Set<Person> persons = new HashSet<>();


    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Composition> compositions = new HashSet<>();

    protected Performer() {
    }

    public Performer(@NotNull @Size(max = 129) String groupName) {
        this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(Set<Composition> compositions) {
        this.compositions = compositions;
    }
}

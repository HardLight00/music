package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "compositions")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "author_id")
    private long authorId;

    @NotNull
    @Column(name = "composer_id")
    private long composerId;

    @NotNull
    @Column(name = "album_id")
    private long albumId;

    @NotNull
    @Column(name = "song_id")
    private long songId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "performer", nullable = false)
    private Performer performer;

    protected Composition() {
    }

    public Composition(@NotNull long authorId, @NotNull long composerId, @NotNull long albumId, @NotNull long songId, Performer performer) {
        this.authorId = authorId;
        this.composerId = composerId;
        this.albumId = albumId;
        this.songId = songId;
        this.performer = performer;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getComposerId() {
        return composerId;
    }

    public void setComposerId(long composerId) {
        this.composerId = composerId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }
}

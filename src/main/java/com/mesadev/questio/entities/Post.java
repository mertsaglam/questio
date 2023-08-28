package com.mesadev.questio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)//tabloları bağla
    @OnDelete(action = OnDeleteAction.CASCADE)//bir user silinince postlarını da sil
    private User user;

    private String title;
    @Lob
            @Column(columnDefinition = "text")
    private String text;
}

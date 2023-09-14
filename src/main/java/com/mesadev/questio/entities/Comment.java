package com.mesadev.questio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name ="comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//tabloları bağla
    @OnDelete(action = OnDeleteAction.CASCADE)//bir user silinince postlarını da sil
    @JsonIgnore
    private User user;
    @Lob
            @Column(columnDefinition = "text")
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}

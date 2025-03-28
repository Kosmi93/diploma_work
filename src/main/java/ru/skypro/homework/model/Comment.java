package ru.skypro.homework.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ad")
    private Ad ad;

    private Long createdAt;

    private String text;

}

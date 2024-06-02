    package com.movie_library.moviewebapp.entity;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.ManyToOne;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Movie {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private boolean isPublic;

        @ManyToOne
        private User user;
        // @ElementCollection
        // private List<String> movies;

    }

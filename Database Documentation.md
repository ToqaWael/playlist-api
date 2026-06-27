# Database Documentation — Playlist API

## Technology Choice

The project uses **MySQL**, a relational database, because the data model is inherently relational: a **Playlist** contains many **Songs**, and each **Song** belongs to exactly one **Playlist**. MySQL handles this naturally and integrates directly with Spring Boot via JPA/Hibernate.

---

## ORM Layer

The app uses **JPA (Java Persistence API)** with Hibernate. Java model classes are mapped to database tables via annotations — no manual SQL required. On startup, Hibernate reads these annotations and creates or updates the schema automatically.

---

## Database

**Name:** `playlistdb`

---

## Schema

### `playlist`

| Column | Type           | Notes                       |
| ------ | -------------- | --------------------------- |
| `id`   | `BIGINT`       | Primary key, auto-increment |
| `name` | `VARCHAR(255)` | Playlist name               |

### `song`

| Column        | Type           | Notes                       |
| ------------- | -------------- | --------------------------- |
| `id`          | `BIGINT`       | Primary key, auto-increment |
| `title`       | `VARCHAR(255)` | Song title                  |
| `artist`      | `VARCHAR(255)` | Artist name                 |
| `playlist_id` | `BIGINT`       | Foreign key → `playlist.id` |

### Relationship

```
playlist            song
────────            ────
id (PK)  ◄──────── playlist_id (FK)
name                id (PK)
                    title
                    artist
```

One playlist → many songs. Each song belongs to exactly one playlist.

---

## Entity Classes

```java
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;
}

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
```

---

## Configuration

**File:** `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/playlistdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

| Property                | Description                                        |
| ----------------------- | -------------------------------------------------- |
| `datasource.url`        | MySQL host, port, and database name                |
| `username` / `password` | MySQL credentials                                  |
| `ddl-auto=update`       | Schema is created/updated automatically on startup |
| `show-sql=true`         | Logs generated SQL to the console                  |

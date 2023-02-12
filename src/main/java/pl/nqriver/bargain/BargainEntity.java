package pl.nqriver.bargain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;


@Entity(name = "BargainEntity")
@Table(name = "bargains")
class BargainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "hyperlink")
    private String hyperlink;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private Instant lastUpdatedAt;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @OneToOne(mappedBy = "bargain", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    BargainMetersEntity bargainMeters;

    BargainEntity(String title, String description, String hyperlink) {
        this.title = title;
        this.description = description;
        this.hyperlink = hyperlink;
        this.expiresAt = Instant.now().plus(Duration.ofDays(1));
    }

    public BargainEntity() {
    }

    static BargainEntity of(BargainCreate create) {
        return new BargainEntity(
                create.title(),
                create.description(),
                create.hyperlink()
        );
    }

    public BargainResponse toBargainResponse() {
        return new BargainResponse(
                this.id,
                this.title,
                this.description,
                this.hyperlink,
                this.createdAt,
                this.expiresAt);
    }

    public BargainMetersResponse toBargainMetersResponse() {
        return bargainMeters.toBargainMetersResponse(this);
    }

    public Long getId() {
        return this.id;
    }

    public BargainMetersEntity getBargainMeters() {
        return bargainMeters;
    }
}

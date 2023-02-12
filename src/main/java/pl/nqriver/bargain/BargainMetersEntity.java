package pl.nqriver.bargain;

import javax.persistence.*;


@Entity(name = "BargainMeters")
@Table(name = "bargain_meters")
class BargainMetersEntity {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "bargain_id")
    private BargainEntity bargain;

    @Column(name = "likes_count")
    private Long likesCount = 0L;

    @Column(name = "dislikes_count")
    private Long dislikesCount = 0L;

    @Column(name = "shares_count")
    private Long sharesCount = 0L;

    public BargainMetersEntity() {

    }

    public Long getId() {
        return id;
    }

    public BargainEntity getBargain() {
        return bargain;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public Long getDislikesCount() {
        return dislikesCount;
    }

    public Long getSharesCount() {
        return sharesCount;
    }

    private BargainMetersEntity(BargainEntity bargain) {
        this.bargain = bargain;
    }

    BargainMetersResponse toBargainMetersResponse(BargainEntity entity) {
        return new BargainMetersResponse(
                entity.toBargainResponse(),
                this.likesCount,
                this.dislikesCount,
                this.sharesCount
        );
    }

    static BargainMetersEntity of(BargainEntity bargain) {
        return new BargainMetersEntity(bargain);
    }
}

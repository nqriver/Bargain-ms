package pl.nqriver.bargain;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BargainFacade {


    @Inject
    BargainRepository bargainRepository;

    @Inject
    BargainMetersRepository bargainMetersRepository;


    public Uni<BargainResponse> create(BargainCreate dto) {
        BargainEntity entity = BargainEntity.of(dto);
        return Panache.withTransaction(() -> bargainRepository.persist(entity)
                .onItem()
                .transformToUni(bargain -> {
                    BargainMetersEntity bargainMeters = BargainMetersEntity.of(bargain);
                    return bargainMetersRepository.persist(bargainMeters)
                            .map(bargainMetersEntity -> bargain);
                }))
                .map(BargainEntity::toBargainResponse);
    }

    public Uni<BargainMetersResponse> find(Long id) {
        Log.debugf("Calling bargainMeters find for id {%s}", id);
        return bargainRepository.findById(id)
                .map(BargainEntity::toBargainMetersResponse);
    }

}

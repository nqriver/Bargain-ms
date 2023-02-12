package pl.nqriver.bargain;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class BargainMetersRepository implements PanacheRepository<BargainMetersEntity> {
}

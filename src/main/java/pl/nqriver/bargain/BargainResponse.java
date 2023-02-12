package pl.nqriver.bargain;

import java.time.Instant;

public record BargainResponse(Long id,
                              String title,
                              String description,
                              String hyperlink,
                              Instant createdAt,
                              Instant expiresAt) {
}

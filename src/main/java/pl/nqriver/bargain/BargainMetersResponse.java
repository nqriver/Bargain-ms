package pl.nqriver.bargain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record BargainMetersResponse(@JsonUnwrapped BargainResponse bargainResponse,
                                    Long likesCount,
                                    Long dislikesCount,
                                    Long sharesCount
) {}

package com.artefatox.sbm.catalog.domain.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainException extends RuntimeException {
  private final String code;
  private final String message;
  private final int status;
}

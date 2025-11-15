package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table {
    final String code;
    String name;
    TableStatus status;

    public Table(String code, String name, TableStatus status) {
        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }
        if (Objects.isNull(status)) {
            throw new DomainValidationException("common.invalid_input", "table status");
        }

        this.code = code;
        this.name = name;
        this.status = status;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    private void alreadyInState(String action) {
        throw new DomainException("table.already_in_state", code, status, action);
    }

    private void invalidTransition(String action) {
        throw new DomainException("table.invalid_transition", code, status, action);
    }

    public void reserve() {
        status = switch (status) {
            case AVAILABLE -> TableStatus.RESERVED;
            case RESERVED -> {
                alreadyInState("reserve");
                yield status;
            }
            case OUT_OF_SERVICE, OCCUPIED -> {
                invalidTransition("reserve");
                yield status;
            }
        };
    }

    public void freeTable() {
        status = switch (status) {
            case OCCUPIED -> TableStatus.AVAILABLE;
            case AVAILABLE -> {
                alreadyInState("available");
                yield status;
            }
            case RESERVED, OUT_OF_SERVICE -> {
                invalidTransition("available");
                yield status;
            }
        };
    }

    public void markOutOfService() {
        status = switch (status) {
            case AVAILABLE -> TableStatus.OUT_OF_SERVICE;
            case OUT_OF_SERVICE -> {
                alreadyInState("out of service");
                yield status;
            }
            case RESERVED, OCCUPIED -> {
                invalidTransition("out of service");
                yield status;
            }
        };
    }

    public void markAvailable() {
        status = switch (status) {
            case OUT_OF_SERVICE -> TableStatus.AVAILABLE;
            case AVAILABLE -> {
                alreadyInState("available");
                yield status;
            }
            case RESERVED, OCCUPIED -> {
                invalidTransition("available");
                yield status;
            }
        };
    }

    public void seatCustomer() {
        status = switch (status) {
            case AVAILABLE -> TableStatus.RESERVED;
            case RESERVED -> {
                alreadyInState("reserved");
                yield status;
            }
            case OUT_OF_SERVICE, OCCUPIED -> {
                invalidTransition("reserved");
                yield status;
            }
        };
    }
}

package com.hades.hadescoffee.domain.vo;

import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Money {
    final BigDecimal amount;
    static final int SCALE = 2;

    public Money(BigDecimal amount) {
        if (Objects.isNull(amount)) {
            throw new DValidationException("common.invalid_input", "money");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DValidationException("common.invalid_input", "money");
        }

        this.amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static Money of(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("common.invalid_input", "money subtraction");
        }

        return new Money(result);
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(factor));
    }

    public BigDecimal toBigDecimal() {
        return this.amount;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isEqualTo(Money other) {
        return this.amount.compareTo(other.amount) == 0;
    }

    public boolean isGreaterOrEqual(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    public boolean isLessOrEqual(Money other) {
        return this.amount.compareTo(other.amount) <= 0;
    }

}

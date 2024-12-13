package lms.spring61ref.spring.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}

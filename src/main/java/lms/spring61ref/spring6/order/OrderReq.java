package lms.spring61ref.spring6.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}

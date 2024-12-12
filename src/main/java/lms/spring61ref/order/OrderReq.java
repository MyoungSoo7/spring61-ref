package lms.spring61ref.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}

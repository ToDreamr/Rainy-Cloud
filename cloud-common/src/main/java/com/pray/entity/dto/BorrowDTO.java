package com.pray.entity.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * BorrowDTO
 * <p>
 *
 * @author 花行 (Rain)
 * @since 2025/6/16 18:04
 */
@Getter
@Setter
public class BorrowDTO extends CommonDTO{

    private int bookId;

    private int userId;

}

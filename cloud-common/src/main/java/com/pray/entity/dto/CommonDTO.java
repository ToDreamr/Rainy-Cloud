package com.pray.entity.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     CommonDTO
 * <p>
 *
 * @author 花行 (Rain)
 * @since  2025/5/26 9:55
 */
@Setter
@Getter
public class CommonDTO extends ToString {

    /**
     * id
     */
    private String id;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

package com.demo.springboot;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

/**
 * It is a class representing the detail of item.
 */
@Getter
@Setter
@JsonDeserialize
public final class Item {
    private String id;
    private String name;
}

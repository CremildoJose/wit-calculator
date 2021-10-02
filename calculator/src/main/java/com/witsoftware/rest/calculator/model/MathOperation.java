package com.witsoftware.rest.calculator.model;

import com.witsoftware.rest.calculator.utils.Operator;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class MathOperation implements Serializable {

    private UUID uuid;

    private int a;

    private int b;

    private Operator operator;

}

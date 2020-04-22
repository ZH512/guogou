package com.neu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShoppingExpress {
    @ApiModelProperty("运费满减所需最低消费")
    private String shopping_express_freight_value;
    @ApiModelProperty("运费满减不足所需运费")
    private String shopping_express_freight_min;
}

package com.yomakase.user.dto.request;

import com.yomakase.restaurant.dto.RestaurantRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private UserRequest userRequest;

    private RestaurantRequest restaurantRequest;
}

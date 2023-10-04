package Enums;

import lombok.Getter;

public enum Endpoints {
findPetsByStatus("/findByStatus");


@Getter
private final String url;


Endpoints(String url) {
    this.url = url;
    }
}

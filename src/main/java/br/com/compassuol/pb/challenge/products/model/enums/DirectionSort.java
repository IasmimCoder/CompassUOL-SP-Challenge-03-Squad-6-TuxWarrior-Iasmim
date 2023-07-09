package br.com.compassuol.pb.challenge.products.model.enums;

import lombok.Getter;

@Getter
public enum DirectionSort {
    ACS("ASC"),
	DESC("DESC");

    private String description;
    private DirectionSort(String description) {
        this.description = description;
    }

    public static DirectionSort fromString(String direction) {
        for (DirectionSort sort : DirectionSort.values()) {
            if (sort.description.equalsIgnoreCase(direction)) {
                return sort;
            }
        }
        return null; // Ou lance uma exceção caso necessário
    }
}

package com.icement.api.iCement.Domains.Shared.Dtos;

import lombok.Data;

@Data
public class PaginationDto {
    protected int pageNumber;
    protected int pageSize;
    protected String sortBy;
    protected String sortOrder;

    public PaginationDto() {
        this.pageNumber = 0;
        this.pageSize = 10;
        this.sortBy = "_id";
        this.sortOrder = "desc";
    }
}

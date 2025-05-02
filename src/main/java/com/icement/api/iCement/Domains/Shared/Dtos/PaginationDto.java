package com.icement.api.iCement.Domains.Shared.Dtos;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;

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

    public SortOperation generateAggregationSortStage() {
        return Aggregation.sort(Sort.by(
            Sort.Direction.fromString(sortOrder.toUpperCase()),
            sortBy
        ));
    }

    public SkipOperation generateAggregationSkipStage() {
        return Aggregation.skip(pageNumber * pageSize);
    }

    public MatchOperation generateAggregationMatchStage() {
        var criteria = new Criteria();
        criteria.and("deleted_at").is(null);
        return Aggregation.match(criteria);
    }
}

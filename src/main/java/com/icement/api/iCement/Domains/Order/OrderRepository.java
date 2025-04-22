package com.icement.api.iCement.Domains.Order;


import org.springframework.stereotype.Repository;

import com.icement.api.iCement.Domains.Shared.Repositories.BaseRepository;

@Repository
public class OrderRepository extends BaseRepository<Order> {
    
    OrderRepository() {
        super(Order.class);
    }

}

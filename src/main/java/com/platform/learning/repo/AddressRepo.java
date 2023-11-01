package com.platform.learning.repo;

import com.platform.learning.dao.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address,Long> {
}

package ru.yesdo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yesdo.model.Merchant;

/**
 * User: Dikansky
 * Date: 02.02.2015
 */
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
}

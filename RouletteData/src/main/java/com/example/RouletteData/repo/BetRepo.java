/**
 * 
 */
package com.example.RouletteData.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.RouletteData.models.Bet;

/**
 * @author joals
 *
 */
public interface BetRepo extends CrudRepository<Bet, Long> {

}

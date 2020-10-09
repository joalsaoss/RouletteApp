/**
 * 
 */
package com.example.RouletteData.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.RouletteData.models.Roulette;

/**
 * @author joals
 *
 */
public interface IRouletteRepo extends CrudRepository<Roulette, Long> {

}

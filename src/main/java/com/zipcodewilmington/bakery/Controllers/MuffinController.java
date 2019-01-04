package com.zipcodewilmington.bakery.Controllers;

import com.zipcodewilmington.bakery.Models.Muffin;
import com.zipcodewilmington.bakery.Repositories.MuffinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuffinController {

    @Autowired
    private MuffinRepository muffinRepository;

    @RequestMapping(value="/muffin",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Muffin>> index() {
        return new ResponseEntity<>(this.muffinRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/muffin/{id}",method = RequestMethod.GET)
    public ResponseEntity<Muffin> show(@PathVariable Long id) {
        return new ResponseEntity<>(this.muffinRepository.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value="/muffin",method = RequestMethod.POST)
    public ResponseEntity<Muffin> create(@RequestBody Muffin muffin) {
        return new ResponseEntity<>(this.muffinRepository.save(muffin), HttpStatus.CREATED);
    }

    @RequestMapping(value="/muffin/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Muffin> update(@PathVariable Long id,@RequestBody Muffin muffin) {
        Muffin foundMuffin = muffinRepository.findById(id).get();
        foundMuffin.setFlavor(muffin.getFlavor());

        return new ResponseEntity<>(this.muffinRepository.save(foundMuffin), HttpStatus.OK);
    }

    @RequestMapping(value="/muffin/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long id) {
        this.muffinRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}

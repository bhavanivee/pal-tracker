package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create( @RequestBody TimeEntry timeEntryToCreate) {
        if (timeEntryToCreate != null) {
            TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
            if (timeEntry != null) {
                ResponseEntity out = new ResponseEntity(timeEntry, HttpStatus.CREATED);
                return out;
            }
        }
        ResponseEntity out = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        return out;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry te = timeEntryRepository.find(id);
        if(te!=null) {
            ResponseEntity out = new ResponseEntity(te, HttpStatus.OK);
            return out;
        }
        else
        {
            ResponseEntity out = new ResponseEntity( HttpStatus.NOT_FOUND);
            return out;
        }
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry te = timeEntryRepository.update(id,expected);

        if(te == null){
            ResponseEntity out = new ResponseEntity(HttpStatus.NOT_FOUND);
            return out;
        } else {
            ResponseEntity out= new ResponseEntity(te, HttpStatus.OK);
            return out;
        }

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        ResponseEntity out= new ResponseEntity( HttpStatus.NO_CONTENT);
        return out;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> telist = timeEntryRepository.list();
        ResponseEntity out = new ResponseEntity(telist, HttpStatus.OK);
        return out;
    }
}

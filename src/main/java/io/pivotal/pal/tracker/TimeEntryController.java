package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value = "/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created = this.timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method = RequestMethod.GET)
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") long timeEntryId) {
        TimeEntry created = this.timeEntryRepository.find(timeEntryId);
        if(created  == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(created, HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("timeEntryId") long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry found = this.timeEntryRepository.update(timeEntryId, timeEntry);
        if(found != null)
            return new ResponseEntity(found, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("timeEntryId")  long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
